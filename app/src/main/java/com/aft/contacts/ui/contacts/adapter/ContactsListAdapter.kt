package com.aft.contacts.ui.contacts.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.aft.contacts.R
import com.aft.contacts.base.BaseBindingRecyclerAdapter
import com.aft.contacts.base.interfaces.OnItemClickListener
import com.aft.contacts.databinding.LayoutItemContactBinding
import com.aft.contacts.models.ContactItemModel
import com.aft.contacts.models.ContactsModel

class ContactsListAdapter(
    private val list: MutableList<ContactsModel>,
) : BaseBindingRecyclerAdapter<ContactsModel, ContactsListAdapter.ContactsItemViewHolder>(list) {
    override fun onCreateViewHolder(binding: ViewDataBinding): ContactsItemViewHolder {
        return ContactsItemViewHolder(binding as LayoutItemContactBinding)
    }

    override fun getLayoutIdForViewType(viewType: Int): Int = R.layout.layout_item_contact

    override fun onBindViewHolder(
        holder: ContactsItemViewHolder,
        position: Int
    ) {
        super.onBindViewHolder(holder, position)
        holder.onBind(list[position], position, onItemClickListener)
    }

    inner class ContactsItemViewHolder(private val binding: LayoutItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(
            data: ContactsModel,
            position: Int,
            onItemClickListener: OnItemClickListener?
        ) {
            binding.viewModel =
                ContactItemModel(
                    data,
                    position,
                    onItemClickListener
                )
            binding.executePendingBindings()
        }

    }

}