package com.example.mybackgroundthread

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.mybackgroundthread.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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

        val btnStart: Button = binding.btnStart
        val tvStatus: TextView = binding.tvStatus


        /**
         * executor ( for execute runnable object/ thread) + handler ( for handling ui thread )
         */
//        val executor = Executors.newSingleThreadExecutor()
//        val handler = Handler(Looper.getMainLooper())
//
//        btnStart.setOnClickListener {
//            executor.execute {
//                try {
//                    for (i in 0..10){
//                        Thread.sleep(500)
//
//                        handler.post{
//                            val percentage = i * 10
//                            if (percentage == 100){
//                                tvStatus.setText(R.string.task_completed)
//                            }else{
//                                tvStatus.text = String.format(getString(R.string.compressing), percentage)
//                            }
//                        }
//
//                        Thread.sleep(10000)
//
//                        handler.post{
//
//                        }
//                    }
//                }catch (e: InterruptedException){
//                    e.printStackTrace()
//                }
//            }
//        }

        /**
         * Coroutines
         */

        btnStart.setOnClickListener {

            // Dispatchers.Default used because in background thread we not using read-and write
            lifecycleScope.launch(Dispatchers.Default) {
                // simulate process in background thread
                for ( i in 0..10){
                    delay(500) // this line that reason to using Dispatcher.Default
                    val percentage = i * 10

                    // Dispatchers.Main use for move to main thread or ui thread
                    withContext(Dispatchers.Main){
                        if (percentage == 100) {
                            tvStatus.setText(R.string.task_completed)
                        } else {
                            tvStatus.text = String.format(getString(R.string.compressing), percentage)
                        }
                    }
                }
            }
        }
    }
}