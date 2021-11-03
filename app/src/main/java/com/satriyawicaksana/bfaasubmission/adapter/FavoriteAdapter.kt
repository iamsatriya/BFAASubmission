package com.satriyawicaksana.bfaasubmission.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.satriyawicaksana.bfaasubmission.database.Favorite
import com.satriyawicaksana.bfaasubmission.databinding.UserSearchResultBinding
import com.satriyawicaksana.bfaasubmission.utils.FavoriteDiffCallback

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    private val listFavorites = ArrayList<Favorite>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setListFavorites(listFavorites: List<Favorite>){
        val diffCallback = FavoriteDiffCallback(this.listFavorites, listFavorites)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listFavorites.clear()
        this.listFavorites.addAll(listFavorites)
        diffResult.dispatchUpdatesTo(this)
    }
    inner class FavoriteViewHolder(private val binding: UserSearchResultBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(favorite: Favorite){
            with(binding){
                civProfileImage.load(favorite.avatarUrl)
                tvUsername.text = favorite.login
                itemView.setOnClickListener{onItemClickCallback?.onItemClicked(favorite)}
            }
        }
    }

    fun setOnItemClickCallback(onItemCliclCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemCliclCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(favorite: Favorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = UserSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(listFavorites[position])
    }

    override fun getItemCount(): Int = listFavorites.size
}