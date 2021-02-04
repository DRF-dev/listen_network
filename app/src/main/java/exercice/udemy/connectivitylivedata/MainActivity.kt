package exercice.udemy.connectivitylivedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations

enum class Connectivity {
    DISCONECTED,
    CONNECTED
}

class MainActivity : AppCompatActivity() {
    private lateinit var connectivityLiveData: ConnectivityLiveData
    private lateinit var connectivityEnumLiveData: LiveData<Connectivity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        connectivityLiveData = ConnectivityLiveData(this)

        connectivityEnumLiveData = Transformations.map(connectivityLiveData) { connected ->
            when(connected) {
                true -> Connectivity.CONNECTED
                else -> Connectivity.DISCONECTED
            }
        }

        /* connectivityLiveData.observe(this, Observer { connected ->
            Log.i("MainActivity", "NetworkConnected=$connected")
        }) */
        connectivityEnumLiveData.observe(this, Observer { connected ->
            Log.i("MainActivity", "NetworkConnected=$connected")
        })
    }
}