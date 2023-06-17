package com.example.fooddelivery.features.profile.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fooddelivery.R
import com.example.fooddelivery.databinding.FragmentProfileBinding
import com.example.fooddelivery.features.basket.presentation.adapter.BasketAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val viewBinding: FragmentProfileBinding by viewBinding(FragmentProfileBinding::bind)
    private val viewModel: ProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
    }

    private fun observe() {
        viewModel.database(requireContext())
        viewBinding.rvHistory.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        viewModel.readAllData!!.observe(viewLifecycleOwner){
            viewBinding.rvHistory.adapter = BasketAdapter(it){ food ->

            }
        }
    }
}