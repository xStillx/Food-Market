package com.example.fooddelivery.features.menu.presentation.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fooddelivery.R
import com.example.fooddelivery.databinding.ItemCategoryBinding

class CategoriesAdapter(
    private val categories: List<String>,
    private val context: Context,
    private val onCategoryClick: (category: Int) -> Unit
): RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    private var selectedItem: Int = 0

    class CategoriesViewHolder(view: View) : ViewHolder(view) {

        internal val viewBinding: ItemCategoryBinding by viewBinding(ItemCategoryBinding::bind)

        fun bind(category: String, context: Context){
            viewBinding.tvCategory.setTextColor(context.getColor(R.color.categories_gray))
            viewBinding.clCategory.setBackgroundResource(R.drawable.category_background)
            viewBinding.clCategory.elevation = 8F
            viewBinding.tvCategory.text = category
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val cellForRow = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoriesViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.bind(categories[position], context)
        if (selectedItem == position){
            holder.itemView.elevation = 0F
            holder.itemView.setBackgroundResource(R.drawable.categories_selected_background)
            holder.viewBinding.tvCategory.setTextColor(context.getColor(R.color.red))
        }
        holder.itemView.setOnClickListener {
            onCategoryClick.invoke(position)
            val previousItem = selectedItem
            selectedItem = position
            notifyItemChanged(previousItem)
            notifyItemChanged(selectedItem)
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}