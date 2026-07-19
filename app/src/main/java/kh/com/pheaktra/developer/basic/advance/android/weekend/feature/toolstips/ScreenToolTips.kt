package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.toolstips

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kh.com.pheaktra.developer.basic.advance.android.weekend.R
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme
import kotlinx.coroutines.launch

@Composable
fun ScreenToolTips(
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
                    Text("Tooltips")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
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
        ScreenToolTips()
    }
}