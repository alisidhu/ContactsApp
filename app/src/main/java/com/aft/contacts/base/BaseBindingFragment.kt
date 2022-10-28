package com.aft.contacts.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.aft.contacts.R
import com.aft.contacts.base.interfaces.IBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseBindingFragment<T : ViewDataBinding, V : IBase.ViewModel<*>>(@LayoutRes val contentLayoutId: Int) :
    IBase.View<V>, BaseNavFragment(contentLayoutId),
    OnBackPressedListener {

    lateinit var binding: T
    private var progress: Dialog? = null
    abstract fun getBindingVariable(): Int
    abstract fun onClick(id: Int)
    override fun onCreate(savedInstanceState: Bundle?) {
        requireActivity().setTheme(R.style.CoreTheme)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, contentLayoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerStateListeners()

        binding.setVariable(getBindingVariable(), viewModel)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()

        viewModel.clickEvent.observe(viewLifecycleOwner, Observer {
            onClick(it)
        })
        viewModel.state.viewState.observe(viewLifecycleOwner, Observer {
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

    private val stateObserver = object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
            if (propertyId == BR.toast && viewModel.state.toast.isNotBlank()) {
                if (activity is BaseBindingActivity<*, *>)
                    (activity as BaseBindingActivity<*, *>).showDialog(viewModel.state.toast)
            }
            if (propertyId == BR.loading) {
                showLoading(viewModel.state.loading)
            }
        }
    }

    fun showDialogMessage(msg: String) {
        if (activity is BaseBindingActivity<*, *>)
            (activity as BaseBindingActivity<*, *>).displayMessageDialog(message = msg)
    }

    /* fun changeStatusBarColor(colorId: Int, systemUILight: Boolean? = true) {
         requireActivity().coloredStatusBarMode(
             ContextCompat.getColor(
                 requireContext(),
                 colorId
             ), systemUILight
         )
     }*/

    override fun onDestroyView() {
        unregisterStateListeners()
        progress?.dismiss()
        viewModel.clickEvent.removeObservers(this)
        viewModel.state.viewState.removeObservers(this)
        super.onDestroyView()
    }

    private fun showLoading(isVisible: Boolean) {
        if (activity is BaseBindingActivity<*, *>)
            (activity as BaseBindingActivity<*, *>).showLoader(isVisible)
    }

    fun launch(dispatcher: Dispatcher = Dispatcher.Main, block: suspend () -> Unit) {
        lifecycleScope.launch(
            when (dispatcher) {
                Dispatcher.Main -> Dispatchers.Main
                Dispatcher.Background -> Dispatchers.IO
                Dispatcher.LongOperation -> Dispatchers.Default
            }
        ) { block() }
    }

    override fun onBackPressed(): Boolean = false
    fun removeFocus() {
        requireActivity().window
            .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
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




    fun navigateScreenForResult(
        navigateToID: Int,
        requestKey: String,
        bundle: Bundle = Bundle(),
        listener: ((key: String, bundle: Bundle) -> Unit)
    ) {
        // Step 1. Listen for fragment results
        setFragmentResultListener(requestKey) { key, bundle1 ->
            // read from the bundle
            listener.invoke(key, bundle1)
        }
        // Step 2. Navigate to FragmentB
        findNavController().navigate(navigateToID,bundle)
    }

    fun navigateToPreviousScreenWithResult(requestKey: String, bundle: Bundle) {
        // Step 3. Set a result
        setFragmentResult(requestKey, bundle)
        // Step 4. Go back to Fragment A
        findNavController().navigateUp()
    }


}

interface OnBackPressedListener {
    fun onBackPressed(): Boolean
}
