package com.aft.contacts.base.interfaces

import androidx.navigation.NavController

interface IBaseNavigator {
    /**
     * get the id of navigation host fragment
     */
    val navHostId: Int
        get() = 0
    var navController: NavController
}