package com.aft.contacts.di

import android.content.Context
import androidx.room.Room
import com.aft.database.ContactsAppDb
import com.aft.database.dao.ContactDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDB(@ApplicationContext appContext: Context): ContactsAppDb {
        return Room.databaseBuilder(
            appContext,
            ContactsAppDb::class.java,
            "contactsApp"
        ).build()
    }

    @Provides
    fun provideNoteDao(appDatabase: ContactsAppDb): ContactDao {
        return appDatabase.contactsDao()
    }
}