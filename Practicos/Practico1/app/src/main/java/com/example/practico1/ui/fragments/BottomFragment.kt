package com.example.practico1.ui.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practico1.R
import com.example.practico1.databinding.FragmentBottomBinding
import com.example.practico1.models.Nota
import com.example.practico1.ui.activities.MainActivity
import com.example.practico1.ui.adapters.NotaAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [BottomFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BottomFragment : Fragment(), NotaAdapter.OnNotaClickListener {
    private lateinit var binding : FragmentBottomBinding
    private val notas = arrayListOf<Nota>(
        Nota(1,"Nota 1", "Contenido de la nota 1"),
    )

    private val colors: MutableMap<Int, Int> = mutableMapOf(
        0 to R.color.white
    )

    private lateinit var rvNotas: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBottomBinding.inflate(inflater, container, false)
        rvNotas = binding.rvNotas
        setupRecyclerView()
        return binding.root
    }

    private fun setupRecyclerView() {
        rvNotas.adapter = NotaAdapter(notas, colors, this)
        rvNotas.layoutManager = LinearLayoutManager(context)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment ButtonFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance()= BottomFragment()
    }

    public fun editNotaFormList(nota: Nota) {
        val adapter = rvNotas.adapter as NotaAdapter
        adapter.itemUpdated(nota)
    }

    public fun addNotaToList(title: String, content: String) {
        val lastId = notas.last().id + 1
        val nota = Nota(lastId, title, content)
        val adapter = rvNotas.adapter as NotaAdapter
        adapter.itemAdded(nota)
    }

    override fun onNotaEditClickListener(nota: Nota) {
        activity?.let {
            val mainActivity = it as MainActivity
            mainActivity.editNota(nota)
        }
    }

    override fun onNotaDeleteClickListener(nota: Nota) {
        val adapter = rvNotas.adapter as NotaAdapter
        adapter.itemDelete(nota)
    }

    override fun onNotaUpdateBackgroundColor(position: Int, color: Int) {
        val adapter = rvNotas.adapter as NotaAdapter
        adapter.updatedBackgroundColor(position, Color.RED)
    }
}