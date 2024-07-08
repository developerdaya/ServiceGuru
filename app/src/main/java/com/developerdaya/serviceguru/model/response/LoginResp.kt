package com.developerdaya.serviceguru.model.response


import com.google.gson.annotations.SerializedName

data class LoginResp(
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
        @SerializedName("email")
        val email: String,
        @SerializedName("first_name")
        val firstName: String,
        @SerializedName("image")
        val image: String,
        @SerializedName("isotp_verified")
        val isotpVerified: Boolean,
        @SerializedName("last_name")
        val lastName: String,
        @SerializedName("mobile_number")
        val mobileNumber: String,
        @SerializedName("quickblox_id")
        val quickbloxId: Any,
        @SerializedName("state")
        val state: String,
        @SerializedName("user_id")
        val userId: Int
    )
}