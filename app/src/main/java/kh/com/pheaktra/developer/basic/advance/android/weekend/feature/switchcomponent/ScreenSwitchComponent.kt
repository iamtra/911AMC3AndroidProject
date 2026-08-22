package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.switchcomponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kh.com.pheaktra.developer.basic.advance.android.weekend.R
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.general.MaterialComponentModel
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.Yellow60

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenSwitchComponent(
    item: MaterialComponentModel,
    onBack: () -> Unit = {}
) {
    var isEnableNotification by remember { mutableStateOf(false) }
    var isLightMode by remember { mutableStateOf(false) }
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
                            contentDescription = "Back"
                        )
                    }
                },
                title = {
                    Text(item.title)
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Enable/Disable Notification"
                )
                Switch(
                    checked = isEnableNotification,
                    onCheckedChange = { enable ->
                        isEnableNotification = enable
                    },
                    thumbContent = {
                        if (isEnableNotification) {
                            Icon(
                                painter = painterResource(R.drawable.ic_notification_on),
                                contentDescription = "Notification"
                            )
                        }
                    }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Light/Dark mode"
                )
                Switch(
                    checked = isLightMode,
                    onCheckedChange = { isLight ->
                        isLightMode = isLight
                    },
                    thumbContent = {
                        if (isLightMode) {
                            Icon(
                                painter = painterResource(R.drawable.ic_light),
                                contentDescription = "Switch",
                                tint = Yellow60
                            )
                        }
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = MaterialTheme.colorScheme.onPrimary,
                        checkedTrackColor = MaterialTheme.colorScheme.primary,
                        uncheckedThumbColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        uncheckedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                        checkedBorderColor = MaterialTheme.colorScheme.error
                    )
                )
            }

        }
    }
}

@Preview(name = "Phone", device = Devices.PIXEL_3_XL, showSystemUi = true)
@Preview(name = "Phone", device = "id:pixel_8", showSystemUi = true)
@Preview(name = "Tablet", device = "id:pixel_tablet", showSystemUi = true)
@Preview(name = "Foldable", device = "id:pixel_fold", showSystemUi = true)
@Preview(name = "Desktop", device = "spec:width=1920dp,height=1080dp", showSystemUi = true)
@Composable
fun ScreenSwitchComponentPreview() {
    AppTheme {
        ScreenSwitchComponent(
            item = MaterialComponentModel(
                1,
                "Switch",
                "Switch description",
                { "" },
                ""
            )
        )
    }
}