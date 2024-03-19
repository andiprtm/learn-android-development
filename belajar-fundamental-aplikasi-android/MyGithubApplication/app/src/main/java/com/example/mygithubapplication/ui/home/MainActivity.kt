package com.example.mygithubapplication.ui.home

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
import com.example.mygithubapplication.data.response.ItemsItem
import com.example.mygithubapplication.databinding.ActivityMainBinding
import com.example.mygithubapplication.ui.home.adapter.MainActivityAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainActivityViewModel by viewModels<MainActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mainActivityViewModel.listGithubUser.observe(this) { listGithubUser ->
            setUserData(listGithubUser)
        }

        mainActivityViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        val layoutManager = LinearLayoutManager(this)
        binding.rvMain.layoutManager = layoutManager
    }

    private fun setUserData(listGithubUser: List<ItemsItem>) {
        val adapter = MainActivityAdapter { item ->
            Toast.makeText(this, "Item ${item.login} clicked", Toast.LENGTH_SHORT).show()
        }
        binding.rvMain.adapter = adapter
        adapter.submitList(listGithubUser)
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.mainProgressBar.visibility = View.VISIBLE
        } else {
            binding.mainProgressBar.visibility = View.INVISIBLE
        }
    }
}