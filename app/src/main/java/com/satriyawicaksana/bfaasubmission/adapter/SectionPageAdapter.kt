package com.satriyawicaksana.bfaasubmission.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.satriyawicaksana.bfaasubmission.ui.follower.FollowerFragment
import com.satriyawicaksana.bfaasubmission.ui.following.FollowingFragment

class SectionPageAdapter(
    appCompatActivity: AppCompatActivity
) : FragmentStateAdapter(appCompatActivity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> {
                val followerFragment = FollowerFragment()
                fragment = followerFragment
            }
            1 -> {
                val followingFragment = FollowingFragment()
                fragment = followingFragment
            }
        }
        return fragment as Fragment
    }
}