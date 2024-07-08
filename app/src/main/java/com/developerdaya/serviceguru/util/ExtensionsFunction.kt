package com.developerdaya.serviceguru.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.text.TextUtils
import android.text.format.DateFormat
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.*
import java.lang.Exception
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.pow

// ProgressExtensions.kt
import androidx.fragment.app.FragmentActivity
import com.developerdaya.serviceguru.R

fun FragmentActivity.showProgress() {
    ProgressUtil.showProgress(this)
}

fun FragmentActivity.hideProgress() {
    ProgressUtil.hideProgress()
}

 fun roundToTwoDecimalPlaces(value: Double): Double {
    val scale = 2
    return kotlin.math.round(value * 10.0.pow(scale)) / 10.0.pow(scale)
}

fun formatTime(minutes: Int): String {
    return if (minutes < 60) {
        "$minutes min"
    } else {
        val hours = minutes / 60
        val remainingMinutes = minutes % 60
        if (remainingMinutes == 0) {
            "$hours hrs"
        } else {
            "$hours hrs $remainingMinutes min"
        }
    }
}


// To Get String With Trim From View


fun convertTimestampToTime(timestamp: Long): String {
    val date = Date(timestamp)
    val format = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return format.format(date)
}


fun convertTimestampToDate(timestamp: Long): String {
    val date = Date(timestamp)
    val format = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return format.format(date)
}


fun EditText.getString(): String {
    return this.text.toString().trim()
}

// To Get String With Trim From View
fun TextView.getString(): String {
    return this.text.toString()
}
fun mTimeStampToDate(value: Long):String
{
    var timestamp= value
    if (timestamp.toString().length<13)
    {
        timestamp*=1000
    }
    val sdf = SimpleDateFormat("dd MMM")
    return sdf.format(Date(timestamp))
}
fun formatDate3(inputDate: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    inputFormat.timeZone = TimeZone.getTimeZone("UTC")

    val outputFormat = SimpleDateFormat("dd MMMM yyyy | hh:mm a")

    try {
        val date = inputFormat.parse(inputDate)
        return outputFormat.format(date)
    } catch (e: Exception) {
        e.printStackTrace()
        return ""
    }
}

fun decimalFormat(digit:Double):String{
    val decimalFormat = DecimalFormat("#.##")
    val formattedNumber = decimalFormat.format(digit)
   return formattedNumber.toString()
}
fun getSelected(startDate:String):String {
    val inputDateString = startDate

    val inputFormat = SimpleDateFormat("dd-MMMM-yyyy", Locale.getDefault())
    val outputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())

    try {
        val date = inputFormat.parse(inputDateString)
        val formattedDate = outputFormat.format(date)
        return formattedDate

        println("Formatted date: $formattedDate")
    } catch (e: Exception) {
        e.printStackTrace()
        return ""
    }

}


fun formatDate(inputDate: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMM yyyy | hh:mm a", Locale.getDefault())
    val date: Date = inputFormat.parse(inputDate) ?: return ""
    return outputFormat.format(date)
}
fun formatDate2(inputDate: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    val date: Date = inputFormat.parse(inputDate) ?: return ""
    return outputFormat.format(date)
}
fun formatTime2(inputDate: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
    val date: Date = inputFormat.parse(inputDate) ?: return ""
    return outputFormat.format(date)
}
fun mTimeStampToDatee(value: Long):String
{
    var timestamp= value
    if (timestamp.toString().length<13)
    {
        timestamp*=1000
    }
    val sdf = SimpleDateFormat("dd MMM, yyyy")
    return sdf.format(Date(timestamp))
}

fun getUtcTime(date:String):String {
    val inputDateStr = date

    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    inputFormat.timeZone = TimeZone.getTimeZone("UTC")

    // Parse the input string to a Date object
    val inputDate = inputFormat.parse(inputDateStr)

    // Define the output format
    val outputFormat = SimpleDateFormat("dd-MMM")
    outputFormat.timeZone = TimeZone.getTimeZone("UTC")

    // Format the Date object to the desired output format
    val outputDateStr = outputFormat.format(inputDate)
    return outputDateStr
}

fun getUtcTime2(date: String): String {
    val inputDateStr = date
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    inputFormat.timeZone = TimeZone.getTimeZone("UTC")
    // Parse the input string to a Date object
    val inputDate = inputFormat.parse(inputDateStr)
    // Define the output format
    val outputFormat = SimpleDateFormat("dd-MMM, yyyy")
    outputFormat.timeZone = TimeZone.getTimeZone("UTC")
    // Format the Date object to the desired output format
    return outputFormat.format(inputDate)
}
fun getUtcTime3(date: String): String {
    val inputDateStr = date
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    inputFormat.timeZone = TimeZone.getTimeZone("UTC")
    // Parse the input string to a Date object
    val inputDate = inputFormat.parse(inputDateStr)
    // Define the output format
    val outputFormat = SimpleDateFormat("dd MMM yyyy")
    outputFormat.timeZone = TimeZone.getTimeZone("UTC")
    // Format the Date object to the desired output format
    return outputFormat.format(inputDate)
}


// To Show Toast
var toast: Toast? = null

fun View.goBack(){
    context?.let { c->
        setOnClickListener {
            (c as? Activity)?.finish()
        }
    }
}

fun AppCompatActivity.moveActivity(activity: Activity) {
    startActivity(Intent(this,activity::class.java))
}

fun AppCompatActivity.moveActivityWithNo(activity: Activity,number:Int) {
    startActivity(Intent(this,activity::class.java)
        .putExtra("page_no",number))
}

fun Fragment.moveActivityWithNoF(activity: Activity,number:Int) {
    startActivity(Intent(requireContext(),activity::class.java)
        .putExtra("page_no",number))
}


fun AppCompatActivity.showToast(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}


fun AppCompatActivity.showToastInternet() {
    Toast.makeText(this, "Please enable connections", Toast.LENGTH_SHORT).show()
}



fun File.toConvertMultipartBodyPart(keyName: String): MultipartBody.Part? = this.let {
    MultipartBody.Part.createFormData(
        keyName, it.name,
        it.asRequestBody("image/*".toMediaTypeOrNull())
    )
}

fun Fragment.showToastF(message: String?)
{
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

//for adapter and VM
fun showToastC(message:String,context: Context)
{
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}




fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}







//TimeZone
fun getTimeShow(jointDate: String): String {
    val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val past: Date = format.parse(jointDate)!!
    val currentDate = Date()
    val second: Long = TimeUnit.MILLISECONDS.toSeconds(currentDate.time - past.time)
    val minutes: Long = TimeUnit.MILLISECONDS.toMinutes(currentDate.time - past.time)
    val hours: Long = TimeUnit.MILLISECONDS.toHours(currentDate.time - past.time)
    val days: Long = TimeUnit.MILLISECONDS.toDays(currentDate.time - past.time)
    val month: Double = (days.toDouble() / 30)
    val properMonth = DecimalFormat("##.#").format(month)

    when {
        second < 60 -> {
            return "$second second ago"
        }

        minutes < 60 -> {
            return "$minutes minute ago"
        }

        hours < 24 -> {
            return "$hours hour ago"
        }

        days < 30 -> {
            return "$days day ago"
        }

        month < 12 -> {
            return "$properMonth month ago"
        }

        else -> {
            val year: Int = (month / 12).toInt()
            val properYear = DecimalFormat("##.#").format(year)
            return "$properYear year ago"
        }
    }
}

//Network Connection Available
/*fun isNetworkConnected(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
   // val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnectedOrConnecting
}*/

//EditText is Empty
@RequiresApi(Build.VERSION_CODES.M)
fun EditText.isEditTextEmpty(message: String): Boolean {
    return if (this.getString() == "") {
//        this.error = message
        onSnackToast(this, message)
        this.requestFocus()
        false
    } else
        true
}

//EditText is Empty
@RequiresApi(Build.VERSION_CODES.M)
fun TextView.isEditTextEmpty(message: String): Boolean {
    return if (this.text.toString() == "") {
//        this.error = message
        onSnackToast(this, message)
        this.requestFocus()
        false
    } else
        true
}
//TextView Equal
@RequiresApi(Build.VERSION_CODES.M)
fun TextView.isEqualData(message: String, data:String): Boolean {
    return if (this.text.toString() == message) {
//        this.error = message
        onSnackToast(this, message)
        this.requestFocus()
        false
    } else
        true
}

//Check Valid Email
@RequiresApi(Build.VERSION_CODES.M)
fun EditText.isValidEmailId(email: String): Boolean {
    return if (!Patterns.EMAIL_ADDRESS.matcher(this.text).matches()) {
        onSnackToast(this, email)
        this.requestFocus(this.getString().length)
        false
    } else
        true

}

fun AppCompatActivity.setStatusBarColor() {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        // window.setNavigationBarColor(this.resources.getColor(com.android.digestive.R.color.very_light_orange))
/*
        window.statusBarColor = this.resources.getColor(R.color.status)
*/
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

    }
}



@RequiresApi(Build.VERSION_CODES.M)
fun onSnackToast(view: View, message: String) {
    val snackbar = Snackbar.make(
        view, message,
        Snackbar.LENGTH_LONG
    )
    snackbar.setActionTextColor(Color.WHITE)
    val snackbarView = snackbar.view
    snackbarView.setBackgroundColor(Color.WHITE)
    val textView =
        snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
    textView.setTextColor(Color.WHITE)
    //textView.setTextAppearance(R.style.Text_Regular_14_White)
    snackbar.show()
}

fun AppCompatActivity.statusBarWhite() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        this.window.statusBarColor = this.resources.getColor(R.color.white)
        this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}

//Check Password Length
@RequiresApi(Build.VERSION_CODES.M)
fun EditText.checkEditTextLength(message: String): Boolean {
    return if (this.getString().length < 8) {
        //this.error = message
        onSnackToast(this, message)
        this.requestFocus(this.getString().length)

        false
    } else
        true
}


//This function show no internet connection
/*
fun showNoInternetDialog(context: Context) {
    val customDialog: CustomDialog = CustomDialog(
        context,
        R.layout.dialog_no_internet_connection,
        NetworkConstants.SHOW_No_Internet
    )

    customDialog.addMyDialogListener(object : CustomDialog.MyDialogListener {
        override fun onResult(position: Int) {
            when (position) {
                1 -> {
                    try{
                        val intent = Intent(Settings.ACTION_DATA_USAGE_SETTINGS)
                        context.startActivity(intent)
                        customDialog.dismiss()
                    }catch (e:Exception){

                    }

                }
            }
        }
    })

    customDialog.show()

}*/


//Check Password Sepecial Character
/*@RequiresApi(Build.VERSION_CODES.M)
fun EditText.checkSpecialCharacter(message: String): Boolean {
    return if (!DetectSpecial().isValidPassword(this.getString())) {
        //this.error = message
        onSnackToast(this, message)
        this.requestFocus()
        false
    } else
        true
}*/

//Password Show and Hide
fun EditText.showHidePassword(visible: Boolean) {
    if (visible) {
        this.transformationMethod = HideReturnsTransformationMethod.getInstance()
    } else {
        this.transformationMethod = PasswordTransformationMethod.getInstance()
    }
}
fun String.isValidEmail(): Boolean = this.isNotEmpty() &&
        Patterns.EMAIL_ADDRESS.matcher(this).matches()

/*fun TextView.setCustomDate() {
    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)
    val dpd = DatePickerDialog(
        this.context, R.style.DatePicker,
        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            val month = monthOfYear + 1
            var fromDate = "" + dayOfMonth + "-" + month + "-" + year
            val sdf = SimpleDateFormat("dd-MM-yyyy")
            try {
                val d = sdf.parse(fromDate)
                sdf.applyPattern("dd-MMM-yyyy")
                this.text = sdf.format(d).toString()


            } catch (ex: ParseException) {
                Log.v("Exception", ex.localizedMessage)
            }
        },
        year,
        month,
        day
    )
    if (dpd.window != null) {
        dpd.window!!.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dpd.window!!.setGravity(Gravity.CENTER)
    }
    dpd.datePicker.maxDate = System.currentTimeMillis()
    dpd.show()
}*/

fun getDate(long: String): String {

    val cal = Calendar.getInstance(Locale.ENGLISH);
    cal.timeInMillis = long.toLong()
    val date = DateFormat.format("dd-MMM-yyyy", cal).toString()
    return date;
}


fun getTime(long: String): String {

    val cal = Calendar.getInstance(Locale.ENGLISH);
    cal.timeInMillis = long.toLong()
    val date = DateFormat.format("hh:mm:a", cal).toString()
    return date;
}
fun getDateAndTime(long: String): String {

    val cal = Calendar.getInstance(Locale.ENGLISH);
    cal.timeInMillis = long.toLong()
    val date = DateFormat.format("dd-MMM-yyyy hh:mm:a", cal).toString()
    return date;
}

fun toRequestBody(data: String?): RequestBody? =
    data?.toRequestBody("text/plain".toMediaTypeOrNull())

fun getTimeStampLong(string: String): String {
    val sdf = SimpleDateFormat("dd-MMM-yyyy")
    val date = sdf.parse(string) as Date
    System.out.println("Today is " + date.time)
    return date.time.toString()
}


//Password Matching Function
@RequiresApi(Build.VERSION_CODES.M)
fun EditText.isPasswordMatch(password: String, message: String): Boolean {
    return if (this.getString() != password) {
//        this.error = message
        onSnackToast(this, message)
        this.requestFocus(this.getString().length)
        false
    } else
        true
}


fun AppCompatActivity.changeStatusBarColor() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val decor = this.window.decorView
        decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}

const val IMAGE_MAX_SIZE=1024
 fun compressImageFile(context: Context?, pathUri: Uri): File {
    var b: Bitmap? = null
    val realPath: String? = getRealPathFromURI(context!!, pathUri)
    val f: File = File(realPath)
    //Decode image size
    val o: BitmapFactory.Options = BitmapFactory.Options()
    o.inJustDecodeBounds = true
    var fis: FileInputStream
    try {
        fis = FileInputStream(f)
        BitmapFactory.decodeStream(fis, null, o)
        fis.close()
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    var scale = 1
    if (o.outHeight > IMAGE_MAX_SIZE || o.outWidth > IMAGE_MAX_SIZE) {
        scale = Math.pow(
            2.0,
            Math.ceil(
                Math.log(
                    IMAGE_MAX_SIZE / Math.max(
                        o.outHeight,
                        o.outWidth
                    ).toDouble()
                ) / Math.log(0.5)
            )
        ).toInt()
    }
    //Decode with inSampleSize
    val o2: BitmapFactory.Options = BitmapFactory.Options()
    o2.inSampleSize = scale
    try {
        fis = FileInputStream(f)
        b = BitmapFactory.decodeStream(fis, null, o2)
        fis.close()
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    val destFile = File(getImageFilePath())
    try {
        val out: FileOutputStream = FileOutputStream(destFile)
        b?.compress(Bitmap.CompressFormat.PNG, 90, out)
        out.flush()
        out.close()

    } catch (e: Exception) {
        e.printStackTrace()
    }
    return destFile
}

fun getRealPathFromURI(context: Context, contentUri: Uri?): String? {
    var contentUri = contentUri
    var cursor: Cursor?
    var filePath: String? = ""
    when (contentUri) {
        null -> return filePath
        else -> {
            val file = File(contentUri.path)
            when {
                file.exists() -> filePath = file.path
            }
            when {
                !TextUtils.isEmpty(filePath) -> return filePath
                else -> {
                    val proj = arrayOf(MediaStore.Images.Media.DATA)
                    when {
                        Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT -> {
                            try {
                                val wholeID = DocumentsContract.getDocumentId(contentUri)
                                val id: String
                                when {
                                    wholeID.contains(":") -> id =
                                        wholeID.split(":".toRegex()).dropLastWhile { it.isEmpty() }
                                            .toTypedArray()[1]
                                    else -> id = wholeID
                                }
                                cursor = context.contentResolver.query(
                                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                    proj,
                                    MediaStore.Images.Media._ID + "='" + id + "'",
                                    null,
                                    null
                                )
                                when {
                                    cursor != null -> {
                                        val columnIndex = cursor.getColumnIndex(proj[0])
                                        when {
                                            cursor.moveToFirst() -> filePath =
                                                cursor.getString(columnIndex)
                                        }
                                        when {
                                            !TextUtils.isEmpty(filePath) -> contentUri =
                                                Uri.parse(filePath)
                                        }
                                    }
                                }
                            } catch (e: IllegalArgumentException) {
                                e.printStackTrace()
                            }
                        }
                    }
                    when {
                        !TextUtils.isEmpty(filePath) -> return filePath
                        else -> {
                            try {
                                cursor = context.contentResolver.query(
                                    contentUri!!,
                                    proj,
                                    null,
                                    null,
                                    null
                                )
                                when {
                                    cursor == null -> return contentUri.path
                                    cursor.moveToFirst() -> filePath =
                                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                                }

                                when {
                                    !cursor!!.isClosed -> cursor.close()
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                                filePath = contentUri!!.path
                            }

                            when (filePath) {
                                null -> filePath = ""
                            }
                            return filePath
                        }
                    }

                }
            }

        }
    }

}

fun getImageFilePath(): String {
    val file =
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/CareerPortalApp")
    if (!file.exists()) {
        file.mkdirs()
    }
    return file.absolutePath + "/IMG_" + System.currentTimeMillis() + ".jpg"
}

fun convertFormFileToMultipartBodyList(key: String, file: ArrayList<File>?): ArrayList<MultipartBody.Part>? {

    var list = ArrayList<MultipartBody.Part>()
    for (i in 0 until file?.size!!) {
        list.add(file!![i].let {
            MultipartBody.Part.createFormData(
                key, it.name,
                it.asRequestBody("image/*".toMediaTypeOrNull())
            )

        })
    }
    return list
}

fun convertFormFileToMultipartBody(key: String, file: File?): MultipartBody.Part? = file?.let {
    MultipartBody.Part.createFormData(
        key, it.name,
        it.asRequestBody("image/*".toMediaTypeOrNull())
    )
}



fun AppCompatActivity.getImageUri(inImage: Bitmap): Uri {
    val bytes = ByteArrayOutputStream()
    inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
    val path = MediaStore.Images.Media.insertImage(
        this.contentResolver, inImage,
        "IMG_${System.currentTimeMillis()}", null
    )
    return Uri.parse(path)
}

fun getCombinedTimestamp(dateString: String, timeString: String): Long {
    val dateFormat = SimpleDateFormat("d MMM yyyy", Locale.ENGLISH)
    val timeFormat = SimpleDateFormat("hh:mm:a", Locale.ENGLISH)

    // Set the time zone to the one you desire
    dateFormat.timeZone = TimeZone.getTimeZone("UTC")
    timeFormat.timeZone = TimeZone.getTimeZone("UTC")

    val date = dateFormat.parse(dateString)
    val time = timeFormat.parse(timeString)

    // Combine date and time
    val combinedDateTime = Date(date.time + time.time)

    // Return timestamp
    return combinedDateTime.time
}


fun getCombinedTimestampTwo(dateString: String, timeString: String): Long {
    val dateFormat = SimpleDateFormat("d MMM yyyy", Locale.ENGLISH)
    val timeFormat = SimpleDateFormat("hh:mm:a", Locale.ENGLISH)

    // Set the time zone to the one you desire
    dateFormat.timeZone = TimeZone.getTimeZone("UTC")
    timeFormat.timeZone = TimeZone.getTimeZone("UTC")

    val date = dateFormat.parse(dateString)
    val time = timeFormat.parse(timeString)

    // Combine date and time
    var combinedDateTime = Date(date.time + time.time)

    // Subtract 5 hours and 30 minutes
    combinedDateTime.time -= (5 * 60 + 30) * 60 * 1000

    // Return timestamp
    return combinedDateTime.time
}


fun getTimeStampFromString(timeString: String): Long {
    val format = SimpleDateFormat("hh:mm:a", Locale.getDefault())
    val date = format.parse(timeString)

    // If date is null, return current time
    if (date == null) {
        return Calendar.getInstance().timeInMillis
    }

    return date.time
}

fun hideKeyboard(activity: Activity) {
    val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = activity.currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(activity)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}


/*
fun getTimeShow(jointDate: String, context: Context): String? {
    val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val past: Date = format.parse(jointDate)!!
    var time = past.time
    val now = System.currentTimeMillis()
    val secondMiles = 1000
    val minuteMiles = 60 * secondMiles
    val hourMiles = 60 * minuteMiles
    val dayMiles = 24 * hourMiles

    // if timestamp given in seconds, convert to millis
    if (time < 1000000000000L) {
        time *= 1000
    }

    if (time > now || time <= 0) {
        return null
    }

    val diff = now - time
    return if (diff < minuteMiles) {
        context.getString(R.string.just_now)
    } else if (diff < 2 * minuteMiles) {
        context.getString(R.string.a_minute_ago)
    } else if (diff < 50 * minuteMiles) {
        (diff / minuteMiles).toString() + " " + context.getString(R.string.minutes_ago)
    } else if (diff < 90 * minuteMiles) {
        context.getString(R.string.an_hour_ago)
    } else if (diff < 24 * hourMiles) {
        (diff / hourMiles).toString() + " " + context.getString(R.string.hours_ago)
    } else if (diff < 48 * hourMiles) {
        context.getString(R.string.yesterday)
    } else {
        (diff / dayMiles).toString() + " " + context.getString(R.string.days_ago)
    }
}*/

/*
fun createFormData(content: String?): RequestBody? =
    if (content != null) {
        RequestBody.create(MediaType.parse("text/plain"), content)
    } else {
        null
    }

fun createFormData(imageFile: File?, name: String, mimeType: String) = when (imageFile) {
    null -> null
    else -> MultipartBody.Part.createFormData(
        name,
        imageFile.name,
        RequestBody.create(MediaType.parse(mimeType), imageFile)
    )
}

fun createFormData(
    imageFiles: MutableList<File>?,
    name: String,
    mimeType: String? = null
): ArrayList<MultipartBody.Part>? {
    if (imageFiles == null || imageFiles.isEmpty()) return null
    return ArrayList<MultipartBody.Part>().apply {
        imageFiles.forEachIndexed { index, file ->

            val mime = if (mimeType == null) {
                var tampMimeType: String? = null
                try {
                    val extension = MimeTypeMap.getFileExtensionFromUrl(file.absolutePath)
                    if (extension != null) {
                        tampMimeType =
                            MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
                        Log.d("###", "mime: $mimeType")
                    }
                } catch (e: Exception) {
                }

                */
/*mimeType = file?.path?.getMimeType()!!*//*


                if (tampMimeType == null || tampMimeType.isEmpty()) {
                    tampMimeType = "application/*"
                }

                tampMimeType
            } else {
                mimeType
            }

            add(createFormData(file, "$name[$index]", mime)!!)
        }
    }
}*/