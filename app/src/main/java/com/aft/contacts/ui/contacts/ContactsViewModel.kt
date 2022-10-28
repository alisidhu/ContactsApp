package com.aft.contacts.ui.contacts

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.ContactsContract
import android.provider.MediaStore
import com.aft.contacts.R
import com.aft.contacts.base.BaseViewModel
import com.aft.contacts.base.Dispatcher
import com.aft.contacts.models.ContactsModel
import com.aft.contacts.resourses.IResourceProvider
import com.aft.contacts.ui.contacts.adapter.ContactsListAdapter
import com.aft.database.ContactsAppDbRepository
import com.aft.database.entity.ContactEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.FileNotFoundException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val resourceProvider: IResourceProvider,
    private val dbRepository: ContactsAppDbRepository
) : BaseViewModel<IContacts.State>(), IContacts.ViewModel {
    override val state: IContacts.State = ContactsState()
    override val contactsListAdapter: ContactsListAdapter = ContactsListAdapter(mutableListOf())

    @SuppressLint("Range")
    override fun saveContacts(onSuccess: () -> Unit) {

        val contactList = ArrayList<ContactEntity>()

        val phones = resourceProvider.context.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC"
        )

        while (phones!!.moveToNext()) {
            val name =
                phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val phoneNo =
                phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            val id =
                phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID))
            val imageUri = phones.getString(
                phones.getColumnIndex(
                    ContactsContract.CommonDataKinds.Phone.PHOTO_URI
                )
            )


            val contact = ContactEntity(
                contactId = id,
                name = name,
                phone = phoneNo.trim(),
                imageUrl = imageUri?:"",
                isNoteUpdated = false
            )
            var hasNumber = contactList.filter { x -> x.phone == phoneNo }
            if (hasNumber.isEmpty()) {
                contactList.add(contact)
            }
        }
        phones.close()
        if (contactList.isNotEmpty()) {
            launch {
                dbRepository.insertContacts(contactList)
                onSuccess.invoke()
            }

        }
    }

    override fun getContactsList(onSuccess: () -> Unit) {
        launch {
            val list = dbRepository.getAllContacts()
            launch(Dispatcher.Main) {
                when {
                    list.isNotEmpty() -> {
                        try {
                            val contactList = ArrayList<ContactsModel>()
                            val defaultBitmap: Bitmap = BitmapFactory.decodeResource(
                                resourceProvider.context.resources,
                                R.drawable.no_photo
                            )
                            var bitmap: Bitmap = defaultBitmap
                            for (contact in list) {
                                if(contact.imageUrl.isNotEmpty()){
                                bitmap = MediaStore.Images.Media.getBitmap(
                                    resourceProvider.context.contentResolver,
                                    Uri.parse(contact.imageUrl)
                                )
                                }
                                else {
                                    bitmap = defaultBitmap
                                }

                                contactList.add(
                                    ContactsModel(
                                        contact.name,
                                        contact.contactId,
                                        contact.phone,
                                        bitmap
                                    )
                                )
                            }
                            contactsListAdapter.setList(contactList)
                            onSuccess.invoke()
                        } catch (e: FileNotFoundException) {
                            e.printStackTrace()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                    list.isEmpty() -> {
                        saveContacts {
                            getContactsList {
                                onSuccess.invoke()
                            }
                        }

                    }
                    else -> {}
                }
            }
        }
    }
}


