package com.ahmrh.newsapp.ui.activity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ahmrh.newsapp.ui.navigation.NavGraph
import com.ahmrh.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)


        setContent {
            NewsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface {

                    navController = rememberNavController()
                    NavGraph(navController = navController)
                }
            }
        }

    }


    fun Context.isConnected(): Flow<Boolean> {
        val connectivityManager =
            this.getSystemService(
                Context.CONNECTIVITY_SERVICE
            ) as ConnectivityManager
        return callbackFlow {
            val networkCallback =
                object : ConnectivityManager.NetworkCallback() {
                    override fun onAvailable(network: Network) {
                        launch(Dispatchers.Default) {
                            send(true)
                        }
                    }

                    override fun onLost(network: Network) {
                        launch(Dispatchers.Default) {
                            send(false)
                        }
                    }

                    override fun onUnavailable() {
                        launch(Dispatchers.Default) {
                            send(false)
                        }
                    }
                }
            connectivityManager.registerDefaultNetworkCallback(networkCallback)
            awaitClose {
                connectivityManager.unregisterNetworkCallback(networkCallback)
            }
        }
    }
}