package com.example.myunittest

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myunittest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mainActivityViewModel = MainActivityViewModel(CuboidModel())

        binding.btnSave.setOnClickListener(this)
        binding.btnCalculateSurfaceArea.setOnClickListener(this)
        binding.btnCalculateCircumference.setOnClickListener(this)
        binding.btnCalculateVolume.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val length = binding.edtLength.text.toString().trim()
        val width = binding.edtWidth.text.toString().trim()
        val height = binding.edtHeight.text.toString().trim()
        when {
            TextUtils.isEmpty(length) -> {
                binding.edtLength.error = "Field ini tidak boleh kosong"
            }

            TextUtils.isEmpty(width) -> {
                binding.edtWidth.error = "Field ini tidak boleh kosong"
            }

            TextUtils.isEmpty(height) -> {
                binding.edtHeight.error = "Field ini tidak boleh kosong"
            }

            else -> {
                val valueLength = length.toDouble()
                val valueWidth = width.toDouble()
                val valueHeight = height.toDouble()
                when (v?.id) {
                    R.id.btn_save -> {
                        mainActivityViewModel.save(valueLength, valueWidth, valueHeight)
                        visible()
                    }

                    R.id.btn_calculate_circumference -> {
                        binding.tvResult.text = mainActivityViewModel.getCircumference().toString()
                        gone()
                    }

                    R.id.btn_calculate_surface_area -> {
                        binding.tvResult.text = mainActivityViewModel.getSurfaceArea().toString()
                        gone()
                    }

                    R.id.btn_calculate_volume -> {
                        binding.tvResult.text = mainActivityViewModel.getVolume().toString()
                        gone()
                    }
                }
            }
        }
    }

    private fun gone() {
        binding.btnCalculateVolume.visibility = View.GONE
        binding.btnCalculateCircumference.visibility = View.GONE
        binding.btnCalculateSurfaceArea.visibility = View.GONE
        binding.btnSave.visibility = View.VISIBLE
    }

    private fun visible() {
        binding.btnCalculateVolume.visibility = View.VISIBLE
        binding.btnCalculateCircumference.visibility = View.VISIBLE
        binding.btnCalculateSurfaceArea.visibility = View.VISIBLE
        binding.btnSave.visibility = View.GONE
    }
}