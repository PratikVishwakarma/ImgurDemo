package com.pratik.imgurdemo.utility

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.pratik.imgurdemo.R
import java.text.SimpleDateFormat
import java.util.*


/*
* util function to print the logs
* */
fun String.printLog() {
    Log.d("com.pratik.imgurdemo", this)
}

/*
* util function to show the toast
* */
fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

@BindingAdapter("android:imageUrl")
fun loadImage(view: ImageView, resource: String) {
    Glide.with(view.context)
        .load(resource)
        .override(view.width, view.height)
        .placeholder(R.mipmap.ic_launcher)
        .into(view)
}

fun Long.getDateByFormat(): String {
    return try {
        val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH)
        return formatter.format(this)
    } catch (e: Exception) {
        ""
    }
}

/*
* util function to check internet availability
* return : Boolean
* */
fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val capabilities =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
    if (capabilities != null) when {
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
            Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
            return true
        }
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
            Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
            return true
        }
        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
            Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
            return true
        }
    }
    return false
}

