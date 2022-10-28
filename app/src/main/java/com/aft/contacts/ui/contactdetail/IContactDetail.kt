package com.aft.contacts.ui.contactdetail

import androidx.databinding.ObservableField
import com.aft.contacts.base.interfaces.IBase
import com.aft.contacts.models.ContactsModel


interface IContactDetail {
    interface View : IBase.View<ViewModel> {
    }

    interface ViewModel : IBase.ViewModel<State> {

    }

    interface State : IBase.State {
        var contactDetail: ObservableField<ContactsModel>

    }
}