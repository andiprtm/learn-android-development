package com.example.mygithubapplication.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.mygithubapplication.R
import com.example.mygithubapplication.data.response.DetailUserResponse
import com.example.mygithubapplication.databinding.ActivityDetailBinding
import com.example.mygithubapplication.ui.detail.adapter.SectionPageAdapter
import com.example.mygithubapplication.util.Render
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailActivityViewModel by viewModels<DetailActivityViewModel>()
    private val helper = Render()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val username = intent.getStringExtra(EXTRA_USER)
        if (username != null) {
            detailActivityViewModel.getDetailUserData(username)
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        detailActivityViewModel.isLoading.observe(this) {
            binding.let { view ->
                helper.showLoading(it, view.userDetailProgressBar)
            }
        }

        detailActivityViewModel.status.observe(this) { status ->
            status.let {
                Toast.makeText(this, status.toString(), Toast.LENGTH_SHORT).show()
            }
        }

        detailActivityViewModel.userDetail.observe(this) { user ->
            setDataToView(user)
        }

        setTabLayoutView()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setTabLayoutView() {
        val userIntent = intent.extras

        if (userIntent != null) {
            val username = userIntent.getString(EXTRA_USER)

            if (username != null) {
                val data = Bundle()
                data.putString(EXTRA_FRAGMENT, username)

                val sectionPagerAdapter = SectionPageAdapter(this, data)
                val viewPager = binding.viewPager

                viewPager.adapter = sectionPagerAdapter

                val tabs = binding.tabs
                val tabTitle = resources.getStringArray(R.array.tab_title)

                TabLayoutMediator(tabs, viewPager) { tab, position ->
                    tab.text = tabTitle[position]
                }.attach()
            }
        }
    }

    private fun setDataToView(user: DetailUserResponse) {
        binding.apply {
            Glide.with(root.context)
                .load(user.avatarUrl)
                .into(userDetailAvatar)
            detailsTvName.text = user.name ?: resources.getString(R.string.noname)
            tvDetailUsername.text = user.login
            detailsTvBio.text = user.bio ?: resources.getString(R.string.nobio)
            detailsTvFollower.text = resources.getString(R.string.follower, user.followers)
            detailsTvFollowing.text = resources.getString(R.string.following, user.following)
            detailsTvGist.text = resources.getString(R.string.gist, user.publicGists)
            detailsTvRepository.text = resources.getString(R.string.repository, user.publicRepos)
            tvCompany.text = user.company ?: resources.getString(R.string.nocompany)
            tvLocation.text = user.location ?: resources.getString(R.string.nolocation)
        }
    }

    companion object {
        const val EXTRA_USER = "extra_user"
        const val EXTRA_FRAGMENT = "extra_fragment"
    }
}