package com.developerdaya.serviceguru.ui.m1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.developerdaya.serviceguru.R
import com.developerdaya.serviceguru.databinding.ActivityInfoBinding
import com.developerdaya.serviceguru.model.model.InfoModel
import com.developerdaya.serviceguru.ui.m1.adapter.InfoAdapter
import com.home.genie.ui.moveActivity

class InfoActivity : AppCompatActivity() {
    lateinit var binding: ActivityInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initControl()



    }

    fun initControl() {
        binding.btnNext.setOnClickListener {
            if (binding.mViewPager2.currentItem == 2)
            {
                moveActivity(LoginActivity())
                finish()
            } else {
                binding.mViewPager2.currentItem = binding.mViewPager2.currentItem + 1
            }
        }
    }

    private fun initViews() {
        val lis2 = ArrayList<InfoModel>()
        lis2.add(
            InfoModel(
            R.raw.anim1,
            "Electronic Kit Repair",
            "Expert repair services for TVs, mobiles, computers, and ACs. Our skilled technicians ensure quick and reliable fixes."
        )
        )
        lis2.add(
            InfoModel(
            R.raw.anim2,
            "Home Cleaning Services",
            "Comprehensive cleaning for your entire home. Eco-friendly products and thorough methods for a spotless, healthy environment."
        )
        )
        lis2.add(
            InfoModel(
            R.raw.anim3,
            "Vehicle Repair Services",
            "Professional repair for all vehicles. From routine maintenance to major overhauls, ensuring your vehicle is safe and road-ready."
        )
        )
        binding.mViewPager2.adapter = InfoAdapter(this, lis2)
        binding.mDotsIndicator.setViewPager(binding.mViewPager2)

        binding.mViewPager2.adapter = InfoAdapter(this, lis2)
        binding.mDotsIndicator.setViewPager(binding.mViewPager2)

    }
}