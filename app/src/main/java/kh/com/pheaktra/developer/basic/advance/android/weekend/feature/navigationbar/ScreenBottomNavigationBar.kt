package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.navigationbar

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import kh.com.pheaktra.developer.basic.advance.android.weekend.R
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenBottomNavigationBar(
    onBack: () -> Unit
) {
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

    Scaffold(
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
            MainContent(selectIndex)
        }
    }
}

@Composable
fun MainContent(selectIndex: Int) {
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
            CONTENT_TYPE.HOME.value -> HomeScreen()
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

@Preview(showBackground = false)
@Composable
fun ScreenBottomNavigationBarPreview() {
    AppTheme {
        ScreenBottomNavigationBar() {

        }
    }
}