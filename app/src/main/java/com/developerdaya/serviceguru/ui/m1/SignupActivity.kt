package com.developerdaya.serviceguru.ui.m1

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.text.method.PasswordTransformationMethod
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.developerdaya.serviceguru.R
import com.developerdaya.serviceguru.databinding.ActivitySignupBinding
import com.developerdaya.serviceguru.ui.m1.adapter.CategoryAdapter
import com.developerdaya.serviceguru.ui.m1.adapter.DocumentAdapter
import com.developerdaya.serviceguru.ui.m1.adapter.OnClick
import com.home.genie.ui.moveActivityData
import com.home.genie.ui.showToast
import com.developerdaya.serviceguru.util.ErrorUtil
import com.developerdaya.serviceguru.util.moveActivity
import com.developerdaya.serviceguru.util.showToastC
import com.home.genie.viewModel.M1ViewModel


class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    var isPasswordVisible = false
    var isPasswordVisible2 = false
    var isCheck = false
    lateinit var m1ViewModel: M1ViewModel
    var REQUEST_LOCATION = 101
    var REQUEST_CAMERA = 102
    var type = 0
    var imagesList = ArrayList<Bitmap?>()
    var documentAdapter:DocumentAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initControl()
        observer()

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_LOCATION) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    var mLatitude = data.getStringExtra("latitude").toString()
                    var mLongitude = data.getStringExtra("longitude").toString()
                    var mAddress = data.getStringExtra("address").toString()
                    binding.mAddress.setText("$mAddress")
                }
            }

        }

        if (requestCode == REQUEST_CAMERA && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap?
            when(type)
            {
                4->{
                binding.mProfileIcon.setImageBitmap(imageBitmap)
                }
                else->
                {
                    imagesList[type]  = imageBitmap
                    documentAdapter?.notifyDataSetChanged()

                }




            }


        }
    }



    private fun observer() {
        m1ViewModel.mSignupResp.observe(this) {
          OtpActivity.PHONE_NUMBER = it.data.mobileNumber
          OtpActivity.COUNTRY_CODE = it.data.code
            showToast("Work on Progress...")
        //    moveActivityData(OtpActivity(), "signup")
        }
        m1ViewModel.mError.observe(this) {
            ErrorUtil.handlerGeneralError(this, it)
        }
        m1ViewModel.isLoading.observe(this) {
         //   if (it) showProgress() else hideProgress()

        }
    }
    fun askPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 111)
    }

    fun openCamera()
    {
        askPermission()
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED))
        {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (takePictureIntent.resolveActivity(packageManager) != null)
            {
                startActivityForResult(takePictureIntent, REQUEST_CAMERA)
            }
        }
    }

     fun initControl()
     {
        binding.mAddIcon.setOnClickListener {
            type = 4
            openCamera()
        }


        binding.mAddress.setOnClickListener {
            val intent = Intent(this, ChangeLocationActivity::class.java)
            startActivityForResult(intent, REQUEST_LOCATION)

        }
        binding.mTermsAndConditions.setOnClickListener {
            if (binding.mTermsAndConditions.isChecked)
            {
                isCheck = true
            } else {
                isCheck = false
            }
        }
    }
    fun hideKeyboard()
    {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocusView = this.currentFocus
        if (currentFocusView != null) {
            imm.hideSoftInputFromWindow(currentFocusView.windowToken, 0)
        }

    }


    private fun initViews() {
        repeat(4)
        {
            imagesList.add(null)
        }
        var list = arrayListOf("Home Appliance","AC Services","Cars","Mobile","Electronics","Fashion")
        val list1 = arrayListOf("Home Cleaning","Mobile Repair","Ac Repair","Vehicle Repair","Car Wash")
        binding.rvCategoryList.adapter = CategoryAdapter(this, list)
        binding.rvSubCategoryList.adapter = CategoryAdapter(this, list1)
        documentAdapter = DocumentAdapter(this, imagesList, onClick = object : OnClick {
            override fun onClick(position: Int) {
                type =position
                openCamera()
            }
        })
        binding.rvDocs.adapter = documentAdapter



        m1ViewModel = ViewModelProvider(this)[M1ViewModel::class.java]
        binding.passEye.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            if (isPasswordVisible) {
                binding.mEnterPassword.transformationMethod = null
                binding.passEye.setImageResource(R.drawable.view)
                binding.mEnterPassword.setSelection(binding.mEnterPassword.length())
            } else {
                binding.mEnterPassword.transformationMethod = PasswordTransformationMethod()
                binding.passEye.setImageResource(R.drawable.hide)
                binding.mEnterPassword.setSelection(binding.mEnterPassword.length())
            }

        }
        binding.passEye2.setOnClickListener {
            isPasswordVisible2 = !isPasswordVisible2
            if (isPasswordVisible2) {
                binding.mEnterPassword2.transformationMethod = null
                binding.passEye2.setImageResource(R.drawable.view)
                binding.mEnterPassword2.setSelection(binding.mEnterPassword2.length())

            } else {
                binding.mEnterPassword2.transformationMethod = PasswordTransformationMethod()
                binding.passEye2.setImageResource(R.drawable.hide)
                binding.mEnterPassword2.setSelection(binding.mEnterPassword2.length())

            }

        }
        binding.btnSubmit.setOnClickListener {
            hideKeyboard()
            if (validation())
            {
                m1ViewModel.hitSignup(
                    firstName = binding.mFirstName.text.toString(),
                    lastName = binding.mLastName.text.toString(),
                    mobileNumber = binding.mEnterMobile.text.toString(),
                    email =binding.mValidEmail.text.toString(),
                    password = binding.mEnterPassword.text.toString(),
                    deviceToken = "asdf",
                    deviceType = 2,
                    code = "+91")

            }

        }
    }

    private fun validation(): Boolean {
        if(binding.mFirstName.text.toString().isEmpty())
        {
            showToast("Enter First Name")
            return false
        }
        if(binding.mLastName.text.toString().isEmpty())
        {
            showToast("Enter Last Name")
            return false
        }
        if(binding.mValidEmail.text.toString().isEmpty()){
            showToast("Enter Email")
            return false
        }
        if (!binding.mValidEmail.text.toString().matches(Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))) {
            showToast("Enter Valid Email")
            return false
        }

        if(binding.mEnterMobile.text.toString().isEmpty()){
            showToast("Enter Mobile Number")
            return false
        }
        if(binding.mEnterMobile.text.toString().length<10){
            showToast("Enter Valid Mobile Number")
            return false

        }
        if(binding.mEnterPassword.text.toString().isEmpty()){
            showToast("Enter Password")
            return false
        }

        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+$).{6,20}$"
        if (!binding.mEnterPassword.text.toString().matches(passwordPattern.toRegex())) {
            showToast("Pass: 6-20 chars, number, uppercase, lowercase, special symbol")
            return false
        }
        if(binding.mEnterPassword2.text.toString().isEmpty()){
            showToast("Enter Confirm Password")
            return false
        }
        if (binding.mEnterPassword2.text.toString()!=(binding.mEnterPassword.text.toString()))
        {
            showToast("Password Not Match")
            return false
        }
        if (!isCheck)
        {
            showToast("Please Accept Terms and Conditions")
            return false
        }


        return true

    }
}