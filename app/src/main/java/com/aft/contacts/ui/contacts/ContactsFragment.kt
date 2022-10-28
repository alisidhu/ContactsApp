package com.aft.contacts.ui.contacts

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aft.contacts.R
import com.aft.contacts.base.BaseBindingFragment
import com.aft.contacts.base.interfaces.OnItemClickListener
import com.aft.contacts.databinding.FragmentContactsBinding
import com.aft.contacts.models.ContactsModel
import com.aft.contacts.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ContactsFragment :
    BaseBindingFragment<FragmentContactsBinding, IContacts.ViewModel>(R.layout.fragment_contacts),
    IContacts.View, OnItemClickListener {
    override val viewModel: ContactsViewModel by viewModels()
    override fun getBindingVariable(): Int = BR.viewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if ((activity as MainActivity).checkContactPermission()) {
            viewModel.getContactsList {
                binding.rvContact.adapter = viewModel.contactsListAdapter
                viewModel.contactsListAdapter.onItemClickListener = this
            }
        }
    }

    override fun onItemClick(view: View, data: Any, pos: Int) {
        if (data is ContactsModel) {
            findNavController().navigate(
                R.id.action_contactsFragment_to_contactDetailFragment,
                bundleOf(ContactsModel::class.java.name to data)
            )

        }
    }

    override fun onClick(id: Int) {
    }


}