package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.Biometric

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kh.com.pheaktra.developer.basic.advance.android.weekend.R
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme
import kh.com.pheaktra.developer.basic.advance.android.weekend.util.BiometricUtil
import kh.com.pheaktra.developer.model.general.MaterialComponentModel

@Composable
fun ScreenFingerPrint(
    item: MaterialComponentModel,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val (resultMessage, setResultMessage) = remember { mutableStateOf("") }

    fun onExecuteBiometric() {
        BiometricUtil.executeBiometric(
            context = context,
            onError = { _, message ->
                setResultMessage(message)
            },
            onSuccess = { message ->
                setResultMessage(message)
            },
            onFailure = {
                setResultMessage("Failed")
            }
        )
    }

    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
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
                    Text(
                        text = item.title
                    )
                }
            )
        },
        bottomBar = {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                onClick = {
                    onExecuteBiometric()
                },
            ) {
                Text("Biometric")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(resultMessage)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenFingerPrintPreview() {
    AppTheme {
        ScreenFingerPrint(
            item = MaterialComponentModel(
                1,
                "Fingerprint",
                "Fingerprint description",
                { "" },
                ""
            ),
            onBack = {}
        )
    }
}