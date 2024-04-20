package com.example.mycanvas

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Region
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mycanvas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888)
        binding.imageView.setImageBitmap(bitmap)

        val canvas = Canvas(bitmap)
        canvas.drawColor(ResourcesCompat.getColor(resources, R.color.blue, null))

        val paint = Paint()

        canvas.save() // menyimpan pengaturan sebelumnya.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            canvas.clipRect(bitmap.width/2 - 100F, bitmap.height/2 - 100F, bitmap.width/2 + 100F, bitmap.height/2 + 100F, Region.Op.DIFFERENCE)
        } else {
            canvas.clipOutRect(bitmap.width/2 - 100, bitmap.height/2 - 100, bitmap.width/2 + 100, bitmap.height/2 + 100)
        }

        paint.color = ResourcesCompat.getColor(resources, R.color.black, null)
        canvas.drawCircle((bitmap.width/2).toFloat(), (bitmap.height/2).toFloat(), 200f, paint)
        canvas.restore() // mengembalikan pengaturan yang telah tersimpan.

        val paintText =  Paint(Paint.FAKE_BOLD_TEXT_FLAG)
        paintText.textSize = 20F
        paintText.color = ResourcesCompat.getColor(resources, R.color.white, null)

        val text = "Selamat Datang!"
        val bounds = Rect()
        paintText.getTextBounds(text, 0, text.length, bounds)

        val x: Int = bitmap.width/2 - bounds.centerX()
        val y: Int = bitmap.height/2 - bounds.centerY()
        canvas.drawText(text, x.toFloat(), y.toFloat(), paintText)
    }
}

// Contoh menggunakan CustomView
class CustomView(context: Context) : View(context) {

    private val bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888)
    private val paint = Paint()
    private val paintText =  Paint(Paint.FAKE_BOLD_TEXT_FLAG)
    private val bounds = Rect()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(bitmap.width, bitmap.height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawColor(ResourcesCompat.getColor(resources, R.color.blue, null))

        canvas.save() // menyimpan pengaturan sebelumnya.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            canvas.clipRect(bitmap.width/2 - 100F, bitmap.height/2 - 100F, bitmap.width/2 + 100F, bitmap.height/2 + 100F, Region.Op.DIFFERENCE)
        } else {
            canvas.clipOutRect(bitmap.width/2 - 100, bitmap.height/2 - 100, bitmap.width/2 + 100, bitmap.height/2 + 100)
        }

        paint.color = ResourcesCompat.getColor(resources, R.color.black, null)
        canvas.drawCircle((bitmap.width/2).toFloat(), (bitmap.height/2).toFloat(), 200f, paint)
        canvas.restore() // mengembalikan pengaturan yang telah tersimpan.

        paintText.textSize = 20F
        paintText.color = ResourcesCompat.getColor(resources, R.color.white, null)

        val text = "Selamat Datang!"
        paintText.getTextBounds(text, 0, text.length, bounds)

        val x: Int = bitmap.width/2 - bounds.centerX()
        val y: Int = bitmap.height/2 - bounds.centerY()
        canvas.drawText(text, x.toFloat(), y.toFloat(), paintText)
    }
}