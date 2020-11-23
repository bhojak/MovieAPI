package com.bhupen.moviedisplaylist.common.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Checks if there is an active connection or not
 * - uses one way for pre android Q and another for sdk >= androidQ
 */
class ConnectivityChecker @Inject constructor(@ApplicationContext context: Context) {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun isNetworkActive(): Boolean {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            connectivityManager.activeNetworkInfo?.isConnected ?: false
        } else {
            var hasInternet = false
            connectivityManager.allNetworks.also { networks ->
                if (networks.isNotEmpty()) {
                    networks.forEach { network ->
                        connectivityManager.getNetworkCapabilities(network)?.let {
                            if (it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET))
                                hasInternet = true
                        }
                    }
                }
            }
            return hasInternet
        }
    }
}
