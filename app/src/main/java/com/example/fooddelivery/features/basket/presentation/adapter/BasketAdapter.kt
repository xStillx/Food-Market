package com.example.fooddelivery.features.basket.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.fooddelivery.R
import com.example.fooddelivery.databinding.ItemFoodBinding
import com.example.fooddelivery.features.menu.domain.model.Food

class BasketAdapter(
    private val food: List<Food>,
    private val onDeleteClick: (Food) -> Unit
): RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {

    class BasketViewHolder(view: View, private val onDeleteClick: (Food) -> Unit): RecyclerView.ViewHolder(view) {

        private val viewBinding: ItemFoodBinding by viewBinding(ItemFoodBinding::bind)

        @SuppressLint("SetTextI18n")
        fun bind(food: Food){
            Glide.with(viewBinding.ivFood).load(food.image).into(viewBinding.ivFood)
            viewBinding.tvName.text = food.name
            viewBinding.tvDescription.text = food.description
            viewBinding.btnBuy.text = "от ${food.price.toInt()} р"
            viewBinding.btnDelete.isVisible = true
            viewBinding.btnDelete.setOnClickListener {
                onDeleteClick.invoke(food)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val cellForMenu = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return BasketViewHolder(cellForMenu, onDeleteClick)
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        holder.bind(food[position])
    }

    override fun getItemCount(): Int {
        return food.size
    }
}