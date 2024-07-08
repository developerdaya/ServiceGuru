package com.developerdaya.serviceguru.ui.m1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developerdaya.serviceguru.databinding.ItemCategoryBinding

class CategoryAdapter(var context: Context, val data: List<String>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.consultTypeName.text = item
          /*  binding.productImage.setImageResource(item.image)
            binding.root.setOnClickListener {
                SubCategoryActivity.mTitle = item.name
                SubCategoryActivity.mPosition = position1.toString()
                context.startActivity(Intent(context, SubCategoryActivity::class.java))
            }*/

        }
    }
}

