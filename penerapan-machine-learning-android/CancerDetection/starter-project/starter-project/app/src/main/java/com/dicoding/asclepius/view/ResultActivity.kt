package com.dicoding.asclepius.view

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.asclepius.R
import com.dicoding.asclepius.data.local.entity.HistoryEntity
import com.dicoding.asclepius.databinding.ActivityResultBinding
import com.dicoding.asclepius.helper.ViewModelFactory
import com.dicoding.asclepius.view.history.HistoryActivityViewModel

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private var history: HistoryEntity? = null
    private val historyActivityViewModel by viewModels<HistoryActivityViewModel> {
        ViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.resultToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.title = "Detection Result"

        val randomID = 0
        val imageUri = Uri.parse(intent.getStringExtra(EXTRA_IMAGE_URI))
        val confidenceScore = intent.getStringExtra(EXTRA_RESULT) ?: "Not Predicted"

        history = HistoryEntity().apply {
            id = randomID
            classifications = confidenceScore
            uri = imageUri.toString()
        }

        insertToDatabase(randomID, confidenceScore, imageUri.toString())

        imageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.resultImage.setImageURI(it)
        }

        if (confidenceScore != "Not Predicted") {
            binding.resultText.text = confidenceScore
        } else {
            binding.cvResult.setCardBackgroundColor(resources.getColor(R.color.danger))
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun insertToDatabase(id: Int, classification: String, imageUri: String) {
        history.let { historyEntity ->
            historyEntity?.id = id
            historyEntity?.uri = imageUri
            historyEntity?.classifications = classification
            historyEntity?.let { historyActivityViewModel.insert(it) }
        } ?: Log.e("DetailActivity", "User is null, cannot insert to database")
    }


    companion object {
        const val EXTRA_IMAGE_URI = "extra_image_uri"
        const val EXTRA_RESULT = "extra_result"
    }


}