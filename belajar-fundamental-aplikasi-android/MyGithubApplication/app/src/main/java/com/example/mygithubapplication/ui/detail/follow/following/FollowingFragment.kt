package com.example.mygithubapplication.ui.detail.follow.following

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubapplication.R
import com.example.mygithubapplication.data.response.ItemsItem
import com.example.mygithubapplication.databinding.FragmentFollowBinding
import com.example.mygithubapplication.ui.detail.DetailActivity
import com.example.mygithubapplication.ui.detail.follow.FollowAdapter
import com.example.mygithubapplication.util.Render
import com.google.android.material.snackbar.Snackbar

class FollowingFragment : Fragment() {

    private var binding: FragmentFollowBinding? = null
    private val followingFragmentViewModel by viewModels<FollowingFragmentViewModel>()
    private val helper = Render()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFollowBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        followingFragmentViewModel.isLoading.observe(viewLifecycleOwner) {
            binding?.let { view ->
                helper.showLoading(it, view.progressBar3)
            }
        }

        followingFragmentViewModel.status.observe(viewLifecycleOwner) { status ->
            status?.let {
                Toast.makeText(activity, status.toString(), Toast.LENGTH_SHORT).show()
            }
        }

        followingFragmentViewModel.getFollowing(
            arguments?.getString(DetailActivity.EXTRA_FRAGMENT).toString()
        )

        followingFragmentViewModel.listFollowing.observe(viewLifecycleOwner) { listFollowing ->
            setDataToFragment(listFollowing)
        }
    }

    private fun setDataToFragment(listFollowing: List<ItemsItem>) {
        val adapter = FollowAdapter{ item ->
            navigateToDetailActivity(item.login)
        }
        binding?.apply {
            rvFollower.layoutManager = LinearLayoutManager(context)
            rvFollower.adapter = adapter
        }
        adapter.submitList(listFollowing)

        if (listFollowing.isEmpty()) {
            binding?.tvNoData?.visibility = View.VISIBLE
        } else {
            binding?.tvNoData?.visibility = View.INVISIBLE
        }
    }

    private fun navigateToDetailActivity(userLogin: String) {
        val intent = Intent(requireActivity(), DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_USER, userLogin)
        }
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}