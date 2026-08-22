package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kh.com.pheaktra.developer.basic.advance.android.weekend.R
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.general.MaterialComponentModel
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.Green60

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenButton(
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
                    Text(
                        text = item.title
                    )
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
            IconButton(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(16.dp)
                    ),
                onClick = {
                    println("=====> You click icon icon Button")
                },
//                colors = IconButtonDefaults.iconButtonColors(
//                    containerColor = MaterialTheme.colorScheme.primary,
//                    contentColor = MaterialTheme.colorScheme.background,
//                    disabledContentColor = DeepOrange40,
//                    disabledContainerColor = Grey40,
//                ),
                enabled = false
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_download),
                    contentDescription = "Download Icon",
                    tint = MaterialTheme.colorScheme.background
                )
            }

            SpaceAndDivider()

            Button(
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = Green60,
                    contentColor = colorResource(R.color.black),
                    disabledContentColor = MaterialTheme.colorScheme.secondary,
                    disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                ),
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    println("=====> You click button")
                },
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary,
                )
            ) {
                Text("Download")
            }

            SpaceAndDivider()

            FilledTonalButton(
                onClick = {
                    println("=====> You click filled tonal button")
                }
            ) {
                Text("Remind me later")
            }

            SpaceAndDivider()

            OutlinedButton(
                onClick = {
                    println("=====> You click outlined button")
                },
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Skip")
            }

            SpaceAndDivider()

            ElevatedButton(
                onClick = {
                    println("=====> You click elevated button")
                },
//                colors = ButtonDefaults.elevatedButtonColors(
//                    containerColor = colorResource(R.color.teal_200),
//                    contentColor = Cyan40
//                ),
                elevation = ButtonDefaults.elevatedButtonElevation(
                    defaultElevation = 10.dp,
                    pressedElevation = 15.dp
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_share),
                        contentDescription = ""
                    )
                    Text("Share")
                }
            }

            SpaceAndDivider()

            TextButton(
                onClick = {
                    println("=====> You click text button")
                }
            ) {
                Text("Skip")
            }
        }
    }
}

@Composable
fun SpaceAndDivider() {
    Spacer(modifier = Modifier.height(16.dp))
    HorizontalDivider()
    Spacer(modifier = Modifier.height(16.dp))
}


@Preview(showBackground = true)
@Composable
fun ScreenButtonPreview() {
    AppTheme() {
        ScreenButton(
            item = MaterialComponentModel(
                1,
                "Button",
                "Button description",
                { "" },
                ""
            ),
            onBack = {}
        )
    }
}