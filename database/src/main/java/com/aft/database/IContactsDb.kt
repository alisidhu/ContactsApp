package com.aft.database

import com.aft.database.entity.ContactEntity

interface IContactsDb {
        suspend fun getAllContacts(): List<ContactEntity>
        suspend fun insertContacts(contacts: List<ContactEntity>)
        suspend fun insertContact(contact: ContactEntity): Long
        suspend fun updateContact(contact: ContactEntity): Int
        suspend fun deleteContact(contact: ContactEntity): Int

}