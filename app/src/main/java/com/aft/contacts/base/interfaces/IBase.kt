package com.aft.contacts.base.interfaces

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.aft.contacts.base.SingleClickEvent

interface IBase {
    interface View<V : ViewModel<*>> {
        val viewModel: V
    }

    interface ViewModel<S : State> :
        ILifecycle {
        val state: S
        fun getString(resourceId: Int): String
        val clickEvent: SingleClickEvent
    }

    interface State {
        var toast: String
        var loading: Boolean
        var error: String
        var viewState : MutableLiveData<Any?>
    }
}