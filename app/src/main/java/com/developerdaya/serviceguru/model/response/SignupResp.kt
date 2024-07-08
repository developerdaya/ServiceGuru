package com.developerdaya.serviceguru.model.response


import com.google.gson.annotations.SerializedName

data class SignupResp(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("token")
    val token: String
) {
    data class Data(
        @SerializedName("city")
        val city: String,
        @SerializedName("code")
        val code: String,
        @SerializedName("country")
        val country: Any,
        @SerializedName("device_token")
        val deviceToken: String,
        @SerializedName("device_type")
        val deviceType: String,
        @SerializedName("dob")
        val dob: Any,
        @SerializedName("email")
        val email: String,
        @SerializedName("first_name")
        val firstName: String,
        @SerializedName("image")
        val image: String,
        @SerializedName("last_name")
        val lastName: String,
        @SerializedName("mobile_number")
        val mobileNumber: String,
        @SerializedName("state")
        val state: String,
        @SerializedName("user_bio")
        val userBio: Any,
        @SerializedName("user_id")
        val userId: Int
    )
}