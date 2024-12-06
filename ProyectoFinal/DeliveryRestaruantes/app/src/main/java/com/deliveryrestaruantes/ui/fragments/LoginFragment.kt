package com.deliveryrestaruantes.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.deliveryrestaruantes.security.TokenManager
import com.deliveryrestaruantes.ui.viewmodels.UserViewModel
import com.example.deliveryrestaruantes.R
import com.example.deliveryrestaruantes.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private val UserViewModel: UserViewModel by viewModels()
    private lateinit var binding: FragmentLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        setupEventListeners()
        return binding.root
    }

    private fun setupEventListeners() {
        binding.btnLogin.setOnClickListener {
            intentLogin()
        }
        binding.btnRegisterLogin.setOnClickListener {
            val navController =findNavController()
            navController.navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun intentLogin(){
        val email = binding.txtEmailUserLogin.text.toString()
        val password = binding.txtPasswordUserLogin.text.toString()
        if (email.isNotEmpty() && password.isNotEmpty()){
            UserViewModel.login(email, password)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            LoginFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}