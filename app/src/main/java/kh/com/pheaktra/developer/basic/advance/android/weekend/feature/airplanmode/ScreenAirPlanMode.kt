package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.airplanmode

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirplanemodeInactive
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberBottomSheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import kh.com.pheaktra.developer.basic.advance.android.weekend.R
import kh.com.pheaktra.developer.basic.advance.android.weekend.receiver.AirplaneModeReceiver
import kh.com.pheaktra.developer.basic.advance.android.weekend.receiver.InternetConnectionReceiver
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme
import kh.com.pheaktra.developer.basic.advance.android.weekend.util.extension.isAirplaneModeEnabled
import kh.com.pheaktra.developer.basic.advance.android.weekend.util.extension.isInternetConnected
import kh.com.pheaktra.developer.model.general.MaterialComponentModel

@Composable
fun ScreenAirPlanMode(
    item: MaterialComponentModel,
    onBack: () -> Unit,
) {
    val context = LocalContext.current
    val isAirplaneModeEnabled = rememberAirplaneModeState()


    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBack()
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = "Back",
                        )
                    }
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                title = {
                    Text(item.title)
                }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (isAirplaneModeEnabled) "Airplane Mode Enabled" else "Airplane Mode Disabled",
                    color = if (isAirplaneModeEnabled) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                )
            }

            if (isAirplaneModeEnabled) {
                BottomSheetAirplaneModeEnabled(
                    onDismiss = {},
                    onTryAgain = {},
                    onSetting = {
                        context.startActivity(
                            Intent(
                                android.provider.Settings.ACTION_AIRPLANE_MODE_SETTINGS
                            )
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun rememberAirplaneModeState(): Boolean {
    val context = LocalContext.current

    var isAirplaneModeEnabled by remember {
        mutableStateOf(context.isAirplaneModeEnabled())
    }

    DisposableEffect(context) {

        val receiver = AirplaneModeReceiver { isEnabled ->
            isAirplaneModeEnabled = isEnabled
        }

        val filter = IntentFilter(
            Intent.ACTION_AIRPLANE_MODE_CHANGED
        )

        ContextCompat.registerReceiver(
            context,
            receiver,
            filter,
            ContextCompat.RECEIVER_NOT_EXPORTED,
        )

        onDispose {
            context.unregisterReceiver(receiver)
        }
    }

    return isAirplaneModeEnabled
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetAirplaneModeEnabled(
    onDismiss: () -> Unit,
    onTryAgain: () -> Unit,
    onSetting: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    bottom = 32.dp,
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                imageVector = Icons.Default.AirplanemodeInactive,
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.error,
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = "Airplane Mode Enabled",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Please turn off your Airplane Mode and try again.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Button(
                onClick = onTryAgain,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
            ) {
                Text(
                    text = "Try Again",
                    style = MaterialTheme.typography.labelLarge,
                )
            }
            Spacer(
                modifier = Modifier.height(24.dp)
            )
            Button(
                onClick = onSetting,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                )
            ) {
                Text(
                    text = "Setting",
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenInternetConnectionPreview() {
    AppTheme {
        ScreenAirPlanMode(
            item = MaterialComponentModel(
                id = 1,
                title = "Airplane Mode",
                description = "Check airplane mode status",
                routeProvider = { "" },
                icon = ""
            ),
            onBack = {}
        )
    }
}