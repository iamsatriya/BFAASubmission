package com.satriyawicaksana.bfaasubmission.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.satriyawicaksana.bfaasubmission.databinding.UserSearchResultBinding
import com.satriyawicaksana.bfaasubmission.pojo.ItemsItem

class SearchResultAdapter(private val listUser: ArrayList<ItemsItem>): RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null
    inner class SearchResultViewHolder(private val binding: UserSearchResultBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(user: ItemsItem){
            with(binding){
                civProfileImage.load(user.avatarUrl)
                tvUsername.text = user.login
                itemView.setOnClickListener{onItemClickCallback?.onItemClicked(user)}
            }
        }
    }

    fun setOnItemClickCallback(onItemCliclCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemCliclCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(user: ItemsItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val binding = UserSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bind(listUser[position])
    }

    override fun getItemCount(): Int = listUser.size
}