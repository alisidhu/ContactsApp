package com.aft.contacts.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.*
import com.aft.contacts.R


abstract class BaseNavActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        setTheme(R.style.CoreTheme)
        super.onCreate(savedInstanceState, persistentState)
    }

    protected fun navigate(
        @IdRes srcNavViewId: Int,
        destinationId: Int,
        args: Bundle? = null,
        navOptions: NavOptions? = navOptions {

        }
    ) {

        findNavController(srcNavViewId).navigate(destinationId, args, navOptions)
    }

    protected fun navigate(
        @IdRes srcNavViewId: Int,
        navDirection: NavDirections,
        navOptions: NavOptions? = navOptions {

        }
    ) {

        findNavController(srcNavViewId).navigate(navDirection, navOptions)
    }

    protected fun navigateBack(
        @IdRes srcNavViewId: Int,
        destinationId: Int = -1, inclusive: Boolean = false
    ) {
        if (destinationId != -1) {
            findNavController(srcNavViewId).popBackStack(destinationId, inclusive)
        } else {
            findNavController(srcNavViewId).popBackStack()
        }
    }

    private fun anim(animBuilder: AnimBuilder.() -> Unit): AnimBuilder =
        AnimBuilder().apply(animBuilder)
}