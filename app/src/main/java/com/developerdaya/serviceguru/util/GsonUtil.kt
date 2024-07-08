package com.developerdaya.serviceguru.util

import com.google.gson.Gson

object GsonUtil {

    var gson: Gson? = null
    fun getGsonInstance(): Gson {
        if (gson == null)
            gson = Gson()
        return gson!!
    }
}