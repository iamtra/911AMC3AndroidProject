package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.datepicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kh.com.pheaktra.developer.basic.advance.android.weekend.R
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.general.MaterialComponentModel
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme
import kh.com.pheaktra.developer.basic.advance.android.weekend.util.common.DateFormat
import kh.com.pheaktra.developer.basic.advance.android.weekend.util.datetime.DatetimeFormatter
import kh.com.pheaktra.developer.basic.advance.android.weekend.util.extension.toDateString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenDatePicker(
    item: MaterialComponentModel,
    onBack: () -> Unit
) {
    val state = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis(),
        initialDisplayMode = DisplayMode.Picker
    )
    var isShowDatePicker by remember { mutableStateOf(false) }
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
                    isShowDatePicker = true
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

            state.selectedDateMillis?.let { date ->
                val result = DatetimeFormatter.formatDate(date, DateFormat.FULL_MONTH_DAY_YEAR)
                Text("Selected date: $result")
            }
            Spacer(Modifier.height(16.dp))
            HorizontalDivider()
            Spacer(Modifier.height(16.dp))
            state.selectedDateMillis?.let { date ->
                val result = DatetimeFormatter.formatDate(date, DateFormat.ISO_DATE)
                Text("Selected date: $result")
            }
            Spacer(Modifier.height(16.dp))
            HorizontalDivider()
            Spacer(Modifier.height(16.dp))
            state.selectedDateMillis?.let { date ->
                val result = DatetimeFormatter.formatDate(date, DateFormat.MONTH_DAY_YEAR_SLASH)
                Text("Selected date: $result")
            }
            Spacer(Modifier.height(16.dp))
            HorizontalDivider()
            Spacer(Modifier.height(16.dp))
            state.selectedDateMillis?.let { date ->
                val result = DatetimeFormatter.formatDate(date, DateFormat.YEAR_MONTH_DAY_SLASH)
                Text("Selected date: $result")
            }
            Spacer(Modifier.height(16.dp))
            HorizontalDivider()
            Spacer(Modifier.height(16.dp))
            state.selectedDateMillis?.let { date ->
                Text("Selected date: ${date.toDateString(DateFormat.FULL_MONTH_DAY_YEAR.pattern)}")
            }
        }
        if (isShowDatePicker) {
            DatePickerDialog(
                onDismissRequest = {
                    isShowDatePicker = false
                },
                dismissButton = {
                    FilledTonalButton(
                        onClick = {
                            isShowDatePicker = false
                        }
                    ) {
                        Text("Cancel")
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            isShowDatePicker = false
                        }
                    ) {
                        Text("Confirm")
                    }
                },
            ) {
                DatePicker(
                    state = state,
                    showModeToggle = false,
                    title = {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Select your birthdate",
                            textAlign = TextAlign.Center,
                            fontSize = 18.sp,
                            style = MaterialTheme.typography.headlineLarge
                        )
                    },
                    dateFormatter = remember {
                        DatePickerDefaults.dateFormatter(
                            yearSelectionSkeleton = "yyyy",
                            selectedDateSkeleton = "dd MMMM yyyy",
                            selectedDateDescriptionSkeleton = "EEEE, dd MMMM yyyy"
                        )
                    }
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = false)
fun ScreenDatePickerPreview() {
    AppTheme {
        ScreenDatePicker(
            item = MaterialComponentModel(
                1,
                "DatePicker",
                "DatePicker description",
                { "" },
                ""
            ),
            onBack = {}
        )
    }
}
