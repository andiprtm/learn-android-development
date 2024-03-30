package com.example.mygithubapplication.ui.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.mygithubapplication.R
import com.example.mygithubapplication.data.favorite.entity.UserEntity
import com.example.mygithubapplication.data.remote.response.DetailUserResponse
import com.example.mygithubapplication.databinding.ActivityDetailBinding
import com.example.mygithubapplication.ui.detail.adapter.SectionPageAdapter
import com.example.mygithubapplication.ui.helper.application.ViewModelFactory
import com.example.mygithubapplication.util.Render
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailActivityViewModel by viewModels<DetailActivityViewModel> {
        ViewModelFactory.getInstance(applicationContext)
    }
    private val helper = Render()
    private lateinit var dataUser: DetailUserResponse
    private var buttonState: Boolean = false
    private var user: UserEntity? = null

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

        detailActivityViewModel.snackbarText.observe(this) { event ->
            event.getContentIfNotHandled()?.let { snackbarText ->
                val snackbar =
                    Snackbar.make(window.decorView.rootView, snackbarText, Snackbar.LENGTH_SHORT)
                Render().showSnackbar(
                    snackbar.view,
                    snackbarText,
                    ContextCompat.getColor(this, R.color.danger),
                    ContextCompat.getColor(this, R.color.white),
                    binding.fabLike
                )
                snackbar.show()
            }
        }

        detailActivityViewModel.userDetail.observe(this) { user ->
            setDataToView(user)
            dataUser = user

            this.user = UserEntity().apply {
                id = user.id
                login = user.login
                htmlUrl = user.htmlUrl
                avatarUrl = user.avatarUrl
            }

            detailActivityViewModel.getAllFavorites().observe(this) { result ->
                when (result) {
                    is com.example.mygithubapplication.data.Result.Success -> {
                        val favoriteUsers = result.data
                        user?.let { currentUser ->
                            for (data in favoriteUsers) {
                                if (currentUser.id == data.id) {
                                    buttonState = true
                                    binding.fabLike.setImageResource(R.drawable.unlike)
                                }
                            }
                        }
                    }

                    is com.example.mygithubapplication.data.Result.Error -> {
                        Toast.makeText(this, result.error, Toast.LENGTH_SHORT).show()
                    }

                    com.example.mygithubapplication.data.Result.Loading -> {
                        binding.userDetailProgressBar.visibility = View.VISIBLE
                    }
                }
            }

            binding.fabLike.setOnClickListener {
                if (!buttonState) {
                    buttonState = true
                    binding.fabLike.setImageResource(R.drawable.unlike)
                    insertToDatabase(dataUser)
                } else {
                    buttonState = false
                    binding.fabLike.setImageResource(R.drawable.like)
                    detailActivityViewModel.delete(dataUser.id)
                    helper.showSnackbar(
                        binding.root,
                        "User has been deleted from favorite.",
                        ContextCompat.getColor(this, R.color.danger),
                        ContextCompat.getColor(this, R.color.white),
                        binding.fabLike
                    )
                }
            }
        }

        setTabLayoutView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun insertToDatabase(detailList: DetailUserResponse) {
        user?.let { favoriteUser ->
            favoriteUser.id = detailList.id
            favoriteUser.login = detailList.login
            favoriteUser.htmlUrl = detailList.htmlUrl
            favoriteUser.avatarUrl = detailList.avatarUrl
            detailActivityViewModel.insert(favoriteUser)

            helper.showSnackbar(
                binding.root,
                "User has been favorited.",
                ContextCompat.getColor(this, R.color.success),
                ContextCompat.getColor(this, R.color.white),
                binding.fabLike
            )

        } ?: Log.e("DetailActivity", "User is null, cannot insert to database")
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {
                val sharingIntent = Intent(Intent.ACTION_SEND)
                sharingIntent.type = "text/plain"
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Kunjungi Profil Ini Yuk")
                sharingIntent.putExtra(Intent.EXTRA_TEXT, dataUser.htmlUrl)
                startActivity(Intent.createChooser(sharingIntent, "Share using"))
            }
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_USER = "extra_user"
        const val EXTRA_FRAGMENT = "extra_fragment"
    }
}