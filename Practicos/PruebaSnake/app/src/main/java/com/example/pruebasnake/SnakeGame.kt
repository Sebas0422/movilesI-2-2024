package com.example.pruebasnake

data class Position(val x: Int, val y: Int)

class SnakeGame {
    var snakeBody: MutableList<Position> = mutableListOf (
        Position(5, 5),
        Position(4,5),
        Position(3,5)
    )
    var foodPosition: Position = Position(10, 10)
    var direction: Direction = Direction.RIGHT

    fun moveSnake() {
        // Lógica para mover la serpiente
        val head = snakeBody.first()
        val newHead = when (direction) {
            Direction.UP -> Position(head.x, head.y - 1)
            Direction.DOWN -> Position(head.x, head.y + 1)
            Direction.LEFT -> Position(head.x - 1, head.y)
            Direction.RIGHT -> Position(head.x + 1, head.y)
        }

        // Verificar colisiones con la comida
        if (newHead == foodPosition) {
            snakeBody.add(0, newHead) // Crece la serpiente
            placeFood() // Reubica la comida
        } else {
            snakeBody.add(0, newHead)
            snakeBody.removeAt(snakeBody.size - 1) // Mover sin crecer
        }
    }

    fun placeFood() {
        // Generar una posición aleatoria para la comida
        foodPosition = Position((0..20).random(), (0..20).random())
    }

    enum class Direction {
        UP, DOWN, LEFT, RIGHT
    }
}
