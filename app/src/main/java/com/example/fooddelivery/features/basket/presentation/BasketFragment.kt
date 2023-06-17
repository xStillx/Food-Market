package com.example.fooddelivery.features.basket.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fooddelivery.R
import com.example.fooddelivery.databinding.FragmentBasketBinding
import com.example.fooddelivery.features.basket.presentation.adapter.BasketAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BasketFragment: Fragment(R.layout.fragment_basket) {

    private val viewBinding: FragmentBasketBinding by viewBinding(FragmentBasketBinding::bind)
    private val viewModel: BasketViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe()
    }

    private fun observe() {
        viewModel.database(requireContext())
        viewBinding.rvBasket.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        viewModel.readAllData!!.observe(viewLifecycleOwner){
            viewBinding.rvBasket.adapter = BasketAdapter(it){ food ->
                viewModel.onDeleteFoodClick(food)
            }
        }
    }
}