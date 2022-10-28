package com.aft.contacts.base.interfaces

import android.view.View

interface OnMultipleItemClicksListener {
    fun onItemSingleClick(view: View, data: Any, pos: Int)
    fun onItemLongClick(view: View, data: Any, pos: Int)
}