package com.example.explicitintentexercise

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnMoveActivity: Button
    private lateinit var btnMoveWithDataActivity: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Move Activity
        btnMoveActivity = findViewById(R.id.btn_move_activity)
        btnMoveActivity.setOnClickListener(this)

        // Move Activity With Data
        btnMoveWithDataActivity = findViewById(R.id.btn_move_activity_data)
        btnMoveWithDataActivity.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_move_activity -> {
                val moveIntent = Intent(this@MainActivity, MovedActivity::class.java)
                startActivity(moveIntent)
            }

            R.id.btn_move_activity_data -> {
                val movewithDataIntent = Intent(this@MainActivity, MoveWithDataActivity::class.java)
                movewithDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "Andi")
                movewithDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 20)
                startActivity(movewithDataIntent)
            }
        }
    }
}