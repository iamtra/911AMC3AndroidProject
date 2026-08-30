package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.lazycolumn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kh.com.pheaktra.developer.basic.advance.android.weekend.R
import kh.com.pheaktra.developer.model.general.MaterialComponentModel
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenLazyColumn(
    item: MaterialComponentModel,
    onBack: () -> Unit
) {
    val names = listOf(
        "Alex", "Benjamin", "Charlotte", "Daniel", "Emma",
        "Felix", "Grace", "Henry", "Isabella", "Jack",
        "Kevin", "Luna", "Michael", "Nathan", "Olivia",
        "Pheaktra", "Ryan", "Sophia", "Thomas", "Victoria"
    )
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
                    Text(item.title)
                }
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
        ) {
            itemsIndexed(
                names,
                key = { _, item -> item }
            ) { index, item ->
                ItemLazyColumn(index, item)
            }
        }
    }
}

@Composable
fun ItemLazyColumn(index: Int, item: String) {
    Row(
        modifier = Modifier
            .height(96.dp)
            .padding(16.dp)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = MaterialTheme.shapes.small
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = "${index + 1}, $item"
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            modifier = Modifier.padding(end = 16.dp),
            imageVector = Icons.Filled.Build,
            contentDescription = ""
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ScreenLazyColumnPreview() {
    AppTheme {
        ScreenLazyColumn(
            item = MaterialComponentModel(
                1,
                "Lazy Column",
                "Lazy Column description",
                { "" },
                ""
            ),
            onBack = {}
        )
    }
}
