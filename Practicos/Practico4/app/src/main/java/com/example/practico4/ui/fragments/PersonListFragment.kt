package com.example.practico4.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practico4.R
import com.example.practico4.databinding.FragmentPersonListBinding
import com.example.practico4.models.Person
import com.example.practico4.ui.adapters.PersonItemAdapter
import com.example.practico4.ui.viewmodels.PersonListViewModel
import com.example.practico4.ui.viewmodels.SharedViewModel

class PersonListFragment : Fragment(), PersonItemAdapter.PersonItemListener {
    private lateinit var binding: FragmentPersonListBinding
    private val viewModel: PersonListViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonListBinding.inflate(inflater, container, false)
        setupViewModelObservers()
        setupReciclerView()
        setupEventListener()
        viewModel.getPersonList()
        return binding.root
    }

    private fun setupReciclerView() {
        val adapter = PersonItemAdapter(this)
        binding.rvPersonList.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun setupViewModelObservers() {
        viewModel.personList.observe(viewLifecycleOwner) {
            val adapter = binding.rvPersonList.adapter as PersonItemAdapter
            adapter.updateData(it)
        }
    }

    private fun setupEventListener() {
        binding.txtSearch.editText?.addTextChangedListener(createTextWatcher())
        binding.btnNewPerson.setOnClickListener{
            val person : Person = Person(0,"","","","","","","", arrayListOf(), arrayListOf())
            sharedViewModel.setPerson(person)
            val navController = findNavController()
            navController.navigate(R.id.action_personListFragment_to_newPersonFragment)
        }
    }

    private fun createTextWatcher(): TextWatcher {
        val handler = Handler(Looper.getMainLooper())
        var runnable: Runnable? = null

        return object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                runnable?.let { handler.removeCallbacks(it) }
                runnable = Runnable {
                    viewModel.searchPerson(s.toString())
                }
                handler.postDelayed(runnable!!, 2000)
            }
        }
    }

    override fun onPersonItemClick(person: Person) {
        sharedViewModel.setPerson(person)
        val navController = findNavController()
        navController.navigate(R.id.action_personListFragment_to_newPersonFragment)
    }

    override fun onPersonItemDelete(person: Person) {
        val adapter = binding.rvPersonList.adapter as PersonItemAdapter
        adapter.deletePerson(person)
        viewModel.deletePerson(person)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PersonListFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}