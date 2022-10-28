package com.aft.contacts.ui.main

import com.aft.contacts.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel  @Inject constructor() : BaseViewModel<IMain.State>(), IMain.ViewModel {
    override val state: IMain.State = MainState()
}