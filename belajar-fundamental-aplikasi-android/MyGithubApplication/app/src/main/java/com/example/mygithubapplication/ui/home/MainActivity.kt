package com.example.mygithubapplication.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubapplication.R
import com.example.mygithubapplication.data.response.ItemsItem
import com.example.mygithubapplication.databinding.ActivityMainBinding
import com.example.mygithubapplication.ui.detail.DetailActivity
import com.example.mygithubapplication.ui.home.adapter.MainActivityAdapter
import com.example.mygithubapplication.util.Render
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainActivityViewModel by viewModels<MainActivityViewModel>()
    private val helper = Render()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val layoutManager = LinearLayoutManager(this)
        binding.rvMain.layoutManager = layoutManager

        mainActivityViewModel.listGithubUser.observe(this) { listGithubUser ->
            setUserData(listGithubUser)
        }

        mainActivityViewModel.isLoading.observe(this) {
            binding.let { view ->
                helper.showLoading(it, view.mainProgressBar)
            }
        }

        mainActivityViewModel.snackbarText.observe(this) {
            val snackbar = it.getContentIfNotHandled()?.let { snackbarText ->
                Snackbar.make(window.decorView.rootView, snackbarText, Snackbar.LENGTH_SHORT)
            }
            val color = ContextCompat.getColor(this, R.color.danger)
            snackbar?.setBackgroundTint(color)
            snackbar?.show()
        }

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { _, _, _ ->
                    searchBar.setText(searchView.text)
                    mainActivityViewModel.findUser(searchView.text.toString())
                    searchView.hide()
                    false
                }
        }
    }

    private fun setUserData(listGithubUser: List<ItemsItem>) {
        val adapter = MainActivityAdapter { item ->
            showSelectedUser(item)
        }
        binding.rvMain.adapter = adapter
        adapter.submitList(listGithubUser)
    }

    private fun showSelectedUser(data: ItemsItem) {
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_USER, data.login)
        startActivity(intent)
    }

}