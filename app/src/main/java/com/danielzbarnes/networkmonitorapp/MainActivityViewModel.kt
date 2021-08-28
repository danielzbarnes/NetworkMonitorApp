package com.danielzbarnes.networkmonitorapp

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.danielzbarnes.networkmonitorapp.network.NetworkLiveData

class MainActivityViewModel(): ViewModel() {

    fun getNetworkLiveData() = NetworkLiveData
}