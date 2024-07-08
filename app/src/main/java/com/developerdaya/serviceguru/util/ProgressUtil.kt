package com.developerdaya.serviceguru.util

// ProgressUtil.kt
import android.app.ProgressDialog
import android.content.Context

object ProgressUtil {
    private var progressDialog: ProgressDialog? = null

    fun showProgress(context: Context) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(context)
            progressDialog?.setMessage("Loading...")
            progressDialog?.setCancelable(false)
        }
        progressDialog?.show()
    }

    fun hideProgress() {
        progressDialog?.dismiss()
        progressDialog = null
    }
}
