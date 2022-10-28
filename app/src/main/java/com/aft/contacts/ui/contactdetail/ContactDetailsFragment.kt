package com.aft.contacts.ui.contactdetail

import android.os.Bundle
import android.view.View
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.viewModels
import com.aft.contacts.R
import com.aft.contacts.base.BaseBindingFragment
import com.aft.contacts.databinding.FragmentContactDetailBinding
import com.aft.contacts.models.ContactsModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactDetailsFragment :
    BaseBindingFragment<FragmentContactDetailBinding, IContactDetail.ViewModel>(R.layout.fragment_contact_detail),
    IContactDetail.View {
    override val viewModel: ContactsDetailViewModel by viewModels()
    override fun getBindingVariable(): Int = BR.viewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getArgumentsFragment()
    }

    private fun getArgumentsFragment() {
        arguments?.let { bundle ->
            bundle.getParcelable<ContactsModel>(
                ContactsModel::class.java.name
            )?.let {
                viewModel.state.contactDetail.set(it)
            }
        }
    }

    override fun onClick(id: Int) {}
}