package com.danielzbarnes.networkmonitorapp

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.danielzbarnes.networkmonitorapp.network.NetworkLiveData

// this ViewModel is largely unnecessary is only here for testing testing behavior
class MainActivityViewModel(): ViewModel() {

    fun getNetworkLiveData() = NetworkLiveData
}