package com.aft.database

import com.aft.database.dao.ContactDao
import com.aft.database.entity.ContactEntity
import javax.inject.Inject

class ContactsAppDbRepository @Inject constructor(private val contactDao: ContactDao) : IContactsDb {
    override suspend fun getAllContacts(): List<ContactEntity> = contactDao.getAllContacts()

    override suspend fun insertContacts(contacts: List<ContactEntity>) {
       return contactDao.insertContacts(contacts)
    }

    override suspend fun insertContact(contact: ContactEntity): Long {
        return  contactDao.insertContact(contact)
    }

    override suspend fun updateContact(contact: ContactEntity): Int {
        return  contactDao.updateContact(contact)
    }

    override suspend fun deleteContact(contact: ContactEntity): Int {
        return  contactDao.deleteContact(contact)
    }
}