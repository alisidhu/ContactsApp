package com.aft.contacts.ui.main

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.library.baseAdapters.BR
import androidx.navigation.fragment.NavHostFragment
import com.aft.contacts.R
import com.aft.contacts.base.BaseBindingActivity
import com.aft.contacts.base.interfaces.IFragmentHolder
import com.aft.contacts.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :
    BaseBindingActivity<ActivityMainBinding, IMain.ViewModel>(), IMain.View,
    IFragmentHolder {
    override fun getLayoutId(): Int = R.layout.activity_main
    override fun getBindingVariable(): Int = BR.viewModel
    private val mainViewModel: MainViewModel by viewModels()
    override val viewModel: IMain.ViewModel
        get() = mainViewModel


    override fun onClick(id: Int) {
        when (id) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBottomNavigationWithNavController()
        if (!checkContactPermission()) {
            requestContactPermission()
        }
    }

    /***
     * Setting bottom nav with nav controller,
     * For nav_graph and navigation ids must be same to navigate fragments properly
     */
    private fun setupBottomNavigationWithNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fcvNavigationHost) as NavHostFragment
        val navController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
        // Setting navigation start destination on The basis of Offers.
        // if user has offers for its Current Location then start destination will be offers screen,
        // otherwise map screen
        navGraph.setStartDestination(R.id.contactsFragment)
        //The bellow line will be only use if we didn't assign app:navGraph="@navigation/nav_graph" to FragmentContainerView in activity_main.xml
        navController.graph = navGraph


    }



    private fun requestContactPermission() {
        val permission = arrayOf(android.Manifest.permission.READ_CONTACTS)
        ActivityCompat.requestPermissions(this, permission, 0)
    }


}