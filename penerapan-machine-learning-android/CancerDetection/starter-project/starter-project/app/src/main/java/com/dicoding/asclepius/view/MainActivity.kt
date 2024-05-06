package com.dicoding.asclepius.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.dicoding.asclepius.R
import com.dicoding.asclepius.databinding.ActivityMainBinding
import com.dicoding.asclepius.helper.ImageClassifierHelper
import com.dicoding.asclepius.utils.Render
import com.dicoding.asclepius.view.history.HistoryActivity
import com.dicoding.asclepius.view.news.NewsActivity
import com.yalantis.ucrop.UCrop
import org.tensorflow.lite.task.vision.classifier.Classifications
import java.io.File
import java.text.NumberFormat
import java.util.UUID
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var imageClassifierHelper: ImageClassifierHelper

    private var currentImageUri: Uri? = null

    private lateinit var sourceUri: Uri
    private lateinit var destinationUri: Uri

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    private val helper = Render()

    private val uCropContract = object : ActivityResultContract<List<Uri>, Uri>() {
        override fun createIntent(context: Context, input: List<Uri>): Intent {
            val inputUri = input[0]
            val outputUri = input[1]

            val uCropOptions = UCrop.Options()

            val uCrop = UCrop.of(inputUri, outputUri)
                .withOptions(uCropOptions)
                .withMaxResultSize(2000, 2000)

            return uCrop.getIntent(context)
        }

        override fun parseResult(resultCode: Int, intent: Intent?): Uri {
            return intent?.let { UCrop.getOutput(it) } ?: run {
                binding.progressIndicator.visibility = View.GONE
                val resultIntent = Intent().apply {
                    putExtra("CLOSE_UCROP_ACIVITY", true)
                }
                setResult(Activity.RESULT_CANCELED, resultIntent)
                finishActivity(UCrop.REQUEST_CROP)
                return@run Uri.EMPTY
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.mainToolbar)
        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.primary_brown)))
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setIcon(R.drawable.logo_white)

        binding.galleryButton.setOnClickListener { startGallery() }
        binding.analyzeButton.setOnClickListener { analyzeImage() }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_history -> {
                val intent = Intent(this@MainActivity, HistoryActivity::class.java)
                startActivity(intent)
            }

            R.id.action_news -> {
                val intent = Intent(this@MainActivity, NewsActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            binding.progressIndicator.visibility = View.VISIBLE
            sourceUri = uri
            destinationUri = File(filesDir, "${UUID.randomUUID()}.jpg").toUri()

            val listUri = listOf(sourceUri, destinationUri)
            cropImage.launch(listUri)
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }

    private val cropImage = registerForActivityResult(uCropContract) { uri ->
        if (uri != null && uri != Uri.EMPTY) {
            binding.progressIndicator.visibility = View.GONE
            currentImageUri = uri
            Log.d("URI", currentImageUri.toString())
            showImage()
        } else {
            Log.d("CROPING IMAGE", "Media Cancelled")
            binding.progressIndicator.visibility = View.GONE
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d("Image URI", "showImage: $it")
            binding.previewImageView.setImageURI(it)
        }
    }

    private fun analyzeImage() {

        if (currentImageUri == null) {
            helper.showSnackbar(
                binding.root,
                "Silahkan Pilih Gambar Terlebih Dahulu",
                ContextCompat.getColor(this, R.color.danger),
                ContextCompat.getColor(this, R.color.white)
            )
        } else {
            binding.progressIndicator.visibility = View.VISIBLE
            currentImageUri?.let { uri ->

                imageClassifierHelper = ImageClassifierHelper(
                    context = this,
                    classifierListener = object : ImageClassifierHelper.ClassifierListener {
                        override fun onError(error: String) {
                            runOnUiThread {
                                showToast(error)
                                binding.progressIndicator.visibility = View.GONE
                            }
                        }

                        override fun onResults(results: List<Classifications>?) {
                            runOnUiThread {
                                results?.let { it ->
                                    if (it.isNotEmpty() && it[0].categories.isNotEmpty()) {
                                        println(it)
                                        val sortedCategories =
                                            it[0].categories.sortedByDescending { it?.score }
                                        val largestCategory = sortedCategories.first()
                                        val displayResult =
                                            "${largestCategory.label} " + NumberFormat.getPercentInstance()
                                                .format(largestCategory.score).trim()
                                        moveToResult(displayResult, uri)
                                        binding.progressIndicator.visibility = View.GONE
                                    } else {
                                        showToast("Classification Not Found")
                                    }
                                }
                            }
                        }
                    }
                )

                executorService.execute {
                    try {
                        imageClassifierHelper.classifyStaticImage(uri)
                    } catch (e: Exception) {
                        showToast(e.message ?: "An error occurred")
                    }
                }
            }
        }
    }

    private fun moveToResult(result: String, uri: Uri) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra(ResultActivity.EXTRA_IMAGE_URI, uri.toString())
        intent.putExtra(ResultActivity.EXTRA_RESULT, result)
        startActivity(intent)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}