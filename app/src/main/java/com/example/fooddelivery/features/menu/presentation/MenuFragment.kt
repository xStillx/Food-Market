package com.example.fooddelivery.features.menu.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fooddelivery.R
import com.example.fooddelivery.databinding.FragmentMenuBinding
import com.example.fooddelivery.features.menu.presentation.adapter.BannersAdapter
import com.example.fooddelivery.features.menu.presentation.adapter.CategoriesAdapter
import com.example.fooddelivery.features.menu.presentation.adapter.MenuAdapter
import com.example.fooddelivery.utils.ViewState
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MenuFragment : Fragment(R.layout.fragment_menu) {

    private val viewBinding: FragmentMenuBinding by viewBinding(FragmentMenuBinding::bind)
    private val viewModel: MenuViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.dataBase(requireContext())
        getUserLocation()
        observe()
        initViews()
    }

    private fun initViews() {
        viewBinding.tvCity.setOnClickListener {
            getUserLocation()
        }
    }

    private fun observe() {
        viewModel.location.observe(viewLifecycleOwner){
            viewBinding.tvCity.text = it
        }
        viewBinding.rvFood.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        viewModel.food.observe(viewLifecycleOwner){
            when(it){
                is ViewState.Empty -> {}
                is ViewState.Error -> {}
                is ViewState.Show -> {
                    viewBinding.rvFood.adapter = MenuAdapter(it.data){ food ->
                        viewModel.onAddFoodToBuyClick(food)
                    }
                }
            }
        }

        viewModel.isVisible.observe(viewLifecycleOwner){
            viewBinding.pbProgress.isVisible = it
        }

        viewBinding.rvBanners.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        viewModel.banners.observe(viewLifecycleOwner){
            viewBinding.rvBanners.adapter = BannersAdapter(it)
        }

        viewBinding.rvCategories.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        viewModel.categories.observe(viewLifecycleOwner){
            viewBinding.rvCategories.adapter = CategoriesAdapter(it, requireContext()){ position ->
                when(position){
                    0 -> {
                        viewModel.getPizzas()
                    }
                    1 -> {
                        viewModel.getBurgers()
                    }
                    2 -> {
                        viewModel.getDesserts()
                    }
                    3 -> {
                        viewModel.getDrinks()
                    }
                    4 -> {
                        viewModel.getBbqs()
                    }
                    5 -> {
                        viewModel.getSteaks()
                    }
                }
            }
        }
    }

    private fun getUserLocation() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                ),
                1
            )
        }
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                // Got last known location. In some rare situations this can be null.
                val geocoder = Geocoder(requireContext(), Locale.getDefault())
                val address = geocoder.getFromLocation(location!!.latitude, location.longitude, 1)
                val city = address!![0].locality
                viewModel.getCity(city)
            }
    }
}