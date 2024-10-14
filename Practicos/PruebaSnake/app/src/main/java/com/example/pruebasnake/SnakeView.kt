package com.example.pruebasnake

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class SnakeView(context: Context) : View(context) {

    var snakeBody: List<Position> = listOf()
    var foodPosition: Position? = null
    private val snakePaint = Paint().apply { color = Color.GREEN }
    private val foodPaint = Paint().apply { color = Color.RED }

    val displayMetrics = Resources.getSystem().displayMetrics
    val screenWidth = displayMetrics.widthPixels
    val screenHeight = displayMetrics.heightPixels

    private val cellSize = 119f
    private val rows = screenHeight / cellSize
    private val cols = screenWidth / cellSize

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Dibujar la serpiente
        snakeBody.forEach { position ->
            canvas.drawRect(
                position.x * cellSize, position.y * cellSize,
                (position.x + 1) * cellSize, (position.y + 1) * cellSize,
                snakePaint
            )
        }

        // Dibujar la comida
        foodPosition?.let {
            canvas.drawRect(
                it.x * cellSize, it.y * cellSize,
                (it.x + 1) * cellSize, (it.y + 1) * cellSize,
                foodPaint
            )
        }
    }
}
