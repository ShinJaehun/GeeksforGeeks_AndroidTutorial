package com.shinjaehun.paintapp

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.shinjaehun.paintapp.PaintView.Companion.colorList
import com.shinjaehun.paintapp.PaintView.Companion.currentBrush
import com.shinjaehun.paintapp.PaintView.Companion.pathList

class MainActivity : AppCompatActivity() {

    companion object {
        var path = Path()
        var paintBrush = Paint()
        private const val TAG = "MainActivity"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val redBtn = findViewById<ImageView>(R.id.redColor)
        val blueBtn = findViewById<ImageView>(R.id.blueColor)
        val blackBtn = findViewById<ImageView>(R.id.blackColor)
        val whiteBtn = findViewById<ImageView>(R.id.whiteColor)

        redBtn.setOnClickListener {
            Toast.makeText(this, "redbtn Clicked", Toast.LENGTH_SHORT).show()
            paintBrush.color = Color.RED
            currentColor(paintBrush.color)
        }

        blueBtn.setOnClickListener {
            Toast.makeText(this, "bluebtn Clicked", Toast.LENGTH_SHORT).show()
            paintBrush.color = Color.BLUE
            currentColor(paintBrush.color)
        }

        blackBtn.setOnClickListener {
            Toast.makeText(this, "blackbtn Clicked", Toast.LENGTH_SHORT).show()
            paintBrush.color = Color.BLACK
            currentColor(paintBrush.color)

        }

        whiteBtn.setOnClickListener {
            Toast.makeText(this, "whitebtn Clicked", Toast.LENGTH_SHORT).show()
//            paintBrush.color = Color.WHITE
//            currentColor(paintBrush.color)
            pathList.clear()
            colorList.clear()
            path.reset()
        }
    }

    private fun currentColor(color: Int) {
        currentBrush = color
        path = Path()
    }
}