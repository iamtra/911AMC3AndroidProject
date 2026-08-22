package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.column

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import kh.com.pheaktra.developer.basic.advance.android.weekend.R
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.general.MaterialComponentModel
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenColumn(
    item: MaterialComponentModel,
    onBack: () -> Unit
) {
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
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier
                    .background(
                        color = colorResource(R.color.teal_200),
                        shape = MaterialTheme.shapes.extraLarge
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = "This is children 1",
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "This is children 2",
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "This is children 3",
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "This is children 4",
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ScreenScreenColumnPreview() {
    AppTheme {
        ScreenColumn(
            item = MaterialComponentModel(
                1,
                "Column",
                "Column description",
                { "" },
                ""
            ),
            onBack = {}
        )
    }
}

/**
 * Homework April 02, 2026
 * 1. Scaffold
 * 2. Column
 */