package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.badge

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
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
fun ScreenBadge() {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .clickable(
                                onClick = {
                                    println("====> You profile")
                                }
                            ),
                        painter = painterResource(R.drawable.ic_arrow_back),
                        contentDescription = "Back"
                    )
                },
                title = {
                    Row() {

                        Column() {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = stringResource(R.string.lbl_badge),
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.Bold
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
                    BadgedBox(
                        badge = {
                            Badge() {
                                Text(
                                    text = "99",
                                    fontSize = 12.sp,
                                )
                            }
                        }
                    ) {
                        IconButton(
                            onClick = {
                                println("=====> Share")
                            }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_share),
                                contentDescription = "Share"
                            )
                        }
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
                    actionIconContentColor = MaterialTheme.colorScheme.secondary,
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
fun ScreenBadgePreview() {
    AppTheme {
        ScreenBadge()
    }
}

/**
 * Homework May 16, 2026
 * 1. Research related to Material Deign
 * 2. Create TopAppBar
 *      - Small
 *      - Medium
 *      - Large
 * 3. Create Badge on any icon
 *      - Badge count number : Example 99+
 *      - Show red dot on the top of icon
 * 4. Submit all of your result and drop in the group to share to all of our member
 * 5. Doing research related to Activity LifeCycle in Android.
 */