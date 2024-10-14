package com.example.practico2.models

import com.example.practico2.Direcciones

class Snake(var x: Int, var y: Int) {
    var direccion : Direcciones = Direcciones.DERECHA

    fun mover() {
        when (direccion) {
            Direcciones.ABAJO -> y--
            Direcciones.DERECHA -> x++
            Direcciones.ARRIBA -> y++
            Direcciones.IZQUIERDA -> x--
        }
    }
}