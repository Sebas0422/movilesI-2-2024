package com.example.practico3.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practico3.R
import com.example.practico3.databinding.FragmentProfileLikeBinding
import com.example.practico3.models.User
import com.example.practico3.ui.adapters.ProfileLikeAdapter
import com.example.practico3.ui.viewmodels.SharedViewModel
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileLikeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileLikeFragment : Fragment() {
    private lateinit var binding: FragmentProfileLikeBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileLikeBinding.inflate(inflater, container, false)
        setupEventListeners()
        setupRecyclerView()
        setupViewModelObservers()
        return binding.root
    }

    private fun setupEventListeners(){
        /*binding.btnVolver.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_profileLikeFragment_to_profileFragment)
        }*/
    }

    private fun setupRecyclerView(){
        binding.rvProfileLike.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.rvProfileLike.adapter = ProfileLikeAdapter(arrayListOf())
    }

    private fun setupViewModelObservers() {
        sharedViewModel.getLikedUsers().observe(viewLifecycleOwner) { users ->
            binding.rvProfileLike.adapter = ProfileLikeAdapter(users)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment ProfileLikeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            ProfileLikeFragment()
    }
}