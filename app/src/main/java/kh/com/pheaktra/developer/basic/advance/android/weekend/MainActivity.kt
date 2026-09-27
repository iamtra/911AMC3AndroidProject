package kh.com.pheaktra.developer.basic.advance.android.weekend

import android.Manifest
import android.app.ComponentCaller
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import dagger.hilt.android.AndroidEntryPoint
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.segmentedbutton.TransactionType
import kh.com.pheaktra.developer.basic.advance.android.weekend.navigation.BaseNavigation
import kh.com.pheaktra.developer.basic.advance.android.weekend.receiver.NotificationClickReceiver
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme
import kh.com.pheaktra.developer.basic.advance.android.weekend.util.Loading
import kh.com.pheaktra.developer.basic.advance.android.weekend.util.LoadingUtil
import kh.com.pheaktra.developer.core.Transfer

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var transfer: Transfer
    private var transactionType: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleNotificationIntent(intent)
        transfer = Transfer()
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
                BaseNavigation(transactionType)
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        handleNotificationIntent(intent)
    }

    private fun handleNotificationIntent(intent: Intent?) {
        val transactionType = intent?.getStringExtra(NotificationClickReceiver.TRANSACTION_TYPE)
        if (transactionType != null) {
            println("=====> MainActivity $transactionType")
            this.transactionType = transactionType
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
                    1001,
                )
            }
        }
    }

    override fun onNewIntent(intent: Intent, caller: ComponentCaller) {
        super.onNewIntent(intent, caller)
        handleNotificationIntent(intent)
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