package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.menu

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kh.com.pheaktra.developer.basic.advance.android.weekend.R
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.MaterialComponentModel
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenMenu(
    item: MaterialComponentModel,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    var expand by remember { mutableStateOf(false) }
    val items = listOf(
        MenuItemModel(
            text = "Open",
            onClick = {
                println("====> Open item")
            },
            leadingIcon = R.drawable.ic_open_folder,
        ),
        MenuItemModel(
            text = "Make a copy",
            onClick = {
                println("====> Open item")
            },
            leadingIcon = R.drawable.ic_make_copy,
        ),
        MenuItemModel(
            text = "Create",
            onClick = {
                println("====> Open item")
            },
            leadingIcon = R.drawable.ic_create,
        ),
        MenuItemModel(
            text = "Offline mode",
            onClick = {
                println("====> Open item")
            },
            leadingIcon = R.drawable.ic_ticked,
        ),
        MenuItemModel(
            text = "Share",
            onClick = {
                println("====> Open item")
            },
            leadingIcon = R.drawable.ic_share,
            trailingIcon = R.drawable.ic_arrow_right_tiny,
            backgroundColor = Purple40
        ),
        MenuItemModel(
            text = "Download",
            onClick = {
                println("====> Open item")
            },
            leadingIcon = R.drawable.ic_download,
            trailingIcon = R.drawable.ic_arrow_right_tiny,
        )
    )
    Scaffold(
        topBar = {
            TopAppBar(
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
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                title = {
                    Text(
                        text = item.title
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            expand = true
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_more_vert),
                            contentDescription = "More"
                        )
                    }
                    DropdownMenu(
                        expanded = expand,
                        onDismissRequest = { expand = false },
                        containerColor = Color.Transparent,
                        shadowElevation = 0.dp,
                        tonalElevation = 0.dp,
                    ) {
                        Column(
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .background(MaterialTheme.colorScheme.background)
                        ) {
                            items.forEachIndexed { index, item ->
                                DropdownMenuItem(
                                    modifier = Modifier
                                        .background(
                                            color = MaterialTheme.colorScheme.background,
                                            shape = RoundedCornerShape(
                                                topStart = if (index == 0) 16.dp else 0.dp,
                                                topEnd = if (index == 0) 16.dp else 0.dp,
                                                bottomStart = if (index == items.size - 1) 16.dp else 0.dp,
                                                bottomEnd = if (index == items.size - 1) 16.dp else 0.dp
                                            )
                                        )
                                        .padding(horizontal = 16.dp),
                                    text = {
                                        Text(
                                            text = item.text
                                        )
                                    },
                                    onClick = {
                                        item.onClick.invoke()
                                        expand = false
                                        val toast = Toast.makeText(
                                            context,
                                            "Your click ${item.text}",
                                            Toast.LENGTH_LONG
                                        )
                                        toast.show()
                                    },
                                    leadingIcon = {
                                        if (item.leadingIcon != null) {
                                            Icon(
                                                painter = painterResource(item.leadingIcon),
                                                contentDescription = "item.leadingIcon"
                                            )
                                        }
                                    },
                                    trailingIcon = {
                                        if (item.trailingIcon != null) {
                                            Icon(
                                                painter = painterResource(item.trailingIcon),
                                                contentDescription = "item.trailingIcon"
                                            )
                                        }
                                    },
                                    enabled = item.enabled,
                                    colors = MenuDefaults.itemColors(
//                                    textColor = MaterialTheme.colorScheme.error,
//                                    leadingIconColor = Cyan60,
//                                    trailingIconColor = Cyan60,
//                                    disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(
//                                        alpha = 0.38f
//                                    ),
//                                    disabledLeadingIconColor = MaterialTheme.colorScheme.onSurface.copy(
//                                        alpha = 0.38f
//                                    ),
//                                    disabledTrailingIconColor = MaterialTheme.colorScheme.onSurface.copy(
//                                        alpha = 0.38f
//                                    )
                                    ),
//                                contentPadding = PaddingValues(16.dp),
                                    interactionSource = remember { MutableInteractionSource() }
                                )
                            }
                        }
                    }
                }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) { }
    }
}

data class MenuItemModel(
    val text: String,
    val onClick: () -> Unit,
    @DrawableRes val leadingIcon: Int? = null,
    @DrawableRes val trailingIcon: Int? = null,
    val enabled: Boolean = true,
    val backgroundColor: Color? = null,
)

@Preview(showBackground = true)
@Composable
fun ScreenMenuPreview() {
    AppTheme {
        ScreenMenu(
            item = MaterialComponentModel(
                1,
                "Menu",
                "Menu description",
                { "" },
                ""
            ),
            onBack = {}
        )
    }
}