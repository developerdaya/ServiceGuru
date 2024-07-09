package com.developerdaya.serviceguru.ui.m1.adapter

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developerdaya.serviceguru.R
import com.developerdaya.serviceguru.databinding.ItemCategoryBinding
import com.developerdaya.serviceguru.databinding.ItemDocBinding

class DocumentAdapter(var context: Context, val data: List<Bitmap?>,val onClick: OnClick) :
    RecyclerView.Adapter<DocumentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDocBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(private val binding: ItemDocBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Bitmap?) {
            if (item!=null)
            {
                binding.mImageView.setImageBitmap(item)
            }
            else
            {
                binding.mImageView.setImageResource(R.drawable.documents_place_holder)
            }
           // binding.info.text = item
            binding.root.setOnClickListener {
               onClick.onClick(position = position)
            }

        }
    }
}

interface OnClick
{
    fun onClick(position: Int)

}

