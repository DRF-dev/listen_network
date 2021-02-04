package exercice.udemy.connectivitylivedata

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData

class ConnectivityLiveData(private val context: Context): LiveData<Boolean>() {
    private val networkReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfos = connectivityManager.activeNetworkInfo

            value = networkInfos != null && networkInfos.isConnected
        }
    }

    override fun onActive() {
        var filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        context.registerReceiver(networkReceiver,filter)
    }

    override fun onInactive() {
        context.unregisterReceiver(networkReceiver)
    }
}