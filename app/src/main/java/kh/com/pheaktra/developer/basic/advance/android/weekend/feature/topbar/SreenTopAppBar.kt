package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.topbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kh.com.pheaktra.developer.basic.advance.android.weekend.R
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopAppBar() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row() {
                        Icon(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .clickable(
                                    onClick = {
                                        println("====> You profile")
                                    }
                                ),
                            painter = painterResource(R.drawable.ic_launcher_foreground),
                            contentDescription = "Back"
                        )
                        Column() {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = stringResource(R.string.lbl_top_app_bar),
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = stringResource(R.string.lbl_top_app_bar),
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp
                            )
                        }
                    }
                },
//                navigationIcon = {
//                    IconButton(
//                        onClick = {
//                            println("=====> Back")
//                        }
//                    ) {
//                        Icon(
//                            painter = painterResource(R.drawable.ic_arrow_back),
//                            contentDescription = "Back"
//                        )
//                    }
//                },
                actions = {
                    IconButton(
//                        colors = IconButtonColors(
//                            containerColor = MaterialTheme.colorScheme.background.copy(alpha = 0.5f),
//                            contentColor = MaterialTheme.colorScheme.primary,
//                            disabledContentColor = MaterialTheme.colorScheme.secondary,
//                            disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
//                        ),
                        onClick = {
                            println("=====> Shared")
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_share),
                            contentDescription = "Share"
                        )
                    }
                    IconButton(
                        onClick = {
                            println("=====> More vert")
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_more_vert),
                            contentDescription = "More"
                        )
                    }
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.secondary,
                    navigationIconContentColor = MaterialTheme.colorScheme.error,
                    actionIconContentColor = MaterialTheme.colorScheme.error,
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Bottom app bar",
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { }) {
//                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text =
                    """
                    This is an example of a scaffold. It uses the Scaffold composable's parameters to create a screen with a simple top app bar, bottom app bar, and floating action button.

                    It also contains some basic inner content, such as this text.

                    You have pressed the floating action button times.
                """.trimIndent(),
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ScreenTopAppBarPreview() {
    AppTheme {
        ScreenTopAppBar()
    }
}