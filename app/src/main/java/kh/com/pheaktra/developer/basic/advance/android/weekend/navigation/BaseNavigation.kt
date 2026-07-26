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
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.badge.ScreenBadge
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.bottomsheet.ScreenBottomSheet
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
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.box.ScreenBox
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.topbar.ScreenTopAppBar

@Composable
fun BaseNavigation() {
    val backStack = remember { mutableStateListOf<Any>(NavKey.Home) }

    NavDisplay(
        backStack = backStack,
        onBack = {
            backStack.removeLastOrNull()
        },
        entryProvider = { key ->
            when (key) {
                is NavKey.Home -> NavEntry(key) {
                    ScreenHome(
                        onClick = { route ->
                            backStack.add(route)
                        }
                    )
                }

                is NavKey.Badge -> NavEntry(key) { ScreenBadge(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.BottomSheet -> NavEntry(key) { ScreenBottomSheet(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.Buttons -> NavEntry(key) { ScreenButton(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.Cards -> NavEntry(key) { ScreenCards(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.Carousel -> NavEntry(key) { ScreenCarousel(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.CheckBox -> NavEntry(key) { ScreenCheckBox(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.Chip -> NavEntry(key) { ScreenChip(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.ColumnScreen -> NavEntry(key) { ScreenColumn(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.DatePicker -> NavEntry(key) { ScreenDatePicker(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.Dialog -> NavEntry(key) { ScreenDialog(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.FullScreenDialog -> NavEntry(key) { ScreenFullScreenDialog(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.ElevatedButton -> NavEntry(key) { ScreenElevatedButton(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.FilledTonalButton -> NavEntry(key) { ScreenFilledTonalButton(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.IconButton -> NavEntry(key) { ScreenIconButton(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.LazyColumnScreen -> NavEntry(key) { ScreenLazyColumn(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.LazyRowScreen -> NavEntry(key) { ScreenLazyRow(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.Menu -> NavEntry(key) { ScreenMenu(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.BottomNavigationBar -> NavEntry(key) { ScreenBottomNavigationBar(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.NavigationDrawer -> NavEntry(key) { ScreenNavigationDrawer(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.OutlineButton -> NavEntry(key) { ScreenOutlineButton(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.ProgressIndicator -> NavEntry(key) { ScreenProgressIndicator(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.Radio -> NavEntry(key) { ScreenRadio(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.RowScreen -> NavEntry(key) { ScreenRow(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.ScaffoldScreen -> NavEntry(key) { ScreenScaffold(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.MultiChoiceSegmentButton -> NavEntry(key) { ScreenMultiChoiceSegmentButton(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.SingleChoiceSegmentedButton -> NavEntry(key) { ScreenSingleChoiceSegmentedButton(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.Slider -> NavEntry(key) { ScreenSlider(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.SnackBar -> NavEntry(key) { ScreenSnackBar(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.SpacerScreen -> NavEntry(key) { ScreenSpacer(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.Switch -> NavEntry(key) { ScreenSwitchComponent(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.Tabs -> NavEntry(key) { ScreenTabs(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.TextButton -> NavEntry(key) { ScreenTextButton(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.TextField -> NavEntry(key) { ScreenTextField(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.TimePicker -> NavEntry(key) { ScreenTimePicker(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.Toolbar -> NavEntry(key) { ScreenToolbar(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.ToolTips -> NavEntry(key) { ScreenToolTips(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.BoxScreen -> NavEntry(key) { ScreenBox(onBack = { backStack.removeLastOrNull() }) }
                is NavKey.TopAppBarScreen -> NavEntry(key) { ScreenTopAppBar(onBack = { backStack.removeLastOrNull() }) }

                else -> NavEntry(Unit) {
                    NavigationNotFound(
                        onBack = {
                            backStack.removeLastOrNull()
                        }
                    )
                }
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
