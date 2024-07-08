package com.developerdaya.serviceguru.ui.m1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.developerdaya.serviceguru.R
import com.developerdaya.serviceguru.databinding.ActivitySubCategoryBinding
import com.developerdaya.serviceguru.ui.m1.adapter.BeautyAdapter
import com.developerdaya.serviceguru.ui.m1.adapter.BeautyModel

class SubCategoryActivity : AppCompatActivity() {
    lateinit var binding: ActivitySubCategoryBinding
   companion object{
       var mTitle = ""
       var mPosition = ""
   }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(512,512)
        initViews()
        initControl()

    }

    private fun initControl() {
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initViews() {
        binding.mLogout.text = mTitle
        var homeList = ArrayList<BeautyModel>()
        homeList.add(BeautyModel("Cleaning", R.drawable.house_icon))
        homeList.add(BeautyModel("Plumbing", R.drawable.house_icon))
        homeList.add(BeautyModel("Electrical", R.drawable.house_icon))
        homeList.add(BeautyModel("Carpentry", R.drawable.house_icon))
        homeList.add(BeautyModel("Painting", R.drawable.house_icon))



        var repairsList = ArrayList<BeautyModel>()
        repairsList.add(BeautyModel("Appliance Repair", R.drawable.repair_tool))
        repairsList.add(BeautyModel("Mobile Repair", R.drawable.repair_tool))
        repairsList.add(BeautyModel("Computer Repair", R.drawable.repair_tool))
        repairsList.add(BeautyModel("AC Repair", R.drawable.repair_tool))
        repairsList.add(BeautyModel("Vehicle Repair", R.drawable.repair_tool))




        var educationList = ArrayList<BeautyModel>()
        educationList.add(BeautyModel("Home Chef", R.drawable.dish_icon))
        educationList.add(BeautyModel("Food Delivery", R.drawable.dish_icon))
        educationList.add(BeautyModel("Baking Services", R.drawable.dish_icon))

        educationList.add(BeautyModel("Meal Prepping", R.drawable.dish_icon))
        educationList.add(BeautyModel("Beverage Delivery", R.drawable.dish_icon))



    var healthList = ArrayList<BeautyModel>()
        healthList.add(BeautyModel("Doctor Consultation", R.drawable.public_health))
        healthList.add(BeautyModel("Physiotherapy", R.drawable.public_health))
        healthList.add(BeautyModel("Nursing Care", R.drawable.public_health))
        healthList.add(BeautyModel("Lab Tests", R.drawable.public_health))
        healthList.add(BeautyModel("Pharmacy Delivery", R.drawable.public_health))




    var automotiveList = ArrayList<BeautyModel>()
        automotiveList.add(BeautyModel("Car Wash", R.drawable.car_services))
        automotiveList.add(BeautyModel("Car Repair", R.drawable.car_services))
        automotiveList.add(BeautyModel("Car Rental", R.drawable.car_services))

        automotiveList.add(BeautyModel("Driver on Demand", R.drawable.car_services))
        automotiveList.add(BeautyModel("Towing Services", R.drawable.car_services))




    var personalList = ArrayList<BeautyModel>()
        personalList.add(BeautyModel("Personal Shopping", R.drawable.personalized))
        personalList.add(BeautyModel("Laundry Services", R.drawable.personalized))
        personalList.add(BeautyModel("Courier Services", R.drawable.personalized))
        personalList.add(BeautyModel("Personal Assistant", R.drawable.personalized))
        personalList.add(BeautyModel("Errand Services", R.drawable.personalized))


   var homeImprovementList = ArrayList<BeautyModel>()
        homeImprovementList.add(BeautyModel("Interior Designing", R.drawable.house_cleaning))
        homeImprovementList.add(BeautyModel("Renovation", R.drawable.house_cleaning))
        homeImprovementList.add(BeautyModel("Pest Control", R.drawable.house_cleaning))
        homeImprovementList.add(BeautyModel("Gardening", R.drawable.house_cleaning))
        homeImprovementList.add(BeautyModel("Home Automation", R.drawable.house_cleaning))




   var businessList = ArrayList<BeautyModel>()
        businessList.add(BeautyModel("Accounting", R.drawable.busibess_responsible))
        businessList.add(BeautyModel("Legal Services", R.drawable.busibess_responsible))
        businessList.add(BeautyModel("IT Support", R.drawable.busibess_responsible))
        businessList.add(BeautyModel("Marketing", R.drawable.busibess_responsible))
        businessList.add(BeautyModel("Consulting", R.drawable.busibess_responsible))




   var techList = ArrayList<BeautyModel>()
        techList.add(BeautyModel("Software Installation", R.drawable.tech_support))
        techList.add(BeautyModel("Network Setup", R.drawable.tech_support))
        techList.add(BeautyModel("Device Troubleshooting", R.drawable.tech_support))
        techList.add(BeautyModel("Data Recovery", R.drawable.tech_support))
        techList.add(BeautyModel("Cybersecurity", R.drawable.tech_support))

        var newList = ArrayList<BeautyModel>()

        when(mPosition)
        {
            "0"->{
                newList = homeList
            }
            "1"->{
                newList = repairsList
            }
            "2"->{
                newList = educationList
            }
            "3"->{
                newList = healthList
                }
            "4"->{
                newList = automotiveList
            }
            "5"->{
                newList = personalList
            }
            "6"->{
                newList = homeImprovementList
            }
            "7"->{
                newList = businessList
            }
            "8"->{
                newList = techList
            }
            else->{
                newList = homeList
            }
        }

        binding.rvBeauty.adapter = BeautyAdapter(this,newList)


    }
}