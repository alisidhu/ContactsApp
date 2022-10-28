package com.aft.contacts.base.interfaces

interface IFragmentHolder {
    fun onFragmentAttached() {}
    fun onFragmentDetached(tag: String) {}
}