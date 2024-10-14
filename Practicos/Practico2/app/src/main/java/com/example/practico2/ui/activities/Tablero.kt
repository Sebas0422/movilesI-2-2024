package com.example.practico2.ui.activities

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practico2.R

class Tablero(context: Context, attrs: AttributeSet?) : View(context, attrs)  {

    val displayMetrics = Resources.getSystem().displayMetrics
    val screenWidth = displayMetrics.widthPixels
    val screenHeight = displayMetrics.heightPixels

    private val cellSize = 119f
    private val rows = screenHeight / cellSize
    private val cols = screenWidth / cellSize

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val paint = Paint().apply {
            color = Color.BLACK
            style = Paint.Style.STROKE
            strokeWidth = 5f
        }

        for (row in 0 until rows.toInt()) {
            for (col in 0 until cols.toInt()) {
                val left = col * cellSize
                val top = row * cellSize
                val right = left + cellSize
                val bottom = top + cellSize

                canvas.drawRect(left, top, right, bottom, paint)
            }
        }
    }
}