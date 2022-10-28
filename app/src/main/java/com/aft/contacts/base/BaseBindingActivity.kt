package com.aft.contacts.base

import android.app.AlertDialog
import android.app.Dialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import androidx.databinding.library.baseAdapters.BR
import com.aft.contacts.R
import com.aft.contacts.base.interfaces.IBase

abstract class BaseBindingActivity<T : ViewDataBinding, V : IBase.ViewModel<*>> : IBase.View<V>,
    BaseNavActivity() {

    lateinit var binding: T
    private var progress: Dialog? = null
    lateinit var context: Context
    private lateinit var localeChangeReceiver: BroadcastReceiver

    var alertDialog: AlertDialog? = null

    @LayoutRes
    abstract fun getLayoutId(): Int
    abstract fun onClick(id: Int)
    abstract fun getBindingVariable(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.CoreTheme)
        super.onCreate(savedInstanceState)
        context = this
        this.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        this.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        progress = createProgressDialog(this)
        registerStateListeners()
        performDataBinding()
        viewModel.clickEvent.observe(this, Observer {
            onClick(it)
        })
        viewModel.state.viewState.observe(this, Observer {
            it?.let {
                when (it) {
                    is String -> {
                        viewModel.state.toast = it
                    }
                    is Boolean -> {
                        viewModel.state.loading = it
                    }
                    else -> {

                    }
                }

            }
        })
    }



    private fun createProgressDialog(context: Context): Dialog {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.progress_dialog)
        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        dialog.setCanceledOnTouchOutside(false)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return dialog
    }

    private fun performDataBinding() {
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.setVariable(getBindingVariable(), viewModel)
        binding.executePendingBindings()
    }

    //fun hideKeyboard() = Utils.hideKeyboard(this.currentFocus)
    fun showToast(msg: String) {
        if (msg.isNotBlank()) {
            this.toast(msg)
        }
    }

    fun showDialog(msg: String) {
        if (msg.isNotBlank()) {
            this.displayMessageDialog(message = msg)
        }
    }



    fun displayMessageDialog(title: String? = "", message: String?) {
        try {
            val dialog = MaterialAlertDialogBuilder(this)
            dialog.setTitle(title)
            dialog.setMessage(message)
            dialog.setCancelable(false)
            dialog.setPositiveButton(getString(R.string.ok))
            { _, _ -> }
            dialog.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun showLoader(isVisible: Boolean) {
        if (isVisible) progress?.show() else progress?.dismiss()
    }

    fun launch(dispatcher: Dispatcher = Dispatcher.Main, block: suspend () -> Unit): Job {
        return lifecycleScope.launch(
            when (dispatcher) {
                Dispatcher.Main -> Dispatchers.Main
                Dispatcher.Background -> Dispatchers.IO
                Dispatcher.LongOperation -> Dispatchers.Default
            }
        ) { block() }
    }

    override fun onDestroy() {
        progress?.dismiss()
        unregisterStateListeners()
        unregisterReceiver(localeChangeReceiver)
        viewModel.clickEvent.removeObservers(this)
        viewModel.state.viewState.removeObservers(this)
        if (alertDialog != null) {
            if (alertDialog!!.isShowing) {
                alertDialog!!.cancel()
            }
        }
        super.onDestroy()
    }

    private val stateObserver = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            if (propertyId == BR.toast && viewModel.state.toast.isNotBlank()) {
                showDialog(viewModel.state.toast)
            }
            if (propertyId == BR.loading) {
                showLoader(viewModel.state.loading)
            }
        }
    }

    private fun registerStateListeners() {
        if (viewModel is BaseViewModel<*>) {
            viewModel.registerLifecycleOwner(this)
        }
        if (viewModel.state is BaseState) {
            (viewModel.state as BaseState).addOnPropertyChangedCallback(stateObserver)
        }
    }

    private fun unregisterStateListeners() {
        if (viewModel is BaseViewModel<*>) {
            viewModel.unregisterLifecycleOwner(this)
        }
        if (viewModel.state is BaseState) {
            (viewModel.state as BaseState).removeOnPropertyChangedCallback(stateObserver)
        }
    }


    fun setTransitionAnimationDuration() {
        window.sharedElementEnterTransition.duration = 200
        window.sharedElementReturnTransition.setDuration(200).interpolator =
            DecelerateInterpolator()
    }
    fun checkContactPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.READ_CONTACTS
        ) == PackageManager.PERMISSION_GRANTED
    }
}
