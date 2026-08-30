package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.dialog

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kh.com.pheaktra.developer.basic.advance.android.weekend.R
import kh.com.pheaktra.developer.model.general.MaterialComponentModel
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.Blue40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenDialog(
    item: MaterialComponentModel,
    onBack: () -> Unit,
) {
    val context = LocalContext.current

    var isShowDialog by remember { mutableStateOf(false) }

    fun onCActionDialog(message: String) {
        isShowDialog = false
        val toast = Toast.makeText(context, message, Toast.LENGTH_SHORT)
        toast.show()
    }

    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBack()
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.background.copy(0.5f),
                            contentColor = colorResource(R.color.black)
                        )
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
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                onClick = {
                    isShowDialog = true
                }
            ) {
                Text("Open Dialog")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {

        }

        if (isShowDialog) {
            AlertDialog(
                icon = {
                    Icon(
                        modifier = Modifier.size(64.dp),
                        painter = painterResource(R.drawable.ic_info),
                        contentDescription = "Info",
                    )
                },
                title = {
                    Text("Message")
                },
                text = {
                    Text("Are you sure to close this dialog?")
                },
                onDismissRequest = {
                    isShowDialog = false
                },
                dismissButton = {
                    FilledTonalButton(
                        onClick = {
                            onCActionDialog("You click dismiss button")
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.lbl_cancel)
                        )
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            onCActionDialog("You click confirm button")
                        }
                    ) {
                        Text(
                            text = stringResource(R.string.lbl_confirm)
                        )
                    }
                },
                shape = RoundedCornerShape(8.dp),
                iconContentColor = Blue40,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenDialogPreview() {
    AppTheme {
        ScreenDialog(
            item = MaterialComponentModel(
                1,
                "Dialog",
                "Dialog description",
                { "" },
                ""
            ),
            onBack = {}
        )
    }
}
