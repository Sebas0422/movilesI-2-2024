package com.example.practico1.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.practico1.R
import com.example.practico1.databinding.FragmentTopBinding
import com.example.practico1.models.Nota
import com.example.practico1.ui.activities.MainActivity

/**
 * A simple [Fragment] subclass.
 * Use the [TopFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TopFragment : Fragment() {
    private lateinit var binding : FragmentTopBinding
    private var currentNota: Nota? = null

   override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentTopBinding.inflate(inflater, container, false)
       setupEventListeners()
       return binding.root
    }

    private fun setupEventListeners() {
        binding.btnSaveNote.setOnClickListener {
            val title = binding.txtTitleNote.text.toString()
            val content = binding.txtContentNote.text.toString()
            val isInsert = currentNota == null
            if(isInsert){
                currentNota = Nota(0, title, content)
            } else {
                currentNota?.titulo = title
                currentNota?.contenido = content
            }
            activity?.let{
                val mainActivity = it as MainActivity
                mainActivity.saveContact(currentNota!!,isInsert)
            }
        }
    }

    fun clearForm() {
        binding.txtTitleNote.setText("")
        binding.txtContentNote.setText("")
        currentNota = null
    }

    fun editNota(nota: Nota) {
        binding.txtTitleNote.setText(nota.titulo)
        binding.txtContentNote.setText(nota.contenido)
        currentNota = nota
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment TopFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = TopFragment()
    }
}