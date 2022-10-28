package com.aft.contacts.ui.main

import com.aft.contacts.base.interfaces.IBase


interface IMain {
    interface View : IBase.View<ViewModel> {

    }

    interface ViewModel : IBase.ViewModel<State> {
    }

    interface State : IBase.State
}