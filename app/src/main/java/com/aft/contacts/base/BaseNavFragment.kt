package com.aft.contacts.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.AnimBuilder
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.aft.contacts.R

abstract class BaseNavFragment(contentLayoutId: Int) : Fragment(contentLayoutId) {


    override fun onCreate(savedInstanceState: Bundle?) {
        requireActivity().setTheme(R.style.CoreTheme)
        super.onCreate(savedInstanceState)
    }
    protected fun navigate(
        destinationId: Int,
        args: Bundle? = null,
        navOptions: NavOptions? = navOptions {

        }
    ) {

        findNavController().navigate(destinationId, args, navOptions)
    }

    protected fun navigate(
        navDirection: NavDirections,
        navOptions: NavOptions? = navOptions {

        }
    ) {

        findNavController().navigate(navDirection, navOptions)
    }


    protected fun navigateBack(destinationId: Int = -1, inclusive: Boolean = false) {
        if (destinationId != -1) {
            findNavController().popBackStack(destinationId, inclusive)
        } else {
            findNavController().popBackStack()
        }
    }

    private fun anim(animBuilder: AnimBuilder.() -> Unit): AnimBuilder =
        AnimBuilder().apply(animBuilder)
}