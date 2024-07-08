package com.developerdaya.serviceguru.ui.m1
import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.developerdaya.serviceguru.util.NetworkUtils
import com.developerdaya.serviceguru.R
import com.developerdaya.serviceguru.databinding.ActivityChangeLocationBinding
import com.developerdaya.serviceguru.databinding.GpsDialogBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.developerdaya.serviceguru.util.GPSTracker
import com.developerdaya.serviceguru.util.SPreferenceUtils
import com.developerdaya.serviceguru.util.moveActivity
import com.developerdaya.serviceguru.util.showToast
import com.developerdaya.serviceguru.util.showToastInternet
class ChangeLocationActivity : AppCompatActivity(), OnMapReadyCallback, LocationListener {
    var liveLocation: Location? = null
    private lateinit var locationManager: LocationManager
    var mMap: GoogleMap? = null
    var fullAddress: String = ""
    var buildingNo: String = "Choose your location"
    private var sector: String = ""
    private var city: String = ""
    var state: String = ""
    var postalCode: String = ""
    private var country: String = ""
    lateinit var binding: ActivityChangeLocationBinding
    var mLatitude = ""
    var mLongitude = ""
    val sharedPreference1: SPreferenceUtils by lazy { SPreferenceUtils.getInstance(this) }
    var supportMapFragment: SupportMapFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangeLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(512, 512)
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        binding.btnConfirm.setOnClickListener {
            if (binding.city.text.toString()!="")
            {
                sharedPreference1.buildingNo = buildingNo
                sharedPreference1.sector = sector
                sharedPreference1.city = city
                sharedPreference1.state = state
                sharedPreference1.postalCode = postalCode
                sharedPreference1.countryName = country
                sharedPreference1.addressLocation = fullAddress
                sharedPreference1.latitude = mLatitude
                sharedPreference1.longitude = mLongitude
                val mAddress = "$buildingNo $sector, $city, $state, $postalCode, $country"
                val resultIntent = Intent()
                resultIntent.putExtra("latitude", mLatitude.toString())
                resultIntent.putExtra("longitude", mLongitude.toString())
                resultIntent.putExtra("address", mAddress.toString())
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
            else{
                showToast("Please select your location")
            }
            binding.ivBack.setOnClickListener {
                onBackPressed()
            }

        }
        supportMapFragment =
            supportFragmentManager.findFragmentById(R.id.HomeMap) as SupportMapFragment
        supportMapFragment!!.getMapAsync(callback)
    }

    private val callback = OnMapReadyCallback { googleMap ->
        mMap = googleMap
        liveLocation = GPSTracker(this).location
        if (liveLocation != null) {
            val mLatLng = LatLng(
                liveLocation?.latitude ?: 0.0,
                liveLocation?.longitude ?: 0.0
            )
            mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(mLatLng, 15F))
        }

        mMap?.apply {
            setOnCameraChangeListener {
                val geocoder = Geocoder(this@ChangeLocationActivity)
                try {
                    val addresses =
                        geocoder.getFromLocation(it.target.latitude, it.target.longitude, 1)
                    fullAddress = addresses!![0].getAddressLine(0)
                    buildingNo = addresses[0].featureName
                    sector = addresses[0].subLocality
                    city = addresses[0].locality
                    state = addresses[0].adminArea
                    postalCode = addresses[0].postalCode
                    country = addresses[0].countryName
                    binding.city?.text = "$buildingNo $sector"
                     binding.fullAddress.text = "$city, $state, $postalCode, $country"
                    mLongitude = it.target.longitude.toString()
                    mLatitude = it.target.latitude.toString()
                    sharedPreference1.latitude = mLatitude
                    sharedPreference1.longitude = mLongitude

                } catch (e: Exception) {
                    e.printStackTrace()
                }


            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {}
    override fun onLocationChanged(location: Location) {}

    override fun onResume() {
        super.onResume()
        liveLocation = GPSTracker(this).location
        val geocoderResume = Geocoder(this)
        try {
            val addresses = geocoderResume.getFromLocation(
                liveLocation?.latitude ?: 0.0,
                liveLocation?.longitude ?: 0.0,
                1
            )
            fullAddress = addresses!![0].getAddressLine(0)
            buildingNo = addresses[0].featureName
            sector = addresses[0].subLocality
            city = addresses[0].locality
            state = addresses[0].adminArea
            postalCode = addresses[0].postalCode
            country = addresses[0].countryName
            binding.city.text = "$buildingNo $sector"
            binding.fullAddress.text = "$city, $state, $postalCode, $country"
            mLongitude = liveLocation?.longitude.toString()
            mLatitude = liveLocation?.latitude.toString()
            sharedPreference1.latitude = mLatitude
            sharedPreference1.longitude = mLongitude

        } catch (e: Exception) {
            e.printStackTrace()
        }

        binding.current.setOnClickListener {
            if (NetworkUtils.isInternetAvailable(this)) {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    if (GPSTracker(this).isGPSEnabled) {
                        liveLocation = GPSTracker(this).location
                        val geocoderResume = Geocoder(this@ChangeLocationActivity)
                        try {
                            val addresses = geocoderResume.getFromLocation(
                                liveLocation?.latitude ?: 0.0,
                                liveLocation?.longitude ?: 0.0,
                                1
                            )
                            fullAddress = addresses!![0].getAddressLine(0)
                            buildingNo = addresses[0].featureName
                            sector = addresses[0].subLocality
                            city = addresses[0].locality
                            state = addresses[0].adminArea
                            postalCode = addresses[0].postalCode
                            country = addresses[0].countryName
                            binding.city.text = "$buildingNo $sector"
                            binding.fullAddress.text = "$city, $state, $postalCode, $country"
                            mLongitude = liveLocation?.longitude.toString()
                            mLatitude = liveLocation?.latitude.toString()
                            sharedPreference1.latitude = mLatitude
                            sharedPreference1.longitude = mLongitude

                        } catch (e: Exception) {
                            e.printStackTrace()
                        }


                        val mLatLng = LatLng(
                            liveLocation?.latitude ?: 0.0,
                            liveLocation?.longitude ?: 0.0
                        )
                        mMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(mLatLng, 15F))

                    } else {
                        gpsDialog(this)
                    }


                } else {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        1
                    )
                }

            } else {
                showToastInternet()
            }

        }


    }

    fun gpsDialog(context: Context) {
        val binding = GpsDialogBinding.inflate(LayoutInflater.from(context))
        val mBuilder = androidx.appcompat.app.AlertDialog.Builder(context)
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
}