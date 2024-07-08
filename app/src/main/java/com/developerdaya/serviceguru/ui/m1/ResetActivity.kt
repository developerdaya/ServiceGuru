package com.developerdaya.serviceguru.ui.m1

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.developerdaya.serviceguru.R
import com.developerdaya.serviceguru.databinding.ActivityResetBinding
import com.developerdaya.serviceguru.databinding.PasswordChangeDialogBinding
import com.home.genie.ui.moveActivity
import com.home.genie.ui.showToast
import com.developerdaya.serviceguru.util.ErrorUtil
import com.home.genie.viewModel.M1ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ResetActivity : AppCompatActivity() {
    lateinit var binding: ActivityResetBinding
    private var isPasswordVisible = false
    private var isPasswordVisible2 = false
    lateinit var m1ViewModel: M1ViewModel
    companion object {
        var SECRET_KEY = ""
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        observer()

    }

    private fun observer() {
        m1ViewModel.mChangePasswordResp.observe(this) {
            showDialogPassword(this)
        }
        m1ViewModel.mError.observe(this) {
            ErrorUtil.handlerGeneralError(this, it)
        }
        m1ViewModel.isLoading.observe(this) {
          //  if (it) showProgress() else hideProgress()

        }
    }
    fun showDialogPassword(context: Context)
    {
        val binding = PasswordChangeDialogBinding.inflate(LayoutInflater.from(context))
        val mBuilder = AlertDialog.Builder(context)
            .setView(binding.root)
            .create()
        mBuilder.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mBuilder.setCancelable(false)
        mBuilder.show()
        CoroutineScope(Dispatchers.Main).launch {
            delay(4000)
            mBuilder.dismiss()
            moveActivity(LoginActivity())
            finish()
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

         fun validation(): Boolean {
        val password = binding.mEnterPassword.text.toString()
        val password2 = binding.mEnterPassword2.text.toString()
        if (password.isEmpty()) {
            showToast("Enter Password")
            return false
        }

        val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+$).{6,20}$"
       if (!password.matches(passwordPattern.toRegex())) {
                 showToast("Pass: 6-20 chars, number, uppercase, lowercase, special symbol")
                 return false
             }

             if (password2.isEmpty()) {
            showToast("Enter Confirm Password")
            return false
        }
        if (password != password2) {
            showToast("Password not match")
            return false
        }
        return true


    }

    private fun initViews() {
        m1ViewModel   = ViewModelProvider(this)[M1ViewModel::class.java]
        binding.mEnterPassword2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString()==binding.mEnterPassword.text.toString())
                {
                    binding.btnSubmit.background =
                        resources.getDrawable(R.drawable.bg_round_green)
                    binding.btnSubmit.setTextColor(resources.getColor(R.color.white))
                } else {
                    binding.btnSubmit.background =
                        resources.getDrawable(R.drawable.bg_round_button)
                    binding.btnSubmit.setTextColor(resources.getColor(R.color.gray))
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        binding.btnSubmit.setOnClickListener {
            hideKeyboard()
            if (validation())
            {
                m1ViewModel.hitChangePassword(binding.mEnterPassword2.text.toString(), SECRET_KEY)


            }
        }
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
    }

}