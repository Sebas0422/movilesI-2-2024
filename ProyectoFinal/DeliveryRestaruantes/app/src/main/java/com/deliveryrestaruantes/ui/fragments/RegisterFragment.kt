package com.deliveryrestaruantes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.deliveryrestaruantes.R
import com.example.deliveryrestaruantes.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        setupEventListeners()
        return binding.root
    }

    private fun setupEventListeners() {
        binding.btnRegister.setOnClickListener {
            intentRegister()
        }
        binding.btnCancelRegister.setOnClickListener {
            navLoginFragment()
        }
    }

    private fun navLoginFragment() {
        val navController = findNavController()
        navController.navigate(R.id.action_registerFragment_to_loginFragment)
    }

    private fun intentRegister() {
        TODO("Not yet implemented")
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            RegisterFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}