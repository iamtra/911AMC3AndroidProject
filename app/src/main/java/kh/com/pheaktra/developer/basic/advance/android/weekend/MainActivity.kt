package kh.com.pheaktra.developer.basic.advance.android.weekend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import kh.com.pheaktra.developer.basic.advance.android.weekend.navigation.BaseNavigation
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme
import kh.com.pheaktra.developer.basic.advance.android.weekend.util.Loading
import kh.com.pheaktra.developer.basic.advance.android.weekend.util.LoadingUtil

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        setContent {
            AppTheme {
                if (LoadingUtil.isLoading.value) {
                    Loading()
                }
                BaseNavigation()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        println("=====> onStart")
    }

    override fun onPause() {
        super.onPause()
//        println("=====> onPause")
    }

    override fun onResume() {
        super.onResume()
//        println("=====> onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
//        println("=====> onDestroy")
    }
}