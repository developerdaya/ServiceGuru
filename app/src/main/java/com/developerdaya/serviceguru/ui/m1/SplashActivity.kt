package com.developerdaya.serviceguru.ui.m1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.developerdaya.serviceguru.databinding.ActivitySplashBinding
import com.developerdaya.serviceguru.util.SPreferenceUtils
import com.developerdaya.serviceguru.util.moveActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(1024,1024)
        CoroutineScope(Dispatchers.Main).launch {
            delay(3500)
            if (SPreferenceUtils.getInstance(this@SplashActivity).isLogin)
            {
                moveActivity(HomeActivity())
            }
            else
            {
               moveActivity(InfoActivity())
            }
           finish()
        }

    }

}