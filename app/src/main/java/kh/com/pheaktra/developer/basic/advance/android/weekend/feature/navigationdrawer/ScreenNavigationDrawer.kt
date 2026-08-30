package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.navigationdrawer

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kh.com.pheaktra.developer.basic.advance.android.weekend.R
import kh.com.pheaktra.developer.model.general.MaterialComponentModel
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.Red40
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenNavigationDrawer(
    item: MaterialComponentModel,
    onBack: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var selectIndex by remember { mutableIntStateOf(0) }

    val tabItems = listOf(
        NavigationItem(
            icon = R.drawable.ic_home,
            label = "Home"
        ),
        NavigationItem(
            icon = R.drawable.ic_favorite,
            label = "Favorite"
        ),
        NavigationItem(
            icon = R.drawable.ic_setting,
            label = "Setting"
        )
    )

    val drawerList = listOf(
        DrawerMenuItem(route = "A", label = "Profile", icon = R.drawable.ic_favorite),
        DrawerMenuItem(route = "B", label = "Accounts", icon = R.drawable.ic_home),
        DrawerMenuItem(route = "C", label = "Transfer", icon = R.drawable.ic_share),
        DrawerMenuItem(route = "D", label = "Payment", icon = R.drawable.ic_open_folder),
        DrawerMenuItem(route = "E", label = "Favorite", icon = R.drawable.ic_favorite),
        DrawerMenuItem(
            route = "F",
            label = "Notification",
            icon = R.drawable.ic_make_copy,
            badge = "New"
        ),
        DrawerMenuItem(route = "G", label = "History", icon = R.drawable.ic_ticked),
        DrawerMenuItem(route = "I", label = "Security", icon = R.drawable.ic_create),
        DrawerMenuItem(route = "J", label = "Help Center", icon = R.drawable.ic_download),
        DrawerMenuItem(route = "K", label = "Setting", icon = R.drawable.ic_setting),
        DrawerMenuItem(route = "L", label = "Logout", icon = R.drawable.ic_arrow_back)
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(
                    drawerList = drawerList,
                    scrollState = scrollState,
                    onBack = { item ->
                        println("=====> ${item.route}")
                        scope.launch {
                            drawerState.close()
                        }
                    }
                )
            }
        }
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
            bottomBar = {
                NavigationBar {
                    tabItems.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = index == selectIndex,
                            alwaysShowLabel = true,
                            onClick = {
                                selectIndex = index
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(item.icon),
                                    contentDescription = "Home"
                                )
                            },
                            label = {
                                Text(
                                    text = item.label,
                                    style = MaterialTheme.typography.labelMedium
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                                indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                            )
                        )
                    }
                }
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                MainContent(
                    selectIndex = selectIndex,
                    onProfileClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                )
            }
        }

    }
}

@Composable
fun DrawerContent(
    drawerList: List<DrawerMenuItem>,
    scrollState: ScrollState,
    onBack: (DrawerMenuItem) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.border(
                    width = 3.dp,
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "User Profile",
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Buon Pheaktra",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "pheaktra@example.com",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        HorizontalDivider()

        drawerList.forEach { item ->
            NavigationDrawerItem(
                modifier = Modifier.padding(vertical = 12.dp),
                label = {
                    Text(text = item.label)
                },
                selected = false,
                onClick = {
                    onBack(item)
                },
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = "Profile"
                    )
                },
                badge = {
                    Text(
                        color = Red40,
                        text = "New"
                    )
                }
            )
            HorizontalDivider()
        }
    }
}

@Composable
fun MainContent(
    selectIndex: Int,
    onProfileClick: () -> Unit = {}
) {
    AnimatedContent(
        targetState = selectIndex,
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
    ) { index ->
        when (index) {
            CONTENT_TYPE.HOME.value -> HomeScreen(
                onProfileClick = onProfileClick
            )

            CONTENT_TYPE.FAVORITE.value -> FavoriteScreen()
            CONTENT_TYPE.SETTINGS.value -> SettingScreen()
        }
    }
}

enum class CONTENT_TYPE(val value: Int) {
    HOME(0),
    FAVORITE(1),
    SETTINGS(2)
}

data class NavigationItem(
    @DrawableRes val icon: Int,
    val enabled: Boolean = true,
    val label: String,
    val alwaysShowLabel: Boolean = true,
)

data class DrawerMenuItem(
    val route: String,
    val label: String,
    @DrawableRes val icon: Int,
    val badge: String? = null
)

@Preview(showBackground = false)
@Composable
fun ScreenNavigationDrawerPreview() {
    AppTheme {
        ScreenNavigationDrawer(
            item = MaterialComponentModel(
                1,
                "Navigation Drawer",
                "Navigation Drawer description",
                { "" },
                ""
            )
        ) {

        }
    }
}