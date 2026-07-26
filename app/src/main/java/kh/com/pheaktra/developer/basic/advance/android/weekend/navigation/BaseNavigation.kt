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
                    onClick = { route ->
                        backStack.add(route)
                    },
                )
            }

            entry<NavKey.Badge> {
                ScreenBadge(onBack = { backStack.removeLastOrNull() })
            }

            entry<NavKey.BottomSheet> {
                ScreenBottomSheet(onBack = { backStack.removeLastOrNull() })
            }

            entry<NavKey.Buttons> {
                ScreenButton(onBack = { backStack.removeLastOrNull() })
            }

            entry<NavKey.Cards> {
                ScreenCards(onBack = { backStack.removeLastOrNull() })
            }

            entry<NavKey.Carousel> {
                ScreenCarousel(onBack = { backStack.removeLastOrNull() })
            }

            entry<NavKey.CheckBox> {
                ScreenCheckBox(onBack = { backStack.removeLastOrNull() })
            }

            entry<NavKey.Chip> {
                ScreenChip(onBack = { backStack.removeLastOrNull() })
            }

            entry<NavKey.ColumnScreen> {
                ScreenColumn(onBack = { backStack.removeLastOrNull() })
            }

            entry<NavKey.DatePicker> {
                ScreenDatePicker(onBack = { backStack.removeLastOrNull() })
            }

            entry<NavKey.Dialog> {
                ScreenDialog(onBack = { backStack.removeLastOrNull() })
            }

            entry<NavKey.FullScreenDialog> {
                ScreenFullScreenDialog(onBack = { backStack.removeLastOrNull() })
            }

            entry<NavKey.ElevatedButton> {
                ScreenElevatedButton(onBack = { backStack.removeLastOrNull() })
            }

            entry<NavKey.FilledTonalButton> {
                ScreenFilledTonalButton(onBack = { backStack.removeLastOrNull() })
            }

            entry<NavKey.IconButton> {
                ScreenIconButton(onBack = { backStack.removeLastOrNull() })
            }

            entry<NavKey.LazyColumnScreen> {
                ScreenLazyColumn(onBack = { backStack.removeLastOrNull() })
            }

            entry<NavKey.LazyRowScreen> {
                ScreenLazyRow(onBack = { backStack.removeLastOrNull() })
            }

            entry<NavKey.Menu> {
                ScreenMenu(onBack = { backStack.removeLastOrNull() })
            }

            entry<NavKey.BottomNavigationBar> {
                ScreenBottomNavigationBar(onBack = { backStack.removeLastOrNull() })
            }

            entry<NavKey.NavigationDrawer> {
                ScreenNavigationDrawer(onBack = { backStack.removeLastOrNull() })
            }

            entry<NavKey.OutlineButton> {
                ScreenOutlineButton(onBack = { backStack.removeLastOrNull() })
            }

            entry<NavKey.ProgressIndicator> {
                ScreenProgressIndicator(onBack = { backStack.removeLastOrNull() })
            }

            entry<NavKey.Radio> {
                ScreenRadio(onBack = { backStack.removeLastOrNull() })
            }

            entry<NavKey.RowScreen> {
                ScreenRow(onBack = { backStack.removeLastOrNull() })
            }

            entry<NavKey.ScaffoldScreen> {
                ScreenScaffold(onBack = { backStack.removeLastOrNull() })
            }

            entry<NavKey.MultiChoiceSegmentButton> {
                ScreenMultiChoiceSegmentButton(
                    onBack = { backStack.removeLastOrNull() },
                )
            }

            entry<NavKey.SingleChoiceSegmentedButton> {
                ScreenSingleChoiceSegmentedButton(
                    onBack = { backStack.removeLastOrNull() },
                )
            }

            entry<NavKey.Slider> {
                ScreenSlider(onBack = { backStack.removeLastOrNull() })
            }

            entry<NavKey.SnackBar> {
                ScreenSnackBar(onBack = { backStack.removeLastOrNull() })
            }

            entry<NavKey.SpacerScreen> {
                ScreenSpacer(onBack = { backStack.removeLastOrNull() })
            }

            entry<NavKey.Switch> {
                ScreenSwitchComponent(onBack = { backStack.removeLastOrNull() })
            }

            entry<NavKey.Tabs> {
                ScreenTabs(onBack = { backStack.removeLastOrNull() })
            }

            entry<NavKey.TextButton> {
                ScreenTextButton(onBack = { backStack.removeLastOrNull() })
            }

            entry<NavKey.TextField> {
                ScreenTextField(onBack = { backStack.removeLastOrNull() })
            }

            entry<NavKey.TimePicker> {
                ScreenTimePicker(onBack = { backStack.removeLastOrNull() })
            }

            entry<NavKey.Toolbar> {
                ScreenToolbar(onBack = { backStack.removeLastOrNull() })
            }

            entry<NavKey.ToolTips> {
                ScreenToolTips(onBack = { backStack.removeLastOrNull() })
            }

            entry<NavKey.BoxScreen> {
                ScreenBox(onBack = { backStack.removeLastOrNull() })
            }

            entry<NavKey.TopAppBarScreen> {
                ScreenTopAppBar(onBack = { backStack.removeLastOrNull() })
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
