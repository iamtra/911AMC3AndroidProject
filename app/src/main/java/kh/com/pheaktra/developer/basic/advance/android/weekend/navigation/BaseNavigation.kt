package kh.com.pheaktra.developer.basic.advance.android.weekend.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.badge.ScreenBadge
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.bottomsheet.ScreenBottomSheet
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.box.ScreenBox
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.buttons.ScreenButton
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.cards.ScreenCards
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.carousel.ScreenCarousel
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.checkbox.ScreenCheckBox
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.chip.ScreenChip
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.column.ScreenColumn
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.datepicker.ScreenDatePicker
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.dialog.ScreenDialog
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.dialog.ScreenFullScreenDialog
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.elevatedbutton.ScreenElevatedButton
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.filltonalbutton.ScreenFilledTonalButton
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.home.ScreenHome
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.iconbuttons.ScreenIconButton
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.lazycolumn.ScreenLazyColumn
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.lazyrow.ScreenLazyRow
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.menu.ScreenMenu
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.navigationbar.ScreenBottomNavigationBar
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.navigationdrawer.ScreenNavigationDrawer
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.notification.ScreenNotificationList
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.outlinebutton.ScreenOutlineButton
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.progressindicator.ScreenProgressIndicator
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.radio.ScreenRadio
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.row.ScreenRow
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.scaffold.ScreenScaffold
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.segmentedbutton.ScreenMultiChoiceSegmentButton
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.segmentedbutton.ScreenSingleChoiceSegmentedButton
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.slider.ScreenSlider
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.snackbar.ScreenSnackBar
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.spacer.ScreenSpacer
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.switchcomponent.ScreenSwitchComponent
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.tab.ScreenTabs
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.textbutton.ScreenTextButton
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.textfield.ScreenTextField
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.timepicker.ScreenTimePicker
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.toolbar.ScreenToolbar
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.tooltips.ScreenToolTips
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.topbar.ScreenTopAppBar
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.MaterialComponentModel
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.route

@Composable
fun BaseNavigation() {
    val backStack = remember { mutableStateListOf<Any>(NavKey.Home) }

    NavDisplay(
        backStack = backStack,
        onBack = {
            backStack.removeLastOrNull()
        },
        entryProvider = entryProvider {
            entry<NavKey.Home> {
                ScreenHome(
                    onClick = { item ->
                        backStack.add(item.route())
                    },
                    onClickNotification = { title ->
                        backStack.add(NavKey.NotificationList(title))
                    }
                )
            }

            entry<NavKey.Badge> { key ->
                ScreenBadge(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.BottomSheet> { key ->
                ScreenBottomSheet(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.Buttons> { key ->
                ScreenButton(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.Cards> { key ->
                ScreenCards(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.Carousel> { key ->
                ScreenCarousel(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.CheckBox> { key ->
                ScreenCheckBox(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.Chip> { key ->
                ScreenChip(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.ColumnScreen> { key ->
                ScreenColumn(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.DatePicker> { key ->
                ScreenDatePicker(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.Dialog> { key ->
                ScreenDialog(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.FullScreenDialog> { key ->
                ScreenFullScreenDialog(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.ElevatedButton> { key ->
                ScreenElevatedButton(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.FilledTonalButton> { key ->
                ScreenFilledTonalButton(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.IconButton> { key ->
                ScreenIconButton(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.LazyColumnScreen> { key ->
                ScreenLazyColumn(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.LazyRowScreen> { key ->
                ScreenLazyRow(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.Menu> { key ->
                ScreenMenu(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.BottomNavigationBar> { key ->
                ScreenBottomNavigationBar(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.NavigationDrawer> { key ->
                ScreenNavigationDrawer(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.OutlineButton> { key ->
                ScreenOutlineButton(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.ProgressIndicator> { key ->
                ScreenProgressIndicator(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.Radio> { key ->
                ScreenRadio(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.RowScreen> { key ->
                ScreenRow(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.ScaffoldScreen> { key ->
                ScreenScaffold(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.MultiChoiceSegmentButton> { key ->
                ScreenMultiChoiceSegmentButton(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() },
                )
            }

            entry<NavKey.SingleChoiceSegmentedButton> { key ->
                ScreenSingleChoiceSegmentedButton(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() },
                )
            }

            entry<NavKey.Slider> { key ->
                ScreenSlider(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.SnackBar> { key ->
                ScreenSnackBar(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.SpacerScreen> { key ->
                ScreenSpacer(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.Switch> { key ->
                ScreenSwitchComponent(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.Tabs> { key ->
                ScreenTabs(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.TextButton> { key ->
                ScreenTextButton(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.TextField> { key ->
                ScreenTextField(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.TimePicker> { key ->
                ScreenTimePicker(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.Toolbar> { key ->
                ScreenToolbar(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.ToolTips> { key ->
                ScreenToolTips(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.BoxScreen> { key ->
                ScreenBox(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.TopAppBarScreen> { key ->
                ScreenTopAppBar(
                    item = key.data,
                    onBack = { backStack.removeLastOrNull() }
                )
            }

            entry<NavKey.NotificationList> { key ->
                ScreenNotificationList(
                    title = key.title,
                    onBack = { backStack.removeLastOrNull() }
                )
            }
        }
    )
}

@Composable
private fun NavigationNotFound(
    onBack: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Unknown route")
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onBack
            ) {
                Text("Back")
            }
        }
    }
}
