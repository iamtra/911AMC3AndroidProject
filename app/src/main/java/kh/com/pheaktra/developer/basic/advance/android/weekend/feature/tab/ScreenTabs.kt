package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.tab

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kh.com.pheaktra.developer.basic.advance.android.weekend.R
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.navigationbar.CONTENT_TYPE
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.navigationbar.FavoriteScreen
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.navigationbar.HomeScreen
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.navigationbar.SettingScreen
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTabs(
    onBack: () -> Unit = {}
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf(
        TabModel(
            id = 0,
            title = "Home",
            icon = R.drawable.ic_home
        ),
        TabModel(
            id = 1,
            title = "Favorite",
            icon = R.drawable.ic_favorite
        ),
        TabModel(
            id = 2,
            title = "Setting",
            icon = R.drawable.ic_setting,
        )
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
                            contentDescription = "Back"
                        )
                    }
                },
                title = {
                    Text("Tab Component")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            PrimaryTabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = MaterialTheme.colorScheme.background,
                indicator = {
                    TabRowDefaults.PrimaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(selectedTabIndex),
                        width = Dp.Unspecified, // Fill selected tab width
                        height = 1.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            ) {

                tabs.forEachIndexed { index, tab ->
                    Tab(
                        selected = index == selectedTabIndex,
                        onClick = {
                            selectedTabIndex = index
                        },
                        modifier = Modifier,
                        enabled = true,
                        text = {
                            Text(tab.title)
                        },
                        icon = {
                            Icon(
                                painter = painterResource(tab.icon),
                                contentDescription = "Home"
                            )
                        },
                        selectedContentColor = MaterialTheme.colorScheme.error,
                        unselectedContentColor = MaterialTheme.colorScheme.primary
                    )
                }
            }
            AnimatedContent(
                targetState = selectedTabIndex,
                transitionSpec = {
                    if (targetState > initialState) {
                        slideInHorizontally(
                            initialOffsetX = { it }
                        ) + fadeIn() togetherWith
                                slideOutHorizontally(
                                    targetOffsetX = { -it }
                                ) + fadeOut()
                    } else {
                        slideInHorizontally(
                            initialOffsetX = { -it }
                        ) + fadeIn() togetherWith
                                slideOutHorizontally(
                                    targetOffsetX = { it }
                                ) + fadeOut()
                    }.using(
                        SizeTransform(clip = false)
                    )
                },
                label = "BottomNavigationAnimation"
            ) { tabIndex ->
                when (tabIndex) {
                    CONTENT_TYPE.HOME.value -> HomeScreen()
                    CONTENT_TYPE.FAVORITE.value -> FavoriteScreen()
                    CONTENT_TYPE.SETTINGS.value -> SettingScreen()
                }
            }
        }
    }
}

data class TabModel(
    val id: Int,
    val title: String,
    @DrawableRes val icon: Int,
)

enum class Tab

@Preview(name = "Phone", device = Devices.PIXEL_3_XL, showSystemUi = true)
@Preview(name = "Phone", device = "id:pixel_8", showSystemUi = true)
@Preview(name = "Tablet", device = "id:pixel_tablet", showSystemUi = true)
@Preview(name = "Foldable", device = "id:pixel_fold", showSystemUi = true)
@Preview(name = "Desktop", device = "spec:width=1920dp,height=1080dp", showSystemUi = true)
@Composable
fun ScreenTabsPreview() {
    AppTheme {
        ScreenTabs()
    }
}
