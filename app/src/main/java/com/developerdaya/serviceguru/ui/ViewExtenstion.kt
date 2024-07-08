package com.home.genie.ui

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.moveActivity(activity: AppCompatActivity) {
    startActivity(Intent(this, activity::class.java))
}


fun AppCompatActivity.moveActivityData(activity: AppCompatActivity,msg:String) {
    startActivity(Intent(this, activity::class.java)
        .putExtra(msg, msg)
    )


}




fun AppCompatActivity.showToast(msg:String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}