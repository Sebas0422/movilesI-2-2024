package com.example.practico1.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practico1.R
import com.example.practico1.databinding.ActivityMainBinding
import com.example.practico1.models.Nota
import com.example.practico1.ui.fragments.BottomFragment
import com.example.practico1.ui.fragments.TopFragment

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun clearForm(){
        val topFragment = binding.fraTop.getFragment<TopFragment>()
        topFragment.clearForm()
    }

    fun saveContact(nota: Nota, isInsert: Boolean) {
        val bottomFragment = binding.fraBottom.getFragment<BottomFragment>()
        if (isInsert){
            bottomFragment.addNotaToList(nota.titulo, nota.contenido)
        } else {
            bottomFragment.editNotaFormList(nota)
        }
        clearForm()
    }

    fun editNota(nota: Nota) {
        val topFragment = binding.fraTop.getFragment<TopFragment>()
        topFragment.editNota(nota)
    }
}