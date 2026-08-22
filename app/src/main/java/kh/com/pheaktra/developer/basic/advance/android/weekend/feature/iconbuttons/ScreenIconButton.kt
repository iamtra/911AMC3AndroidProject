package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.iconbuttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kh.com.pheaktra.developer.basic.advance.android.weekend.R
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.general.MaterialComponentModel
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenIconButton(
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
                    .height(128.dp)
                    .width(128.dp)
                    .clip(CircleShape),
                onClick = {
                    println("=====> You click icon icon Button")
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = colorResource(R.color.teal_200),
                    contentColor = colorResource(R.color.red),
                )
            ) {
                Icon(
                    modifier = Modifier
                        .size(64.dp),
                    painter = painterResource(R.drawable.ic_download),
                    contentDescription = stringResource(R.string.lbl_icon_button)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            IconButton(
                modifier = Modifier
                    .height(128.dp)
                    .width(128.dp)
                    .clip(RoundedCornerShape(
                        topStart = 64.dp,
                        topEnd = 0.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 64.dp
                    ))
                    .background(
                        color = colorResource(R.color.teal_200)
                    ),
                onClick = {
                    println("=====> You click icon icon Button")
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = colorResource(R.color.teal_200),
                    contentColor = colorResource(R.color.red),
                )
            ) {
                Icon(
                    modifier = Modifier
                        .size(64.dp),
                    painter = painterResource(R.drawable.ic_download),
                    contentDescription = stringResource(R.string.lbl_icon_button)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ScreenIconButtonPreview() {
    AppTheme {
        ScreenIconButton(
            item = MaterialComponentModel(
                1,
                "Icon Button",
                "Icon Button description",
                { "" },
                ""
            ),
            onBack = {}
        )
    }
}