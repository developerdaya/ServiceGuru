package com.developerdaya.serviceguru.util

import android.content.Context
import android.view.LayoutInflater
import com.developerdaya.serviceguru.base.ErrorBean
import com.developerdaya.serviceguru.util.GsonUtil.getGsonInstance
import com.developerdaya.serviceguru.R
import com.developerdaya.serviceguru.databinding.ErrorDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ErrorUtil {
    fun handlerGeneralError(context: Context?, throwable: Throwable) {
        throwable.printStackTrace()
        if (context == null) return
        when (throwable) {
            is ConnectException -> {
                errorSheet(context, "Network Error PLease Try Later")
            }

            is SocketTimeoutException -> {
                errorSheet(context, "Connection Lost PLease Try Later")
            }

            is UnknownHostException, is InternalError -> {
                errorSheet(context, "Server Error PLease Try Later")
            }

            is HttpException -> {
                displayError(context, throwable)
            }

            else -> {
                errorSheet(context, "Something Went Wrong")
            }
        }
    }

    fun errorSheet(context: Context, msg: String) {
        val dialog = BottomSheetDialog(context, R.style.AppBottomSheetDialogTheme)
        val binding = ErrorDialogBinding.inflate(LayoutInflater.from(context))
        dialog.setContentView(binding.root)
        binding.mError.text = msg
        dialog.show()
    }

    fun displayError(context: Context, exception: HttpException) {
        try {
            val errorBody = getGsonInstance().fromJson(
                exception.response()!!.errorBody()?.charStream(),
                ErrorBean::class.java
            )
            errorSheet(context, errorBody.message)

        } catch (e: Exception) {
            errorSheet(context, "Something Went Wrong")
        }
    }
}

