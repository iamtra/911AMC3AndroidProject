package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.box

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenBox() {
    val text = """
        In the layout model, the UI tree is laid out in a single pass.
         Each node is first asked to measure itself, 
         then measure any children recursively, 
         passing size constraints down the tree to children. 
         Then, leaf nodes are sized and placed, 
         with the resolved sizes and placement instructions passed back up the tree.
    """.trimIndent()
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = "Back",
                        )
                    }
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Box")
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
            Box(
                modifier = Modifier.background(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                    shape =
                        RoundedCornerShape(16.dp)
                )
                    .size(128.dp),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .padding(end = 96.dp)
                        .size(56.dp)
                        .background(color = MaterialTheme.colorScheme.error)
                ) {

                }

                Box(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .size(56.dp)
                        .background(color = MaterialTheme.colorScheme.secondary)
                ) {

                }
                Box(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .padding(top = 24.dp)
                        .padding(end = 24.dp)
                        .size(56.dp)
                        .background(color = MaterialTheme.colorScheme.primary)
                ) {

                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ScreenBoxPreview() {
    AppTheme() {
        ScreenBox()
    }
}