package com.aft.contacts.base

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.aft.contacts.base.interfaces.IBase
import androidx.databinding.library.baseAdapters.BR


abstract class BaseState : BaseObservable(), IBase.State {
    @get:Bindable
    override var loading: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(
                BR.loading)
        }


    @get:Bindable
    override var error: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.error)
        }

    @get:Bindable
    override var toast: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.toast)
        }
    override var viewState: MutableLiveData<Any?> = MutableLiveData()
}