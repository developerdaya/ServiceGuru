package com.developerdaya.serviceguru.ui.m1

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.viewpager.widget.ViewPager
import com.developerdaya.serviceguru.R
import com.developerdaya.serviceguru.databinding.ActivityHomeBinding
import com.developerdaya.serviceguru.databinding.GpsDialogBinding
import com.developerdaya.serviceguru.databinding.LogoutDialogBinding
import com.developerdaya.serviceguru.ui.m1.adapter.BeautyAdapter
import com.developerdaya.serviceguru.ui.m1.adapter.BeautyModel
import com.developerdaya.serviceguru.ui.m1.adapter.ViewPagerAdapter
import com.google.android.gms.maps.model.LatLng
import com.home.genie.ui.moveActivity
import com.developerdaya.serviceguru.util.GPSTracker
import com.developerdaya.serviceguru.util.SPreferenceUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    private lateinit var mViewPager: ViewPager
    private var currentPage = 0
    private var timer: Timer? = null
    private val DELAY_MS: Long = 2000
    private val PERIOD_MS: Long = 2000
    var liveLocation: Location? = null
    val LOCATION_REQUEST_CODE = 101
    val sharedPreference1: SPreferenceUtils by lazy { SPreferenceUtils.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(512,512)
        startAutoScroll()
        initControl()
        if (SPreferenceUtils.getInstance(this).latitude!="")
        {
            val city = sharedPreference1.city
            var state = sharedPreference1.state
            var postalCode = sharedPreference1.postalCode
            var country = sharedPreference1.countryName
            var buildingNo = sharedPreference1.buildingNo
            var sector = sharedPreference1.sector
            binding.mAddress1?.text = "$buildingNo $sector"
            binding.mAddress2.text = "$city, $state, $postalCode, $country"
        }
        else
        {
            getLocation1()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (GPSTracker(this).isGPSEnabled)
            {
                liveLocation = GPSTracker(this).location
                liveLocation?.let {
                    getAddressFromLocation(it.latitude, it.longitude)
                }
            }
            else
            {
                gpsDialog(this)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (SPreferenceUtils.getInstance(this).latitude!="")
        {
            val city = sharedPreference1.city
            var state = sharedPreference1.state
            var postalCode = sharedPreference1.postalCode
            var country = sharedPreference1.countryName
            var buildingNo = sharedPreference1.buildingNo
            var sector = sharedPreference1.sector
            binding.mAddress1?.text = "$buildingNo $sector"
            binding.mAddress2.text = "$city, $state, $postalCode, $country"
        }
        else{
            CoroutineScope(Dispatchers.Main).launch {
                delay(5000)
                if (GPSTracker(this@HomeActivity).isGPSEnabled)
                {
                    liveLocation = GPSTracker(this@HomeActivity).location
                    liveLocation?.let {
                        val mLatLng = LatLng(
                            it.latitude,
                            it.longitude)
                        getAddressFromLocation(it.latitude,it.longitude)

                    }

                }
            }
        }


    }

    fun getLocation1() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            if (GPSTracker(this).isGPSEnabled)
            {
                liveLocation = GPSTracker(this).location
                liveLocation?.let {
                    val mLatLng = LatLng(
                        it.latitude,
                        it.longitude)
                    getAddressFromLocation(it.latitude,it.longitude)

                }

            }
            else
            {
                gpsDialog(this)
            }
        }
        else{
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_REQUEST_CODE)
        }
    }



    private fun getAddressFromLocation(latitude: Double, longitude: Double) {
        val geocoder = Geocoder(this)
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            var fullAddress = addresses!![0].getAddressLine(0)
            var buildingNo = addresses[0].featureName
            var sector = addresses[0].subLocality
            var city = addresses[0].locality
            var state = addresses[0].adminArea
            val postalCode = addresses[0].postalCode
            val country = addresses[0].countryName

            binding.mAddress1?.text = "$buildingNo $sector"
            binding.mAddress2?.text = "$city, $state, $postalCode, $country"

    }

    fun gpsDialog(context: Context) {
        val binding = GpsDialogBinding.inflate(LayoutInflater.from(context))
        val mBuilder = AlertDialog.Builder(context)
            .setView(binding.root)
            .create()
        mBuilder.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mBuilder.setCancelable(false)
        mBuilder.show()
        binding.myButtonSkip.setOnClickListener {
            mBuilder.dismiss()
        }

        binding.myButton.setOnClickListener {
            mBuilder.dismiss()
            startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))

        }


    }

    private fun startAutoScroll() {
        val handler = android.os.Handler()
        val update = Runnable {
            if (currentPage == NUM_PAGES) {
                currentPage = 0
            }
            binding.mViewPager2.setCurrentItem(currentPage++, true)
        }

        timer = Timer() // This will create a new Thread
        timer!!.schedule(object : TimerTask() { // task to be scheduled
            override fun run() {
                handler.post(update)
            }
        }, DELAY_MS, PERIOD_MS)
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel() // Cancel timer on fragment destroy to prevent memory leaks
    }

    companion object {
        private const val NUM_PAGES = 4 /* Set the number of pages in your ViewPager adapter */
    }

    fun logoutDialog(context: Context) {
        val binding = LogoutDialogBinding.inflate(LayoutInflater.from(context))
        val mBuilder = AlertDialog.Builder(context)
            .setView(binding.root)
            .create()
        mBuilder.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mBuilder.setCancelable(false)
        mBuilder.show()
        binding.myButtonSkip.setOnClickListener {
            mBuilder.dismiss()
        }

        binding.myButton.setOnClickListener {
            mBuilder.dismiss()
            SPreferenceUtils.getInstance(this).accessToken = ""
            SPreferenceUtils.getInstance(this).isLogin = false
            SPreferenceUtils.getInstance(this).latitude = ""
            SPreferenceUtils.getInstance(this).longitude = ""
            moveActivity(LoginActivity())
            finishAffinity()
        }


    }


    private fun initControl() {
        binding.mAddress1.setOnClickListener {
            moveActivity(ChangeLocationActivity())
        }
        binding.mLogout.setOnClickListener {
            logoutDialog(this)

        }


        var list = ArrayList<BeautyModel>()
        list.add(BeautyModel("Tech Support", R.drawable.tech_support_banner))
        list.add(BeautyModel("Home Services", R.drawable.home_services))
        list.add(BeautyModel("Education & Coaching", R.drawable.education_banner))
        list.add(BeautyModel("Home Improvement", R.drawable.home_interior))
        list.add(BeautyModel("Business Services",R.drawable.tech_support))
        list.add(BeautyModel("Health & Medcal",R.drawable.dish_icon))
        list.add(BeautyModel("Automotive Services",R.drawable.car_services))


        var lis2 = ArrayList<BeautyModel>()
        lis2.add(BeautyModel("Home Services", R.drawable.house_icon))
        lis2.add(BeautyModel("Repairs", R.drawable.repair_tool))
        lis2.add(BeautyModel("Food & Beverage", R.drawable.dish_icon))

        lis2.add(BeautyModel("Health & Medical", R.drawable.public_health))
        lis2.add(BeautyModel("Automotive Services", R.drawable.car_services))
        lis2.add(BeautyModel("Personal Services", R.drawable.personalized))

        lis2.add(BeautyModel("Home Improvement", R.drawable.house_cleaning))
        lis2.add(BeautyModel("Business Services", R.drawable.busibess_responsible))
        lis2.add(BeautyModel("Tech Support", R.drawable.tech_support))
        lis2.add(BeautyModel("Beauty Sevices",R.drawable.home_interior))
        lis2.add(BeautyModel("Personam Services",R.drawable.house_icon))
        lis2.add(BeautyModel("Home Cleaning",R.drawable.home_services))



        binding.mViewPager2.adapter = ViewPagerAdapter(this, list)
       // binding.mDotsIndicator.setViewPager(binding.mViewPager2)
        binding.rvBeauty.adapter = BeautyAdapter(this,lis2)

    }

}
