package com.developerdaya.serviceguru.ui.m1.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developerdaya.serviceguru.databinding.LayoutBeautyBinding
import com.developerdaya.serviceguru.ui.m1.SubCategoryActivity

class BeautyAdapter(var context: Context,val data: List<BeautyModel>) :
    RecyclerView.Adapter<BeautyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutBeautyBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item,position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(private val binding: LayoutBeautyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BeautyModel, position1: Int) {
            binding.productName.text = item.name
            binding.productImage.setImageResource(item.image)
            binding.root.setOnClickListener {
                SubCategoryActivity.mTitle = item.name
                SubCategoryActivity.mPosition = position1.toString()
                context.startActivity(Intent(context, SubCategoryActivity::class.java))
            }

        }
    }
}

