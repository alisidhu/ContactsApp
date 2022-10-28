package com.aft.contacts.models

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContactsModel(

    var name: String? = null,
    var id: String? = null,
    var phone: String? = null,
    var pic: Bitmap? = null,
)  : Parcelable
