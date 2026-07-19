package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.chip

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kh.com.pheaktra.developer.basic.advance.android.weekend.R
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenChip(
    onBack: () -> Unit
) {
    data class ChipItem(
        val id: Int,
        val title: String,
        val description: String,
        val checked: Boolean,
    )

    var checked by remember { mutableStateOf(false) }
    val materialComponents = remember {
        mutableStateListOf(
            ChipItem(
                id = 1,
                title = "Button",
                description = "Material Button component",
                checked = false
            ),
            ChipItem(
                id = 2,
                title = "TextField",
                description = "Material TextField component",
                checked = false
            ),
            ChipItem(
                id = 3,
                title = "Checkbox",
                description = "Material Checkbox component",
                checked = false
            ),
            ChipItem(
                id = 4,
                title = "Radio Button",
                description = "Material Radio Button component",
                checked = false
            ),
            ChipItem(
                id = 5,
                title = "Switch",
                description = "Material Switch component",
                checked = false
            ),
            ChipItem(
                id = 6,
                title = "Card",
                description = "Material Card component",
                checked = false
            ),
            ChipItem(
                id = 7,
                title = "Dialog",
                description = "Material Dialog component",
                checked = false
            ),
            ChipItem(
                id = 8,
                title = "Bottom Sheet",
                description = "Material Bottom Sheet component",
                checked = false
            ),
            ChipItem(
                id = 9,
                title = "Snackbar",
                description = "Material Snackbar component",
                checked = false
            ),
            ChipItem(
                id = 10,
                title = "Navigation Drawer",
                description = "Material Navigation Drawer component",
                checked = false
            )
        )
    }
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
                ),
                title = {
                    Text(
                        text = "Chip Component"
                    )
                }
            )

        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {
            Row(
                modifier = Modifier
                    .horizontalScroll(
                        state = rememberScrollState()
                    )
                    .padding(
                        top = 16.dp,
                        start = 16.dp,
                        end = 16.dp
                    ),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                materialComponents.forEachIndexed { index, item ->
                    FilterChip(
                        selected = item.checked,
                        onClick = {
                            materialComponents[index] = item.copy(checked = !item.checked)
                        },
                        label = {
                            Text(
                                text = item.title
                            )
                        },
                    )
                }
            }

        }
    }
}

@Composable
@Preview(showBackground = false)
fun ScreenChipPreview() {
    AppTheme() {
        ScreenChip { }
    }
}

/**
 * Homework
 * 1. Create carousel component
 * 2. Carousel, but apply with list of your object
 * 3. Create single check box that allow user to toggle checked
 * 4. Create multi check box that allow user to toggle checked  (Mutable list )
 */









