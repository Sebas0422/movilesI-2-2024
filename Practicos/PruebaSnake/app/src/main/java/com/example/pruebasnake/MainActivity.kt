package com.example.pruebasnake
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    private val viewModel: SnakeViewModel by viewModels()

    private lateinit var snakeView: SnakeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        snakeView = SnakeView(this)
        setContentView(snakeView)

        // Observar los cambios en los datos del juego
        viewModel.snakeBodyLiveData.observe(this, Observer { snakeBody ->
            snakeView.snakeBody = snakeBody
            snakeView.invalidate() // Redibujar la vista
        })

        viewModel.foodPositionLiveData.observe(this, Observer { foodPosition ->
            snakeView.foodPosition = foodPosition
            snakeView.invalidate() // Redibujar la vista
        })
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        // Manejar gestos táctiles para cambiar la dirección
        if (event?.action == MotionEvent.ACTION_DOWN) {
            val x = event.x
            val y = event.y

            // Cambiar la dirección en función del toque (esto es solo un ejemplo básico)
            if (x > y) {
                viewModel.changeDirection(SnakeGame.Direction.RIGHT)
            } else {
                viewModel.changeDirection(SnakeGame.Direction.LEFT)
            }
        }
        return super.onTouchEvent(event)
    }
}
