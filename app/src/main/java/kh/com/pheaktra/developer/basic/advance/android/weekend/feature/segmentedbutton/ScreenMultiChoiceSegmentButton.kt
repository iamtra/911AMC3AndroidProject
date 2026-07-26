package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.segmentedbutton

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kh.com.pheaktra.developer.basic.advance.android.weekend.R
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.MaterialComponentModel
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenMultiChoiceSegmentButton(
    item: MaterialComponentModel,
    onBack: () -> Unit
) {

    val selects = remember {
        mutableStateListOf(true, false, true)
    }
    val items = listOf<String>("9-10PM", "7-9AM", "9-11AM")

    LaunchedEffect(Unit) {
        //
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
                        text = item.title
                    )
                }
            )
        },
    ) { passing ->
        Column(
            modifier = Modifier
                .padding(passing)
                .fillMaxWidth()
        ) {
            MultiChoiceSegmentedButtonRow(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                items.forEachIndexed { index, item ->
                    SegmentedButton(
                        modifier = Modifier.weight(1f),
                        checked = selects[index],
                        onCheckedChange = {
                            selects[index] = !selects[index]
                            println(selects)
                        },
                        icon = { SegmentedButtonDefaults.Icon(selects[index]) },
                        label = {
                            Text(
                                text = item
                            )
                        },
                        shape = RoundedCornerShape(
                            topStart = if (index == 0) 16.dp else 0.dp,
                            topEnd = if (index == items.size - 1) 16.dp else 0.dp,
                            bottomStart = if (index == 0) 16.dp else 0.dp,
                            bottomEnd = if (index == items.size - 1) 16.dp else 0.dp
                        ),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ScreenMultiChoiceSegmentButtonPreview() {
    AppTheme {
        ScreenMultiChoiceSegmentButton(
            item = MaterialComponentModel(
                1,
                "Multi Choice Segmented Button",
                "Multi Choice Segmented Button description",
                { "" },
                ""
            )
        ) {

        }
    }
}
