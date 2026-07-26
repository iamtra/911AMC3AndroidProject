package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.radio

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kh.com.pheaktra.developer.basic.advance.android.weekend.R
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.MaterialComponentModel
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme

/**
 * * Requirement
 * - Create few radio button
 * - Choose of them as an option
 * * Order coffee from food panda
 * - Choose coffee
 * - Choose size
 * - Choose ice
 * - Choose milk
 * - Choose sugar
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenRadio(
    item: MaterialComponentModel,
    onBack: () -> Unit,
) {
    val scrollState = rememberScrollState()

    var selectedIndex by remember { mutableIntStateOf(0) }


    val coffeeOption = listOf(
        RadioModel(
            label = "Ice Latte",
        ),
        RadioModel(
            label = "Ice Espresso",
        ),
        RadioModel(
            label = "Ice Americano",
        ),
        RadioModel(
            label = "Hot Cappuccino",
        ),
        RadioModel(
            label = "Ice Mocha",
        )
    )
    val sizeOption = listOf(
        RadioModel(
            label = "Small",
        ),
        RadioModel(
            label = "Medium",
        ),
        RadioModel(
            label = "Large",
        )
    )
    val iceOption = listOf(
        RadioModel(
            label = "No ice",
        ),
        RadioModel(
            label = "Less ice",
        ),
        RadioModel(
            label = "Normal ice",
        ),
        RadioModel(
            label = "Ice outside",
        )
    )
    val milkOption = listOf(
        RadioModel(
            label = "No Milk",
        ),
        RadioModel(
            label = "Less Milk",
        ),
        RadioModel(
            label = "Normal Milk",
        ),
    )
    val sugarOption = listOf(
        RadioModel(
            label = "0%",
        ),
        RadioModel(
            label = "25%",
        ),
        RadioModel(
            label = "50%",
        ),
        RadioModel(
            label = "75%",
        ),
        RadioModel(
            label = "100%",
        ),
    )

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
                    .padding(16.dp),
                onClick = {}
            ) {
                Text("Confirm Order")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            /**
             * Choose coffee
             */
            OrderSection(
                modifier = Modifier.padding(top = 16.dp),
                title = "Choose coffee",
                list = coffeeOption
            )
            /**
             * Choose size
             */
            OrderSection(
                modifier = Modifier.padding(top = 16.dp),
                title = "Choose size",
                list = sizeOption
            )

            /**
             * Choose ice
             */
            OrderSection(
                modifier = Modifier.padding(top = 16.dp),
                title = "Choose Ice",
                list = iceOption
            )

            /**
             * Choose milk
             */
            OrderSection(
                modifier = Modifier.padding(top = 16.dp),
                title = "Choose Milk",
                list = milkOption
            )
            /**
             * Choose sugar
             */
            OrderSection(
                modifier = Modifier.padding(top = 16.dp),
                title = "Choose Sugar",
                list = sugarOption
            )
        }

    }
}

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    title: String,
    list: List<RadioModel> = emptyList()
) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    Column(
        modifier = Modifier
            .then(modifier)
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = title,
            fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            style = MaterialTheme.typography.titleLarge
        )
        list.forEachIndexed { index, option ->
            Option(
                option = RadioModel(
                    label = option.label,
                ),
                isSelected = selectedIndex == index,
                onClick = {
                    selectedIndex = index
                }
            )
        }
    }
}

@Composable
fun Option(
    option: RadioModel,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = onClick
        )
        Text(
            text = option.label
        )
    }
}

data class RadioModel(
    val label: String,
)

@Preview(showBackground = false)
@Composable
fun ScreenRadioPreview() {
    AppTheme {
        ScreenRadio(
            item = MaterialComponentModel(
                1,
                "Radio",
                "Radio description",
                { "" },
                ""
            ),
            onBack = {}
        )
    }
}