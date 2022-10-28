package com.aft.contacts.ui.contactdetail

import androidx.databinding.ObservableField
import com.aft.contacts.base.BaseState
import com.aft.contacts.models.ContactsModel

class ContactDetailState : BaseState(), IContactDetail.State  {
    override var contactDetail: ObservableField<ContactsModel> = ObservableField<ContactsModel>()
}