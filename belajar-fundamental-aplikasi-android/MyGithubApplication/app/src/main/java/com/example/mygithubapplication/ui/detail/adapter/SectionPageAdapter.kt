package com.example.mygithubapplication.ui.detail.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mygithubapplication.ui.detail.follow.followers.FollowersFragment
import com.example.mygithubapplication.ui.detail.follow.following.FollowingFragment

class SectionPageAdapter(activity: AppCompatActivity, private val data: Bundle) :
    FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null

        when (position) {
            0 -> fragment = FollowersFragment()
            1 -> fragment = FollowingFragment()
        }

        fragment?.arguments = data
        return fragment as Fragment
    }
}