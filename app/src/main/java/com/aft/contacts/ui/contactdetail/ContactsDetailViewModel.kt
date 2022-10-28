package com.aft.contacts.ui.contactdetail

import com.aft.contacts.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class ContactsDetailViewModel @Inject constructor() : BaseViewModel<IContactDetail.State>(), IContactDetail.ViewModel {
    override val state: IContactDetail.State = ContactDetailState()


}