package com.example.fooddelivery.features.menu.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.fooddelivery.R
import com.example.fooddelivery.databinding.ItemBannerBinding

class BannersAdapter(
    private val ids: List<Int>
): RecyclerView.Adapter<BannersAdapter.BannersViewHolder>() {

    class BannersViewHolder(view: View) : ViewHolder(view) {

        private val viewBinding: ItemBannerBinding by viewBinding(ItemBannerBinding::bind)

        fun bind(id: Int){
            viewBinding.ivBanner.setImageResource(id)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannersViewHolder {
        val cellForId = LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false)
        return BannersViewHolder(cellForId)
    }

    override fun onBindViewHolder(holder: BannersViewHolder, position: Int) {
        holder.bind(ids[position])
    }

    override fun getItemCount(): Int {
        return ids.size
    }
}