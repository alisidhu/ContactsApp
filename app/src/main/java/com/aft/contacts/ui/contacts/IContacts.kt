package com.aft.contacts.ui.contacts

import androidx.lifecycle.MutableLiveData
import com.aft.contacts.base.interfaces.IBase
import com.aft.contacts.models.ContactsModel
import com.aft.contacts.ui.contacts.adapter.ContactsListAdapter


interface IContacts {
    interface View : IBase.View<ViewModel> {

    }

    interface ViewModel : IBase.ViewModel<State> {
        val contactsListAdapter: ContactsListAdapter

        fun saveContacts(onSuccess : () -> Unit)
        fun getContactsList(onSuccess: () -> Unit)
    }

    interface State : IBase.State {

    }
}