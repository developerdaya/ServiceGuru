package com.developerdaya.serviceguru.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.developerdaya.serviceguru.util.AppConstant.PERSISTABLE_PREFRENCE_NAME


class SPreferenceUtils
private constructor(val context: Context) {
    val TAG = SPreferenceUtils::class.java.simpleName!!

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(PERSISTABLE_PREFRENCE_NAME, Context.MODE_PRIVATE)
    /* private val persistableSharedPreferences: SharedPreferences = context.getSharedPreferences(Constant.PERSISTABLE_PREFRENCE_NAME, Context.MODE_PRIVATE)*/

    private val editor: SharedPreferences.Editor = sharedPreferences.edit()
    /* private val persistableEditor: SharedPreferences.Editor = persistableSharedPreferences.edit()*/

    companion object {

        @SuppressLint("StaticFieldLeak")
        private var instance: SPreferenceUtils? = null

        fun getInstance(ctx: Context): SPreferenceUtils {
            if (instance == null) {
                instance = SPreferenceUtils(ctx)
            }
            return instance!!
        }
    }

    var deviceToken: String
        get() = sharedPreferences["deviceToken"]!!
        set(value) = sharedPreferences.set("deviceToken", value)

       var sector: String
        get() = sharedPreferences["sector"]!!
        set(value) = sharedPreferences.set("sector", value)



           var buildingNo: String
        get() = sharedPreferences["buildingNo"]!!
        set(value) = sharedPreferences.set("buildingNo", value)




            var city: String
        get() = sharedPreferences["city"]!!
        set(value) = sharedPreferences.set("city", value)


           var state: String
        get() = sharedPreferences["state"]!!
        set(value) = sharedPreferences.set("state", value)



            var postalCode: String
        get() = sharedPreferences["postalCode"]!!
        set(value) = sharedPreferences.set("postalCode", value)



       var countryName: String
        get() = sharedPreferences["countryName"]!!
        set(value) = sharedPreferences.set("countryName", value)



     var addressLocation: String
        get() = sharedPreferences["addressLocation"]!!
        set(value) = sharedPreferences.set("addressLocation", value)

    var latitude: String
        get() = sharedPreferences["latitude",""]!!
        set(value) = sharedPreferences.set("latitude", value)

    var longitude: String
        get() = sharedPreferences["longitude",""]!!
        set(value) = sharedPreferences.set("longitude", value)








    var is_mobileVerified: Boolean
        get() = sharedPreferences["is_mobileVerified",false]!!
        set(value) = sharedPreferences.set("is_mobileVerified", value)


    var isLogin: Boolean
        get() = sharedPreferences["isLogin", false]!!
        set(value) = sharedPreferences.set("isLogin", value)


    var isAccount_Private_On: Boolean
        get() = sharedPreferences["isAccount_Private_On", false]!!
        set(value) = sharedPreferences.set("isAccount_Private_On", value)

    var isAllowComment: Boolean
        get() = sharedPreferences["isAllowComment", false]!!
        set(value) = sharedPreferences.set("isAllowComment", value)

    var isScheDate: Boolean
        get() = sharedPreferences["isScheDate", false]!!
        set(value) = sharedPreferences.set("isScheDate", value)

    var name: String
        get() = sharedPreferences["name",""]!!
        set(value) = sharedPreferences.set("name", value)

        var firstName: String
        get() = sharedPreferences["firstName",""]!!
        set(value) = sharedPreferences.set("firstName", value)



    var productName: String
        get() = sharedPreferences["productName",""]!!
        set(value) = sharedPreferences.set("productName", value)

    var UserOtp: String
        get() = sharedPreferences["UserOtp", ""]!!
        set(value) = sharedPreferences.set("UserOtp", value)


    var profile_image: String
        get() = sharedPreferences["profile_image",""]!!
        set(value) = sharedPreferences.set("profile_image", value)

    var accessToken: String
        get() = sharedPreferences["accessToken", ""]!!
        set(value) = sharedPreferences.set("accessToken", value)

    var email_id: String
        get() = sharedPreferences["email_id",""]!!
        set(value) = sharedPreferences.set("email_id", value)

    var mobileNumber: String
        get() = sharedPreferences["mobileNumber", ""]!!
        set(value) = sharedPreferences.set("mobileNumber", value)

    var countryCode: String
        get() = sharedPreferences["countryCode", ""]!!
        set(value) = sharedPreferences.set("countryCode", value)

   var otp: String
        get() = sharedPreferences["otp", ""]!!
        set(value) = sharedPreferences.set("otp", value)

   var otpexpiretime: String
        get() = sharedPreferences["otpexpiretime", ""]!!
        set(value) = sharedPreferences.set("otpexpiretime", value)

    var _id: String
        get() = sharedPreferences["_id", ""]!!
        set(value) = sharedPreferences.set("_id", value)




  var nextUser: String
        get() = sharedPreferences["nextUser", ""]!!
        set(value) = sharedPreferences.set("nextUser", value)

        var roleId: String
        get() = sharedPreferences["roleId", ""]!!
        set(value) = sharedPreferences.set("roleId", value)

        var is_ProfileCreated: Boolean
        get() = sharedPreferences["is_ProfileCreated", false]!!
        set(value) = sharedPreferences.set("is_ProfileCreated", value)

       var selectedAddress: Int
        get() = sharedPreferences["selectedAddress",1]?:1
        set(value) = sharedPreferences.set("selectedAddress", value)




       var login_first: Int
        get() = sharedPreferences["login_first",0]!!
        set(value) = sharedPreferences.set("login_first", value)

        var createPostTypeA: String
        get() = sharedPreferences["CreatePostTypeA",""]!!
        set(value) = sharedPreferences.set("CreatePostTypeA", value)

        var createPostTypeB: String
        get() = sharedPreferences["CreatePostTypeB",""]!!
        set(value) = sharedPreferences.set("CreatePostTypeB", value)

        var createPostTypeC: String
        get() = sharedPreferences["CreatePostTypeC",""]!!
        set(value) = sharedPreferences.set("CreatePostTypeC", value)

        var createPostTypeD: String
        get() = sharedPreferences["CreatePostTypeD",""]!!
        set(value) = sharedPreferences.set("CreatePostTypeD", value)

       var createPostTypeE: String
        get() = sharedPreferences["CreatePostTypeE",""]!!
        set(value) = sharedPreferences.set("CreatePostTypeE", value)

        var createPostTypeF: String
        get() = sharedPreferences["CreatePostTypeF",""]!!
        set(value) = sharedPreferences.set("CreatePostTypeF", value)

        var createPostTypeG: String
        get() = sharedPreferences["CreatePostTypeG",""]!!
        set(value) = sharedPreferences.set("CreatePostTypeG", value)



    var is_socialProfileVerified: Boolean
        get() = sharedPreferences["is_socialProfileVerified",false]!!
        set(value) = sharedPreferences.set("is_socialProfileVerified", value)

    var test_Publishable_key: String
        get() = sharedPreferences["test_Publishable_key",""]!!
        set(value) = sharedPreferences.set("test_Publishable_key", value)

    var live_Publishable_key: String
        get() = sharedPreferences["live_Publishable_key",""]!!
        set(value) = sharedPreferences.set("live_Publishable_key", value)

    var cartCount: Int?
        get() = sharedPreferences["cartCount",0]
        set(value) = sharedPreferences.set("cartCount", value)

    /**
     * puts a key value pair in shared prefs if doesn't exists, otherwise updates value on given [key]
     */
    operator fun SharedPreferences.set(key: String, value: Any?) {
        when (value) {
            is String? -> edit({ it.putString(key, value) })
            is Int -> edit({ it.putInt(key, value) })
            is Boolean -> edit({ it.putBoolean(key, value) })
            is Float -> edit({ it.putFloat(key, value) })
            is Long -> edit({ it.putLong(key, value) })
            else -> Log.e(TAG, "Setting shared pref failed for key: $key and value: $value ")
        }
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    /**
     * finds value on given key.
     * [T] is the type of value
     * @param defaultValue optional default value - will take null for strings, false for bool and -1 for numeric values if [defaultValue] is not specified
     */
    inline operator fun <reified T : Any> SharedPreferences.get(key: String, defaultValue: T? = null): T? {
        return when (T::class) {
            String::class -> getString(key, defaultValue as? String) as T?
            Int::class -> getInt(key, defaultValue as? Int ?: -1) as T?
            Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T?
            Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T?
            Long::class -> getLong(key, defaultValue as? Long ?: -1) as T?
            else -> throw UnsupportedOperationException("Not yet implemented")
        }
    }

    fun deletePreferences() {
        editor.clear()
        editor.apply()
        editor.commit()
    }
}