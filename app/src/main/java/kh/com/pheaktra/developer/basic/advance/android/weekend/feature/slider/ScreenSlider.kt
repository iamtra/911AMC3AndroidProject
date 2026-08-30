package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.slider

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberRangeSliderState
import androidx.compose.material3.rememberSliderState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
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
import kh.com.pheaktra.developer.basic.advance.android.weekend.R
import kh.com.pheaktra.developer.model.general.MaterialComponentModel
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenSlider(
    item: MaterialComponentModel,
    onBack: () -> Unit
) {
    val sliderState = rememberSliderState(
        value = 0f
    )
    val rangeSliderState = rememberRangeSliderState(
        activeRangeStart = 1f,
        activeRangeEnd = 19f,
        valueRange = 1f..20f,
        steps = 4,
        onValueChangeFinished = {
        }
    )

    var sliderValue by remember { mutableFloatStateOf(0f) }
    var sliderStep by remember { mutableFloatStateOf(0f) }
    var sliderRange by remember { mutableStateOf(0f..100f) }

    Scaffold(
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
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Slider without using slider state",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = "Normal Slider",
                style = MaterialTheme.typography.titleLarge
            )
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Slider(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 16.dp),
                    value = sliderValue,
                    onValueChange = { value ->
                        sliderValue = value
                    }
                )
                Text(
                    modifier = Modifier.width(56.dp),
                    text = "${(sliderValue * 100).roundToInt()}%",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = "Step Slider",
                style = MaterialTheme.typography.titleLarge
            )
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Slider(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 16.dp),
                    value = sliderStep,
                    onValueChange = { value ->
                        sliderStep = value
                    },
                    steps = 5,
                    valueRange = 1f..10f
                )
                Text(
                    modifier = Modifier.width(56.dp),
                    text = "${sliderStep.roundToInt()}",
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                text = "Range Slider",
                style = MaterialTheme.typography.titleLarge
            )
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RangeSlider(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 16.dp),
                    value = sliderRange,
                    onValueChange = { value ->
                        sliderRange = value
                    },
                    steps = 6,
                )
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "$sliderRange",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            HorizontalDivider()
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = "Slider within slider state",
                style = MaterialTheme.typography.titleLarge
            )

            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Slider(
                    modifier = Modifier.weight(1f),
                    state = sliderState,
                )
                Text(
                    modifier = Modifier.width(96.dp),
                    text = "${(sliderState.value * 100).roundToInt()} %",
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RangeSlider(
                    modifier = Modifier.weight(1f),
                    state = rangeSliderState,
                )
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Full Range ${rangeSliderState.valueRange}",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Start Range ${rangeSliderState.activeRangeStart}",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "End Range ${rangeSliderState.activeRangeEnd}",
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenSliderPreview() {
    AppTheme {
        ScreenSlider(
            item = MaterialComponentModel(
                1,
                "Slider",
                "Slider description",
                { "" },
                ""
            ),
            onBack = {}
        )
    }
}