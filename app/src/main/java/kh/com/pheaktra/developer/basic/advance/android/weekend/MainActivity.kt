package kh.com.pheaktra.developer.basic.advance.android.weekend

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kh.com.pheaktra.developer.basic.advance.android.weekend.navigation.BaseNavigation
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme
import kh.com.pheaktra.developer.basic.advance.android.weekend.util.Loading
import kh.com.pheaktra.developer.basic.advance.android.weekend.util.LoadingUtil
import kh.com.pheaktra.developer.core.Transfer

class MainActivity : ComponentActivity() {
    private lateinit var transfer: Transfer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        transfer = Transfer()
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                lightScrim = android.graphics.Color.TRANSPARENT,
                darkScrim = android.graphics.Color.TRANSPARENT,
            ),
            navigationBarStyle = SystemBarStyle.auto(
                lightScrim = android.graphics.Color.TRANSPARENT,
                darkScrim = android.graphics.Color.TRANSPARENT,
            ),
        )
        requestAccessLocalNetworkPermission()
        setContent {
            AppTheme {
                if (LoadingUtil.isLoading.value) {
                    Loading()
                }
                BaseNavigation()
            }
        }
    }

    fun requestAccessLocalNetworkPermission() {
        if (Build.VERSION.SDK_INT >= 37) {
            if (
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_LOCAL_NETWORK
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_LOCAL_NETWORK),
                    1001
                )
            }
        }
    }

    override fun onStart() {
        transfer.onTransfer(2000.0)
        super.onStart()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}