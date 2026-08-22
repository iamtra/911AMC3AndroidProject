package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.timepicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.TimePickerDialog
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kh.com.pheaktra.developer.basic.advance.android.weekend.R
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.general.MaterialComponentModel
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTimePicker(
    item: MaterialComponentModel,
    onBack: () -> Unit
) {
    val calendar = Calendar.getInstance()
    val state = rememberTimePickerState(
        initialHour = calendar.get(Calendar.HOUR_OF_DAY),
        initialMinute = calendar.get(Calendar.MINUTE),
        is24Hour = false
    )
    var isShowTimePicker by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            LargeTopAppBar(
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
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp),
                onClick = {
                    isShowTimePicker = true
                }
            ) {
                Text("Select Date")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Selected time is : ${state.hour}:${state.minute}")
        }
        if (isShowTimePicker) {
            TimePickerDialog(
                onDismissRequest = {
                    isShowTimePicker = false
                },
                title = {
                    Text("Time Picker")
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            isShowTimePicker = false
                            println("Time: ${state.hour}:${state.minute}")
                        }
                    ) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    FilledTonalButton(
                        onClick = {
                            isShowTimePicker = false
                        }
                    ) {
                        Text("Cancel")
                    }
                },
                shape = RoundedCornerShape(8.dp),
                containerColor = Color.Red,
            ) {
                TimePicker(
                    state = state,
                    colors = TimePickerDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.background,
                        clockDialColor = MaterialTheme.colorScheme.primary,
                        clockDialSelectedContentColor = MaterialTheme.colorScheme.primary,
                        clockDialUnselectedContentColor = MaterialTheme.colorScheme.onPrimary,
                        selectorColor = MaterialTheme.colorScheme.onPrimary,
                        periodSelectorBorderColor = MaterialTheme.colorScheme.error,
                        periodSelectorSelectedContainerColor = MaterialTheme.colorScheme.primary,
                        periodSelectorSelectedContentColor = MaterialTheme.colorScheme.onPrimary,
                        periodSelectorUnselectedContainerColor = MaterialTheme.colorScheme.secondary,
                        periodSelectorUnselectedContentColor = MaterialTheme.colorScheme.onSecondary,
                        timeSelectorSelectedContainerColor = MaterialTheme.colorScheme.primary,
                        timeSelectorSelectedContentColor = MaterialTheme.colorScheme.onPrimary,
                        timeSelectorUnselectedContainerColor = MaterialTheme.colorScheme.secondary,
                        timeSelectorUnselectedContentColor = MaterialTheme.colorScheme.onSecondary,
                    )
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = false)
fun ScreenTimePickerPreview() {
    AppTheme() {
        ScreenTimePicker(
            item = MaterialComponentModel(
                1,
                "TimePicker",
                "TimePicker description",
                { "" },
                ""
            )
        ) { }
    }
}

/**
 * Homework Time Picker Component
 * 1. Create time picker Input without having dialog
 * 2. Create time picker Input with having dialog
 * 3. Create time picker Dial mode without having dialog
 * 4. Create time picker Dial mode with having dialog and customize color
 *  - containerColor
 *  - clockDialColor
 *  - clockDialSelectedContentColor
 *  - clockDialUnselectedContentColor
 *  - and all
 */










