package com.aft.contacts.base.bindings

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter

object ImageBinder {

    @JvmStatic
    @BindingAdapter("app:setSrcImg")
    fun setDrawable(imageView: ImageView, resId: Bitmap?) {
        resId?.let {
            imageView.setImageBitmap(resId)
        }
    }
}