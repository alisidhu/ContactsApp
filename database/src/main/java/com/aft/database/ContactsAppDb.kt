package com.aft.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aft.database.dao.ContactDao
import com.aft.database.entity.ContactEntity

@Database(entities = [ContactEntity::class], version = 1, exportSchema = false)
abstract class ContactsAppDb : RoomDatabase(){
    abstract fun contactsDao(): ContactDao

}