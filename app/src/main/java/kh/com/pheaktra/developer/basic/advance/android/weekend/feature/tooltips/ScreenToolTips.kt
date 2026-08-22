package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.tooltips

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.RichTooltip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipAnchorPosition
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kh.com.pheaktra.developer.basic.advance.android.weekend.R
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.general.MaterialComponentModel
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenToolTips(
    item: MaterialComponentModel,
    onBack: () -> Unit = {}
) {
    val scope = rememberCoroutineScope()
    val plainState = rememberTooltipState(
        initialIsVisible = false
    )
    val richState = rememberTooltipState(
        initialIsVisible = true
    )

    val additionInfo = """
        Pay to: 0987654321
        Account: 1234567890
        Amount: 1000000000
    """.trimIndent()

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
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.secondaryContainer),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TooltipBox(
                tooltip = {
                    PlainTooltip{
                            Text(
                                text = additionInfo,
                                modifier = Modifier
                                    .padding(8.dp),
                                style = MaterialTheme.typography.bodySmall
                            )
                    }
                },
                state = plainState,
                onDismissRequest = {
                    plainState.dismiss()
                },
                positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
                hasAction = true
            ) {
                IconButton(
                    onClick = {
                        scope.launch {
                            plainState.show()
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_info),
                        contentDescription = "Info"
                    )
                }
            }

            TooltipBox(
                tooltip = {
                    RichTooltip(){
                        Text(
                            text = additionInfo,
                            modifier = Modifier
                                .padding(8.dp),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                },
                state = richState,
                onDismissRequest = {
                    richState.dismiss()
                },
                positionProvider = TooltipDefaults.rememberTooltipPositionProvider(
                    positioning = TooltipAnchorPosition.Below,
                    spacingBetweenTooltipAndAnchor = 4.dp
                ),
                hasAction = true
            ) {
                IconButton(
                    onClick = {
                        scope.launch {
                            richState.show()
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_info),
                        contentDescription = "Info"
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ScreenToolTipsPreview() {
    AppTheme {
        ScreenToolTips(
            item = MaterialComponentModel(
                1,
                "Tooltips",
                "Tooltips description",
                { "" },
                ""
            )
        )
    }
}