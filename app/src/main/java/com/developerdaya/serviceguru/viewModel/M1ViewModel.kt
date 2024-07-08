package com.home.genie.viewModel

import androidx.lifecycle.MutableLiveData
import com.developerdaya.serviceguru.base.BaseViewModel
import com.developerdaya.serviceguru.model.response.LoginResp
import com.developerdaya.serviceguru.model.response.OtpResp
import com.developerdaya.serviceguru.model.response.SignupResp
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class M1ViewModel : BaseViewModel() {
    lateinit var disposable: Disposable
    var mError = MutableLiveData<Throwable>()
    var isLoading = MutableLiveData<Boolean>()
    val mLoginResp = MutableLiveData<LoginResp>()
    val mOtpVerifySignupResp = MutableLiveData<OtpResp>()
    val mOtpVerifyForgetResp = MutableLiveData<OtpResp>()
    val mForgetPass = MutableLiveData<OtpResp>()
    val mChangePasswordResp = MutableLiveData<OtpResp>()
    val mSignupResp = MutableLiveData<SignupResp>()

    fun hitVerifySignupOtp(
        mobile_number: String,
        code: String
    ) {
        disposable = api.hitVerifySignupOtp(mobile_number, code)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                isLoading.value = true
            }.doOnTerminate {
                isLoading.value = false
            }.subscribe({
                mOtpVerifySignupResp.value = it
            }, {
                mError.value = it
            })
    }


        fun hitVerifyForget(
        mobile_number: String,
        code: String
    ) {
        disposable = api.hitVerifyForget(mobile_number, code)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                isLoading.value = true
            }.doOnTerminate {
                isLoading.value = false
            }.subscribe({
                mOtpVerifyForgetResp.value = it
            }, {
                mError.value = it
            })
    }


        fun hitForgetPass(
        mobile_number: String,
        code: String
    ) {
        disposable = api.hitForgetPass(mobile_number, code)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                isLoading.value = true
            }.doOnTerminate {
                isLoading.value = false
            }.subscribe({
                mForgetPass.value = it
            }, {
                mError.value = it
            })
    }






        fun hitChangePassword(
            password: String,
            secret_key: String
    ) {
        disposable = api.hitChangePassword(password , secret_key )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                isLoading.value = true
            }.doOnTerminate {
                isLoading.value = false
            }.subscribe({
                mChangePasswordResp.value = it
            }, {
                mError.value = it
            })
    }





    fun hitLogin(
        mobile_number: String,
        password: String,
        device_token: String,
        device_type: Int,
        code: String
    ) {
        disposable = api.hitLogin(mobile_number, password, device_token, device_type, code)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe {
                isLoading.value = true
            }.doOnTerminate {
                isLoading.value = false
            }.subscribe({
                mLoginResp.value = it
            }, {
                mError.value = it
            })
    }



    fun hitSignup(
        firstName: String,
        lastName: String,
        mobileNumber: String,
        email: String,
        password: String,
        deviceToken: String,
        deviceType: Int,
        code: String,
    ) {
        disposable = api.hitSignup(
            firstName,
            lastName,
            mobileNumber,
            email,
            password,
            deviceToken,
            deviceType,
            code)
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe {
                isLoading.value = true
            }.doOnTerminate {
                isLoading.value = false
            }.subscribe({
                mSignupResp.value = it
            }, {
                mError.value = it
            })
    }





}