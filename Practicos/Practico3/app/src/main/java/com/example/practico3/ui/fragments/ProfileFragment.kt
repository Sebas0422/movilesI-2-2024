package com.example.practico3.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practico3.R
import com.example.practico3.databinding.FragmentProfileBinding
import com.example.practico3.models.User
import com.example.practico3.ui.adapters.ProfileAdapter
import com.example.practico3.ui.viewmodels.ProfileViewModel
import com.example.practico3.ui.viewmodels.SharedViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment(), ProfileAdapter.OnProfileClickListener  {
    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels()
    private val sharedViewModel: SharedViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.rvProfile.layoutManager = LinearLayoutManager(context)
        setupEventListeners()
        setupViewModelObservers()
        return binding.root
    }

    private fun setupViewModelObservers(){
        viewModel.users.observe(viewLifecycleOwner) {
            binding.rvProfile.adapter = ProfileAdapter(it, this)
        }
    }

    private fun setupEventListeners() {
        binding.btnVerLike.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_profileFragment_to_profileLikeFragment)
        }
    }

    override fun onLikeClick(user: User) {
        // Añadir el usuario al SharedViewModel
        sharedViewModel.addLikedUser(user)
        val adapter = binding.rvProfile.adapter as ProfileAdapter
        adapter.itemDelete(user)
    }

    override fun onDislikeClick(user: User) {
        val adapter = binding.rvProfile.adapter as ProfileAdapter
        adapter.itemDelete(user)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment profileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            ProfileFragment()
    }
}