package com.developerdaya.serviceguru.ui.m1.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.developerdaya.serviceguru.R
import java.util.ArrayList

class ViewPagerAdapter(private val context: Context, private val imageList: ArrayList<BeautyModel>) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as View
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val layout = inflater.inflate(R.layout.rc_banner, container, false) as ViewGroup
        val productTitle: TextView = layout.findViewById(R.id.productTitle)
        val imageViewPager: ImageView = layout.findViewById(R.id.productImage)
        imageViewPager.setImageResource(imageList[position].image)
        productTitle.text = imageList[position].name
        container.addView(layout)
        return layout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return imageList.size
    }
}
