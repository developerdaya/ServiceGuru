package com.developerdaya.serviceguru.ui.m1

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.developerdaya.serviceguru.R
import com.developerdaya.serviceguru.databinding.ActivityForgetBinding
import com.home.genie.ui.moveActivity
import com.home.genie.ui.showToast
import com.developerdaya.serviceguru.util.ErrorUtil
import com.home.genie.viewModel.M1ViewModel

class ForgetActivity : AppCompatActivity() {
    lateinit var binding: ActivityForgetBinding
    lateinit var m1ViewModel: M1ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityForgetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        observer()

    }

    fun hideKeyboard()
    {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocusView = this.currentFocus
        if (currentFocusView != null) {
            imm.hideSoftInputFromWindow(currentFocusView.windowToken, 0)
        }

    }

    private fun observer() {
        m1ViewModel.mForgetPass.observe(this) {
            OtpActivity.PHONE_NUMBER = "${binding.mEnterMobile.text.toString()}"
            OtpActivity.COUNTRY_CODE = "+91"
            moveActivity(OtpActivity())
        }
        m1ViewModel.mError.observe(this) {
            ErrorUtil.handlerGeneralError(this, it)
        }
        m1ViewModel.isLoading.observe(this) {
         //   if (it) showProgress() else hideProgress()

        }
    }

    private fun validation() :Boolean{
        if (binding.mEnterMobile.text.toString().isEmpty()) {
            showToast("Enter mobile number")

            return false
        } else if (binding.mEnterMobile.text.toString().length != 10) {
            showToast("Enter valid mobile number")
            return false
        }
        else {
            return true
        }
    }


    private fun initViews() {
        m1ViewModel  = ViewModelProvider(this)[M1ViewModel::class.java]
        binding.mSubmit.setOnClickListener {
            hideKeyboard()
            if (validation())
            {
                m1ViewModel.hitForgetPass(binding.mEnterMobile.text.toString(),"+91")

            }

        }
        binding.mEnterMobile.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().length == 10) {
                    binding.mSubmit.background =
                        resources.getDrawable(R.drawable.bg_round_green)
                    binding.mSubmit.setTextColor(resources.getColor(R.color.white))
                } else {
                    binding.mSubmit.background =
                        resources.getDrawable(R.drawable.bg_round_button)
                    binding.mSubmit.setTextColor(resources.getColor(R.color.gray))
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

    }
}