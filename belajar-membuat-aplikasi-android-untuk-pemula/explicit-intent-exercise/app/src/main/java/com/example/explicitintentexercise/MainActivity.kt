package com.example.explicitintentexercise

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.example.explicitintentexercise.data.Person

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var btnMoveActivity: Button
    private lateinit var btnMoveWithDataActivity: Button
    private lateinit var btnMoveWithObject: Button
    private lateinit var btnDialPhone: Button
    private lateinit var btnMoveForResult: Button
    private lateinit var tvResult: TextView

    private val resultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){result ->
        if (result.resultCode == MoveForResultActivity.RESULT_CODE && result.data != null){
            val selectedValue = result.data?.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE, 0)
            tvResult.text = "Hasil : $selectedValue"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Move Activity
        btnMoveActivity = findViewById(R.id.btn_move_activity)
        btnMoveActivity.setOnClickListener(this)

        // Move Activity With Data
        btnMoveWithDataActivity = findViewById(R.id.btn_move_activity_data)
        btnMoveWithDataActivity.setOnClickListener(this)

        // Move Activity With Object
        btnMoveWithObject = findViewById(R.id.btn_move_activity_object)
        btnMoveWithObject.setOnClickListener(this)

        // Implicit Intent
        btnDialPhone = findViewById(R.id.btn_dial_number)
        btnDialPhone.setOnClickListener(this)

        // Move For Result
        btnMoveForResult = findViewById(R.id.btn_move_result)
        btnMoveForResult.setOnClickListener(this)

        // Text View Result
        tvResult = findViewById(R.id.tv_result)
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

            R.id.btn_move_activity_object -> {
                val person = Person(
                    "Andi",
                    5,
                    "andi.dvg@gmial.com",
                    "Surabaya"
                )

                val moveWithObject = Intent(this@MainActivity, MoveWithObjectActivity::class.java)
                moveWithObject.putExtra(MoveWithObjectActivity.EXTRA_PERSON, person)
                startActivity(moveWithObject)
            }

            R.id.btn_dial_number -> {
                val phoneNumber = "08128829842"
                val dialPhoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialPhoneIntent)
            }

            R.id.btn_move_result -> {
                val moveResultIntent = Intent(this@MainActivity, MoveForResultActivity::class.java)
                resultLauncher.launch(moveResultIntent)
            }
        }
    }
}