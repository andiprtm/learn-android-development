package com.example.mygithubapplication.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubapplication.R
import com.example.mygithubapplication.data.favorite.entity.UserEntity
import com.example.mygithubapplication.databinding.ActivityFavoriteUserBinding
import com.example.mygithubapplication.ui.detail.DetailActivity
import com.example.mygithubapplication.ui.favorite.adapter.FavoriteUserAdapter
import com.example.mygithubapplication.ui.helper.application.ViewModelFactory

class FavoriteUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFavoriteUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.rvFavorites.layoutManager = LinearLayoutManager(this)
        binding.rvFavorites.setHasFixedSize(false)

        setSupportActionBar(binding.favoriteToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.title = "Favorite User"

        val favoriteUserActivityViewModel: FavoriteUserActivityViewModel by viewModels {
            ViewModelFactory.getInstance(
                application
            )
        }

        favoriteUserActivityViewModel.getAllFavorites().observe(this) { result ->

            if (result != null) {
                when (result) {

                    is com.example.mygithubapplication.data.Result.Error -> {
                        binding.favoriteProgressBar.visibility = View.GONE
                        Toast.makeText(
                            application,
                            "Terjadi kesalahan" + result.error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    com.example.mygithubapplication.data.Result.Loading -> {
                        binding.favoriteProgressBar.visibility = View.VISIBLE
                    }

                    is com.example.mygithubapplication.data.Result.Success -> {
                        binding.favoriteProgressBar.visibility = View.GONE
                        val adapter = FavoriteUserAdapter { item ->
                            showSelectedUser(item)
                        }
                        binding.rvFavorites.adapter = adapter
                        adapter.submitList(result.data)

                        if (result.data.isEmpty()) {
                            binding.tvNoData.visibility = View.VISIBLE
                        } else {
                            binding.tvNoData.visibility = View.INVISIBLE
                        }
                    }
                }
            }
        }

    }

    private fun showSelectedUser(item: UserEntity) {
        val intent = Intent(this@FavoriteUserActivity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_USER, item.login)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}