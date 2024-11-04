package com.example.practico4.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.practico4.databinding.FragmentPersonBinding
import com.example.practico4.models.Phone
import com.example.practico4.ui.adapters.PersonPhoneItemAdapter
import com.example.practico4.ui.viewmodels.PhoneViewModel
import com.example.practico4.ui.viewmodels.SharedViewModel

class PersonFragment : Fragment(), PersonPhoneItemAdapter.PersonPhoneItemListener {
    private lateinit var binding: FragmentPersonBinding
    private val phoneViewModel: PhoneViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonBinding.inflate(inflater, container, false)
        setupEventListeners()
        setupRecycleView()
        setupViewModelObservers()
        return binding.root
    }

    private fun setupViewModelObservers() {
        sharedViewModel.getPersona().observe(viewLifecycleOwner) { person ->
            val fullName =  person.name + " " + person.last_name
            
            if (person.profile_picture.isNotEmpty()) {
                Glide.with(this)
                    .load(person.profile_picture)
                    .into(binding.imgPerson)
            } else {
                binding.imgPerson.setImageResource(android.R.drawable.ic_menu_gallery)
            }
            binding.rvPhones.adapter = PersonPhoneItemAdapter(person.phones, this)
        }

        phoneViewModel.createdPhone.observe(viewLifecycleOwner){phone->
            if (phone != null) {
                val adapter = binding.rvPhones.adapter as PersonPhoneItemAdapter
                adapter.addPhone(phone)
            } else {
                Log.e("Fragment", "El teléfono creado es nulo")
            }
        }

    }

    private fun setupRecycleView() {
        binding.rvPhones.apply {
            adapter = PersonPhoneItemAdapter(arrayListOf(), this@PersonFragment)
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setupEventListeners() {
        binding.button.setOnClickListener {
            val phone = Phone(null,"Ingrese su numero de telefono", sharedViewModel.person.value?.id ?: 0, "Casa")
            phoneViewModel.addPhone(phone)
            val index = getIndex(phone)
            Log.d("Fragment", "Intentando crear teléfono en el índice: $index")
            phoneViewModel.createPhone(index)
        }
    }

    private fun getIndex(phone: Phone): Int {
        val foundData = phoneViewModel.getPhones().value!!.first { it.id == phone.id }
        return phoneViewModel.getPhones().value!!.indexOf(foundData)
    }

    override fun onPhoneOptionsListener(phone: Phone, options: MutableList<String>, optionsLabelPhone: Spinner) {
        optionsLabelPhone.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedOption = options[position]
                if (selectedOption == "Otro") {
                    showAddOptionDialog(optionsLabelPhone, options)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    override fun onPhoneTextChanged(phone: Phone): TextWatcher {
        val handler = Handler(Looper.getMainLooper())
        var runnable: Runnable? = null

        return object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                runnable?.let { handler.removeCallbacks(it) }
                runnable = Runnable {
                    phone.number = p0.toString()
                    val index = getIndex(phone)
                    phoneViewModel.updatePhone(index, phone)
                }
                handler.postDelayed(runnable!!, 2000)
            }
        }
    }

    override fun addPhoneList(phone: Phone) {
        phoneViewModel.addPhone(phone)
    }

    override fun onPhoneDelete(phone: Phone) {
        val adapter = binding.rvPhones.adapter as PersonPhoneItemAdapter
        adapter.deleteItem(phone)
        phoneViewModel.deletePhone(phone)
    }

    private fun showAddOptionDialog(optionsLabel: Spinner, options: MutableList<String>) {
        // Crear un diálogo de alerta para solicitar la nueva opción
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Agregar Nueva Opción")
        val adapter = optionsLabel.adapter as ArrayAdapter<*>

        // Agregar un campo de entrada
        val input = EditText(context)
        builder.setView(input)

        // Configurar botones del diálogo
        builder.setPositiveButton("Agregar") { dialog, _ ->
            val newOption = input.text.toString().trim()
            if (newOption.isNotEmpty()) {
                options.add(newOption)  // Agrega la nueva opción a la lista
                adapter.notifyDataSetChanged()  // Actualiza el adaptador del Spinner
                optionsLabel.setSelection(options.size - 1)  // Selecciona la nueva opción
            }
            dialog.dismiss()
        }
        builder.setNegativeButton("Cancelar") { dialog, _ -> dialog.cancel() }

        builder.show()
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PersonFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}