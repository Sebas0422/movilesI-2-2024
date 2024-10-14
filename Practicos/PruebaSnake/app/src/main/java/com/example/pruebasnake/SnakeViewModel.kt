package com.example.pruebasnake

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.concurrent.fixedRateTimer

class SnakeViewModel : ViewModel() {
    val snakeBodyLiveData: MutableLiveData<List<Position>> = MutableLiveData()
    val foodPositionLiveData: MutableLiveData<Position> = MutableLiveData()
    val gameOverLiveData: MutableLiveData<Boolean> = MutableLiveData()

    private val game: SnakeGame = SnakeGame()

    init {
        snakeBodyLiveData.value = game.snakeBody
        foodPositionLiveData.value = game.foodPosition
        gameOverLiveData.value = false

        // Iniciar el movimiento automático de la serpiente
        fixedRateTimer("timer", initialDelay = 1000, period = 1000) {
            moveSnake()
        }
    }

    fun moveSnake() {
        game.moveSnake()
        snakeBodyLiveData.postValue(game.snakeBody)
        foodPositionLiveData.postValue(game.foodPosition)
    }

    fun changeDirection(direction: SnakeGame.Direction) {
        game.direction = direction
    }

    fun resetGame() {
        game.snakeBody = mutableListOf(Position(5, 5))
        game.placeFood()
        gameOverLiveData.postValue(false)
    }
}
