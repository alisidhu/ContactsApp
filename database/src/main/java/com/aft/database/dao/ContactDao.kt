package com.aft.database.dao

import androidx.room.*
import com.aft.database.entity.ContactEntity

@Dao
interface ContactDao {
    @Query("SELECT * FROM contacts")
    suspend fun getAllContacts(): List<ContactEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(note: ContactEntity): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateContact(note: ContactEntity): Int

    @Delete
    suspend fun deleteContact(note: ContactEntity): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContacts(notes: List<ContactEntity>)

}