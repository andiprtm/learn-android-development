package com.example.mygithubapplication.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubapplication.R
import com.example.mygithubapplication.data.preference.SettingPreferences
import com.example.mygithubapplication.data.preference.dataStore
import com.example.mygithubapplication.data.remote.response.ItemsItem
import com.example.mygithubapplication.databinding.ActivityMainBinding
import com.example.mygithubapplication.ui.detail.DetailActivity
import com.example.mygithubapplication.ui.favorite.FavoriteUserActivity
import com.example.mygithubapplication.ui.helper.preference.SettingsViewModelFactory
import com.example.mygithubapplication.ui.home.adapter.MainActivityAdapter
import com.example.mygithubapplication.ui.setting.SettingActivity
import com.example.mygithubapplication.util.Render

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val helper = Render()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pref = SettingPreferences.getInstance(application.dataStore)
        val mainActivityViewModel = ViewModelProvider(
            this,
            SettingsViewModelFactory(pref)
        )[MainActivityViewModel::class.java]

        mainActivityViewModel.getThemeSettings().observe(this) {
            if (it) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

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

        setSupportActionBar(binding.mainToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setIcon(R.drawable.app_icon_day)

        mainActivityViewModel.listGithubUser.observe(this) { listGithubUser ->
            setUserData(listGithubUser)
        }

        mainActivityViewModel.isLoading.observe(this) {
            binding.let { view ->
                helper.showLoading(it, view.mainProgressBar)
            }
        }

        mainActivityViewModel.snackbarText.observe(this) { event ->
            event.getContentIfNotHandled()?.let { snackbarText ->
                Render().showSnackbar(
                    binding.root,
                    snackbarText,
                    ContextCompat.getColor(this, R.color.danger),
                    ContextCompat.getColor(this, R.color.white)
                )
            }
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_setting -> {
                val intent = Intent(this@MainActivity, SettingActivity::class.java)
                startActivity(intent)
            }

            R.id.action_favorite -> {
                val intent = Intent(this@MainActivity, FavoriteUserActivity::class.java)
                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
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