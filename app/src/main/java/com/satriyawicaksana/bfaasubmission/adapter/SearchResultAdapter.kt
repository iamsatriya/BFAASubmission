package com.satriyawicaksana.bfaasubmission.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.satriyawicaksana.bfaasubmission.databinding.UserSearchResultBinding
import com.satriyawicaksana.bfaasubmission.pojo.ResponseSearch

class SearchResultAdapter(private val listUser: ArrayList<ResponseSearch>): RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder>() {
    private var onItemCliclCallback: OnItemCliclCallback? = null
    inner class SearchResultViewHolder(private val binding: UserSearchResultBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(user: ResponseSearch){
            with(binding){
                civProfileImage.load(user.avatarUrl)
                tvUsername.text = user.login
                itemView.setOnClickListener{onItemCliclCallback?.onItemClicked(user)}
            }
        }
    }

    fun setOnItemClickCallback(onItemCliclCallback: OnItemCliclCallback) {
        this.onItemCliclCallback = onItemCliclCallback
    }

    interface OnItemCliclCallback {
        fun onItemClicked(user: ResponseSearch)
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