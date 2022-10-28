package com.aft.contacts.models

import com.aft.contacts.base.interfaces.OnItemClickListener

class ContactItemModel(
    val contact: ContactsModel,
    val position: Int,
    private val onItemClickListener: OnItemClickListener?
)