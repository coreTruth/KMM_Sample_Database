package com.example.kmmapplication.android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kmmapplication.android.databinding.ItemViewUserBinding
import com.example.kmmapplication.db.User

class UserListAdapter: ListAdapter<User, UserListAdapter.UserViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemViewUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
        holder.bind(getItem(position))

    class UserViewHolder(private val binding: ItemViewUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User){
            binding.textUserInfo.text = String.format("%d %s", user.id, user.name)
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: User, newItem: User) =
            (oldItem.name == newItem.name)
    }
}