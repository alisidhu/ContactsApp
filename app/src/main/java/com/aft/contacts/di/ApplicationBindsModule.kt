package com.aft.contacts.di

import com.aft.contacts.resourses.AndroidResourceProvider
import com.aft.contacts.resourses.IResourceProvider
import com.aft.database.ContactsAppDbRepository
import com.aft.database.IContactsDb
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class ApplicationBindsModule {

    @Binds
    @Singleton
    abstract fun provideAndroidResource(androidResourceProvider: AndroidResourceProvider): IResourceProvider

    @Binds
    @Singleton
    abstract fun provideLocalRepository(repository: ContactsAppDbRepository): IContactsDb

}