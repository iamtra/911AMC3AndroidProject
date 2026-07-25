package kh.com.pheaktra.developer.basic.advance.android.weekend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import kh.com.pheaktra.developer.basic.advance.android.weekend.navigation.BaseNavigation
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                BaseNavigation()
            }
        }
    }
}