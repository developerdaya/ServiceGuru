package com.developerdaya.serviceguru.ui.m1.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.airbnb.lottie.LottieAnimationView
import com.developerdaya.serviceguru.R
import com.developerdaya.serviceguru.model.model.InfoModel
import java.util.ArrayList

class InfoAdapter(private val context: Context, private val imageList: ArrayList<InfoModel>) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as View
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val layout = inflater.inflate(R.layout.info_item, container, false) as ViewGroup
        val title: TextView = layout.findViewById(R.id.title)
        val title2: TextView = layout.findViewById(R.id.title2)
        val lottieAnimationView: LottieAnimationView = layout.findViewById(R.id.lottieAnimationView)
        lottieAnimationView.setAnimation(imageList[position].image)
        lottieAnimationView.playAnimation()
        title.text = imageList[position].name
        title2.text = imageList[position].name2
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
