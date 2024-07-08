package com.developerdaya.serviceguru.util

import com.developerdaya.serviceguru.model.response.LoginResp
import com.developerdaya.serviceguru.model.response.OtpResp
import com.developerdaya.serviceguru.model.response.SignupResp
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {
    @FormUrlEncoded
    @POST(AppConstant.login)
    fun hitLogin(
        @Field("mobile_number") mobile_number: String,
        @Field("password") password: String,
        @Field("device_token") device_token: String,
        @Field("device_type") device_type: Int,
        @Field("code") code: String
    ): Observable<LoginResp>


    @FormUrlEncoded
    @POST(AppConstant.signup)
    fun hitSignup(
        @Field("first_name") first_name: String,
        @Field("last_name") last_name: String,
        @Field("mobile_number") mobile_number: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("device_token") device_token: String,
        @Field("device_type") device_type: Int,
        @Field("code") code: String,
    ) : Observable<SignupResp>



        @FormUrlEncoded
    @POST(AppConstant.verify_signup_otp)
    fun hitVerifySignupOtp(
        @Field("mobile_number") mobile_number: String,
        @Field("otp") otp: String,
    ) : Observable<OtpResp>




            @FormUrlEncoded
    @POST(AppConstant.verify_foget_otp)
    fun hitVerifyForget(
        @Field("mobile_number") mobile_number: String,
        @Field("otp") otp: String,
    ) : Observable<OtpResp>




            @FormUrlEncoded
    @POST(AppConstant.forget_password_otp)
    fun hitForgetPass(
        @Field("mobile_number") mobile_number: String,
        @Field("code") code: String,
    ) : Observable<OtpResp>





@FormUrlEncoded
    @POST(AppConstant.change_password)
    fun hitChangePassword(
        @Field("password") password: String,
        @Field("secret_key") secret_key: String,
    ) : Observable<OtpResp>










}