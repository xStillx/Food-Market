package com.example.fooddelivery.features.menu.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.fooddelivery.R
import com.example.fooddelivery.databinding.ItemFoodBinding
import com.example.fooddelivery.features.menu.domain.model.Food

class MenuAdapter(
    private val food: List<Food>,
    private val onBuyClick: (Food) -> Unit
): RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    class MenuViewHolder(view: View, private val onBuyClick: (Food) -> Unit): ViewHolder(view) {

        private val viewBinding: ItemFoodBinding by viewBinding(ItemFoodBinding::bind)

        @SuppressLint("SetTextI18n")
        fun bind(food: Food){
            Glide.with(viewBinding.ivFood).load(food.image).into(viewBinding.ivFood)
            viewBinding.tvName.text = food.name
            viewBinding.tvDescription.text = food.description
            viewBinding.btnBuy.text = "от ${food.price.toInt()} р"
            viewBinding.btnBuy.setOnClickListener {
                onBuyClick.invoke(food)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val cellForMenu = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return MenuViewHolder(cellForMenu, onBuyClick)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(food[position])
    }

    override fun getItemCount(): Int {
        return food.size
    }
}