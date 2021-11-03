package com.satriyawicaksana.bfaasubmission.utils

import androidx.recyclerview.widget.DiffUtil
import com.satriyawicaksana.bfaasubmission.database.Favorite

class FavoriteDiffCallback(
    private val mOldFavorite: List<Favorite>,
    private val mNewFavorite: List<Favorite>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = mOldFavorite.size

    override fun getNewListSize(): Int = mNewFavorite.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldFavorite[oldItemPosition].login == mNewFavorite[newItemPosition].login
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldFavorite = mOldFavorite[oldItemPosition]
        val newFavorite = mNewFavorite[newItemPosition]
        return oldFavorite.login == newFavorite.login && oldFavorite.avatarUrl == newFavorite.avatarUrl
    }
}