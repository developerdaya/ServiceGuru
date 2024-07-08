package com.developerdaya.serviceguru.ui.m1

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.developerdaya.serviceguru.R
import com.developerdaya.serviceguru.databinding.ActivityOtpBinding
import com.developerdaya.serviceguru.databinding.OtpVerifyDialogBinding
import com.developerdaya.serviceguru.databinding.ProfileCreatedDialogBinding
import com.home.genie.ui.moveActivity
import com.home.genie.ui.showToast
import com.developerdaya.serviceguru.util.ErrorUtil
import com.developerdaya.serviceguru.util.SPreferenceUtils
import com.home.genie.viewModel.M1ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class OtpActivity : AppCompatActivity() {
    lateinit var binding: ActivityOtpBinding
    lateinit var m1ViewModel: M1ViewModel

    companion object {
        var PHONE_NUMBER = ""
        var COUNTRY_CODE = ""
    }

    fun hideKeyboard()
    {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocusView = this.currentFocus
        if (currentFocusView != null) {
            imm.hideSoftInputFromWindow(currentFocusView.windowToken, 0)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        binding.otpText.text = "Enter 6 Digit OTP sent on $COUNTRY_CODE $PHONE_NUMBER"
        initControls()
        observer()


    }

    fun showDialogSignup(context: Context)
    {
        val binding = ProfileCreatedDialogBinding.inflate(LayoutInflater.from(context))
        val mBuilder = AlertDialog.Builder(context)
            .setView(binding.root)
            .create()
        mBuilder.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mBuilder.setCancelable(false)
        mBuilder.show()
        CoroutineScope(Dispatchers.Main).launch {
            delay(4000)
            mBuilder.dismiss()
            moveActivity(HomeActivity())
            finishAffinity()
        }

    }

    fun showDialogOtpVerified(context: Context)
    {
        val binding = OtpVerifyDialogBinding.inflate(LayoutInflater.from(context))
        val mBuilder = AlertDialog.Builder(context)
            .setView(binding.root)
            .create()
        mBuilder.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mBuilder.setCancelable(false)
        mBuilder.show()
        CoroutineScope(Dispatchers.Main).launch {
            delay(4000)
            mBuilder.dismiss()
            moveActivity(ResetActivity())
            finish()
        }



    }


    private fun observer() {
        m1ViewModel.mOtpVerifySignupResp.observe(this) {
           SPreferenceUtils.getInstance(this).accessToken =it.token
            SPreferenceUtils.getInstance(this).isLogin =true
            showDialogSignup(this)
        }
        m1ViewModel.mOtpVerifyForgetResp.observe(this) {
            ResetActivity.SECRET_KEY = it.secret_key
            showDialogOtpVerified(this)

        }
        m1ViewModel.mError.observe(this) {
            ErrorUtil.handlerGeneralError(this, it)
        }
        m1ViewModel.isLoading.observe(this) {
          //  if (it) showProgress() else hideProgress()

        }
    }

    private fun initViews() {
        m1ViewModel = ViewModelProvider(this)[M1ViewModel:: class.java]
    }

    private fun initControls() {
        var mTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                var sec = millisUntilFinished / 1000
                var min = sec / 60
                sec %= 60
                binding.otpText2.text = "Resend OTP in 0$min:$sec"
                if (sec<10)
                {
                    binding.otpText2.text = "Resend OTP in 0$min:0$sec"
                }
                else{
                    binding.otpText2.text = "Resend OTP in 0$min:$sec"
                }
                binding.otpText2.setTextColor(resources.getColor(R.color.dark_green))
                binding.otpText2.isClickable = false

            }
            override fun onFinish() {
                binding.otpText2.isClickable = true
                binding.otpText2.text = "Resend OTP"
                binding.otpText2.setTextColor(resources.getColor(R.color.dark_green))
            }
        }
        mTimer.start()

        binding.otpText2.setOnClickListener {
            mTimer.start()
            showToast("OTP Resend successfully")
        }

        binding.btnSubmit.setOnClickListener {
            hideKeyboard()
            if (validation()) {
                if (intent.hasExtra("signup"))
                {
                    m1ViewModel.hitVerifySignupOtp(PHONE_NUMBER, binding.pinViewOtp.text.toString())

                }
                else
                {
                  m1ViewModel.hitVerifyForget(PHONE_NUMBER, binding.pinViewOtp.text.toString())
                }

            }
        }



        binding.pinViewOtp.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().length == 6) {
                    binding.btnSubmit.background = resources.getDrawable(R.drawable.bg_round_green)
                    binding.btnSubmit.setTextColor(resources.getColor(R.color.white))
                } else {
                    binding.btnSubmit.background = resources.getDrawable(R.drawable.bg_round_button)
                    binding.btnSubmit.setTextColor(resources.getColor(R.color.gray))
                }

            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun validation(): Boolean {
        if (binding.pinViewOtp.text.toString().isEmpty()) {
            showToast("Please enter OTP")
            return false
        }
        if (binding.pinViewOtp.text.toString().length != 6) {
            showToast("Invalid OTP")
            return false
        }


        return true


    }
}