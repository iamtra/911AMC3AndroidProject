package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.checkbox

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kh.com.pheaktra.developer.basic.advance.android.weekend.R
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.general.MaterialComponentModel
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenCheckBox(
    item: MaterialComponentModel,
    onBack: () -> Unit
) {
    data class CheckBoxItem(
        val id: Int,
        val title: String,
        val description: String,
        val checked: Boolean,
    )

    var checked by remember { mutableStateOf(false) }
    val materialComponents = remember {
        mutableStateListOf(
            CheckBoxItem(
                id = 1,
                title = "Button",
                description = "Material Button component",
                checked = false
            ),
            CheckBoxItem(
                id = 2,
                title = "TextField",
                description = "Material TextField component",
                checked = false
            ),
            CheckBoxItem(
                id = 3,
                title = "Checkbox",
                description = "Material Checkbox component",
                checked = false
            ),
            CheckBoxItem(
                id = 4,
                title = "Radio Button",
                description = "Material Radio Button component",
                checked = false
            ),
            CheckBoxItem(
                id = 5,
                title = "Switch",
                description = "Material Switch component",
                checked = false
            ),
            CheckBoxItem(
                id = 6,
                title = "Card",
                description = "Material Card component",
                checked = false
            ),
            CheckBoxItem(
                id = 7,
                title = "Dialog",
                description = "Material Dialog component",
                checked = false
            ),
            CheckBoxItem(
                id = 8,
                title = "Bottom Sheet",
                description = "Material Bottom Sheet component",
                checked = false
            ),
            CheckBoxItem(
                id = 9,
                title = "Snackbar",
                description = "Material Snackbar component",
                checked = false
            ),
            CheckBoxItem(
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
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                title = {
                    Text(
                        text = item.title
                    )
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
        ) {
            itemsIndexed(
                items = materialComponents,
                key = { _, item -> item.id }
            ) { index, item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            materialComponents[index] = item.copy(checked = !item.checked)
                        }
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = item.checked,
                        onCheckedChange = { isChecked ->
                            materialComponents[index] = item.copy(checked = isChecked)
                        }
                    )
                    Column(

                    ) {
                        Text(
                            text = item.title,
                            textDecoration = if (item.checked) TextDecoration.LineThrough else null
                        )
                        Text(
                            text = item.description,
                            fontSize = 12.sp
                        )
                    }
                }
                HorizontalDivider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenCheckBoxPreview() {
    AppTheme() {
        ScreenCheckBox(
            item = MaterialComponentModel(
                1,
                "CheckBox",
                "CheckBox description",
                { "" },
                ""
            )
        ) {}
    }
}