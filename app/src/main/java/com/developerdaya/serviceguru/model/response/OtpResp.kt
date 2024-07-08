package com.developerdaya.serviceguru.model.response


import com.google.gson.annotations.SerializedName

data class OtpResp(
    val message: String,
    val secret_key: String,
    val token: String,

)