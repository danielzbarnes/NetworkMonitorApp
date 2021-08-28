package com.danielzbarnes.networkmonitorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import com.danielzbarnes.networkmonitorapp.network.NetworkLiveData

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        NetworkLiveData.init(application)

        setContent {
            MyScreen()
        }

        /*
        setContentView(R.layout.activity_main)
        val statusTextView = findViewById<TextView>(R.id.status)

        NetworkLiveData.observe(this, Observer {
            if (it)
                statusTextView.text = getString(R.string.connected)
            else statusTextView.text = getString(R.string.disconnected)
        })
        */
    }
}

@Composable
private fun MyScreen(mainActivityViewModel: MainActivityViewModel = MainActivityViewModel()){

    val status by mainActivityViewModel.getNetworkLiveData().observeAsState(initial = false)

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        StatusDisplay(when (status) {
            true -> "Network Status: Connected"
            false -> "Network Status: Disconnected"
        } )
    }
}

@Composable
private fun StatusDisplay(message: String){
    Text(text = message)
}
