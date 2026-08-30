package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.toolbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FlexibleBottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kh.com.pheaktra.developer.basic.advance.android.weekend.R
import kh.com.pheaktra.developer.model.general.MaterialComponentModel
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenToolbar(
    item: MaterialComponentModel,
    onBack: () -> Unit = {}
) {
    val scrollBehavior = BottomAppBarDefaults.exitAlwaysScrollBehavior()
    val scrollBehaviorTop = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val icons =
        listOf(
            Icons.AutoMirrored.Filled.ArrowBack,
            Icons.AutoMirrored.Filled.ArrowForward,
            Icons.Filled.Add,
            Icons.Filled.Check,
            Icons.Filled.Edit,
        )

    Scaffold(
        modifier = Modifier
            .nestedScroll(connection = scrollBehavior.nestedScrollConnection)
            .nestedScroll(connection = scrollBehaviorTop.nestedScrollConnection),
        topBar = {
            TopAppBar(
                scrollBehavior = scrollBehaviorTop,
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
        },
        bottomBar = {
            FlexibleBottomAppBar(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
                    .clip(RoundedCornerShape(100.dp)),
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                horizontalArrangement = Arrangement.SpaceEvenly,
                scrollBehavior = scrollBehavior,
                windowInsets = WindowInsets(0, 0, 0, 0)
            ) {

                icons.forEachIndexed { index, icon ->

                    if (index == icons.size -3) {
                        FloatingActionButton(
                            onClick = {

                            }
                        ) {
                            Icon(
                                imageVector = icon,
                                contentDescription = "Back"
                            )
                        }
                    } else {
                        IconButton(
                            onClick = {
                                println("====> $index")
                            }
                        ) {
                            Icon(
                                imageVector = icon,
                                contentDescription = "Back"
                            )
                        }
                    }
                }
            }
        },
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(100) { index ->
                Text(
                    text = "This is text ${index + 1}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                )
                HorizontalDivider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenToolbarPreview() {
    AppTheme {
        ScreenToolbar(
            item = MaterialComponentModel(
                1,
                "Toolbar",
                "Toolbar description",
                { "" },
                ""
            )
        )
    }
}


