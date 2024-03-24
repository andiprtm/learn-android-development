package com.example.mysharedpreferences

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mysharedpreference.UserPreference
import com.example.mysharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mainUserPreference: UserPreference
    private var isPreferenceEmpty = false

    private lateinit var userModel: UserModel
    private lateinit var binding: ActivityMainBinding

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.data != null && result.resultCode == FormUserPreferenceActivity.RESULT_CODE) {
                userModel =
                    result.data?.getParcelableExtra<UserModel>(FormUserPreferenceActivity.EXTRA_RESULT) as UserModel
                populatedView(userModel)
                checkForm(userModel)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportActionBar?.title = "My User Preference"

        mainUserPreference = UserPreference(this)

        showExistingPreference()

        binding.btnSave.setOnClickListener(this)
    }

    private fun showExistingPreference() {
        userModel = mainUserPreference.getUser()
        populatedView(userModel)
        checkForm(userModel)
    }

    private fun checkForm(userModel: UserModel) {
        when {
            userModel.toString().isNotEmpty() -> {
                binding.btnSave.text = getString(R.string.change)
                isPreferenceEmpty = false
            }

            else -> {
                binding.btnSave.text = getString(R.string.save)
                isPreferenceEmpty = true
            }
        }
    }

    private fun populatedView(userModel: UserModel) {
        binding.tvName.text =
            if (userModel.name.toString().isEmpty()) "Tidak Ada" else userModel.name
        binding.tvAge.text =
            if (userModel.age.toString().isEmpty()) "Tidak Ada" else userModel.age.toString()
        binding.tvIsLoveMu.text = if (userModel.isLove) "Ya" else "Tidak"
        binding.tvEmail.text =
            if (userModel.email.toString().isEmpty()) "Tidak Ada" else userModel.email
        binding.tvPhone.text =
            if (userModel.phoneNumber.toString().isEmpty()) "Tidak Ada" else userModel.phoneNumber
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_save) {
            val intent = Intent(this, FormUserPreferenceActivity::class.java)

            when {
                isPreferenceEmpty -> {
                    intent.putExtra(
                        FormUserPreferenceActivity.EXTRA_TYPE_FORM,
                        FormUserPreferenceActivity.TYPE_ADD
                    )

                    intent.putExtra("USER", userModel)
                }

                else -> {
                    intent.putExtra(
                        FormUserPreferenceActivity.EXTRA_TYPE_FORM,
                        FormUserPreferenceActivity.TYPE_EDIT
                    )

                    intent.putExtra("USER", userModel)
                }
            }
            resultLauncher.launch(intent)
        }
    }
}