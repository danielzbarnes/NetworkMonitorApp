package com.danielzbarnes.networkmonitorapp.network

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import androidx.lifecycle.LiveData

private const val TAG = "networkLiveData"

object NetworkLiveData: LiveData<Boolean>(){

    private lateinit var application: Application
    private lateinit var networkRequest: NetworkRequest
    private lateinit var connectivityManager: ConnectivityManager
    private lateinit var networkCallback: ConnectivityManager.NetworkCallback

    fun init(application: Application){

        this.application = application
        connectivityManager = NetworkLiveData.application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        networkCallback = setupNetworkCallback()

        networkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
    }

    private fun setupNetworkCallback(): ConnectivityManager.NetworkCallback =

        object : ConnectivityManager.NetworkCallback(){

            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                Log.d(TAG, "onAvailable()")
                postValue(true)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                Log.d(TAG, "onLost()")
                postValue(false)
            }

            override fun onUnavailable() {
                super.onUnavailable()
                Log.d(TAG, "onUnavailable()")
                postValue(false)
            }
        }

    override fun onActive() {
        super.onActive()
        Log.d(TAG, "onActive()")
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    override fun onInactive() {
        super.onInactive()
        Log.d(TAG, "onInactive()")
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}