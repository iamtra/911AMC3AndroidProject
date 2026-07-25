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

data object Home
data object Badge
data object BottomSheet
data object Buttons
data object Cards
data object Carousel
data object CheckBox
data object Chip
data object ColumnScreen
data object DatePicker
data object Dialog
data object FullScreenDialog
data object ElevatedButton
data object FilledTonalButton
data object IconButton
data object LazyColumnScreen
data object LazyRowScreen
data object Menu
data object BottomNavigationBar
data object NavigationDrawer
data object OutlineButton
data object ProgressIndicator
data object Radio
data object RowScreen
data object ScaffoldScreen
data object MultiChoiceSegmentButton
data object SingleChoiceSegmentedButton
data object Slider
data object SnackBar
data object SpacerScreen
data object Switch
data object Tabs
data object TextButton
data object TextField
data object TimePicker
data object Toolbar
data object ToolTips
data object BoxScreen
data object TopAppBarScreen

@Composable
fun BaseNavigation() {
    val backStack = remember { mutableStateListOf<Any>(Home) }

    NavDisplay(
        backStack = backStack,
        onBack = {
            backStack.removeLastOrNull()
        },
        entryProvider = { key ->
            when (key) {
                is Home -> NavEntry(key) {
                    ScreenHome(
                        onClickComponent = { route ->
                            val next = when (route) {
                                "Badge" -> Badge
                                "BottomSheet" -> BottomSheet
                                "Buttons" -> Buttons
                                "Cards" -> Cards
                                "Carousel" -> Carousel
                                "CheckBox" -> CheckBox
                                "Chip" -> Chip
                                "Column" -> ColumnScreen
                                "DatePicker" -> DatePicker
                                "Dialog" -> Dialog
                                "FullScreenDialog" -> FullScreenDialog
                                "ElevatedButton" -> ElevatedButton
                                "FilledTonalButton" -> FilledTonalButton
                                "IconButton" -> IconButton
                                "LazyColumn" -> LazyColumnScreen
                                "LazyRow" -> LazyRowScreen
                                "Menu" -> Menu
                                "BottomNavigationBar" -> BottomNavigationBar
                                "NavigationDrawer" -> NavigationDrawer
                                "OutlineButton" -> OutlineButton
                                "ProgressIndicator" -> ProgressIndicator
                                "Radio" -> Radio
                                "Row" -> RowScreen
                                "Scaffold" -> ScaffoldScreen
                                "MultiChoiceSegmentButton" -> MultiChoiceSegmentButton
                                "SingleChoiceSegmentedButton" -> SingleChoiceSegmentedButton
                                "Slider" -> Slider
                                "SnackBar" -> SnackBar
                                "Spacer" -> SpacerScreen
                                "Switch" -> Switch
                                "Tabs" -> Tabs
                                "TextButton" -> TextButton
                                "TextField" -> TextField
                                "TimePicker" -> TimePicker
                                "Toolbar" -> Toolbar
                                "ToolTips" -> ToolTips
                                "Box" -> BoxScreen
                                "TopAppBar" -> TopAppBarScreen
                                else -> null
                            }
                            next?.let { backStack.add(it) }
                        }
                    )
                }

                is Badge -> NavEntry(key) { ScreenBadge(onBack = { backStack.removeLastOrNull() }) }
                is BottomSheet -> NavEntry(key) { ScreenBottomSheet(onBack = { backStack.removeLastOrNull() }) }
                is Buttons -> NavEntry(key) { ScreenButton(onBack = { backStack.removeLastOrNull() }) }
                is Cards -> NavEntry(key) { ScreenCards(onBack = { backStack.removeLastOrNull() }) }
                is Carousel -> NavEntry(key) { ScreenCarousel(onBack = { backStack.removeLastOrNull() }) }
                is CheckBox -> NavEntry(key) { ScreenCheckBox(onBack = { backStack.removeLastOrNull() }) }
                is Chip -> NavEntry(key) { ScreenChip(onBack = { backStack.removeLastOrNull() }) }
                is ColumnScreen -> NavEntry(key) { ScreenColumn(onBack = { backStack.removeLastOrNull() }) }
                is DatePicker -> NavEntry(key) { ScreenDatePicker(onBack = { backStack.removeLastOrNull() }) }
                is Dialog -> NavEntry(key) { ScreenDialog(onBack = { backStack.removeLastOrNull() }) }
                is FullScreenDialog -> NavEntry(key) { ScreenFullScreenDialog(onBack = { backStack.removeLastOrNull() }) }
                is ElevatedButton -> NavEntry(key) { ScreenElevatedButton(onBack = { backStack.removeLastOrNull() }) }
                is FilledTonalButton -> NavEntry(key) { ScreenFilledTonalButton(onBack = { backStack.removeLastOrNull() }) }
                is IconButton -> NavEntry(key) { ScreenIconButton(onBack = { backStack.removeLastOrNull() }) }
                is LazyColumnScreen -> NavEntry(key) { ScreenLazyColumn(onBack = { backStack.removeLastOrNull() }) }
                is LazyRowScreen -> NavEntry(key) { ScreenLazyRow(onBack = { backStack.removeLastOrNull() }) }
                is Menu -> NavEntry(key) { ScreenMenu(onBack = { backStack.removeLastOrNull() }) }
                is BottomNavigationBar -> NavEntry(key) { ScreenBottomNavigationBar(onBack = { backStack.removeLastOrNull() }) }
                is NavigationDrawer -> NavEntry(key) { ScreenNavigationDrawer(onBack = { backStack.removeLastOrNull() }) }
                is OutlineButton -> NavEntry(key) { ScreenOutlineButton(onBack = { backStack.removeLastOrNull() }) }
                is ProgressIndicator -> NavEntry(key) { ScreenProgressIndicator(onBack = { backStack.removeLastOrNull() }) }
                is Radio -> NavEntry(key) { ScreenRadio(onBack = { backStack.removeLastOrNull() }) }
                is RowScreen -> NavEntry(key) { ScreenRow(onBack = { backStack.removeLastOrNull() }) }
                is ScaffoldScreen -> NavEntry(key) { ScreenScaffold(onBack = { backStack.removeLastOrNull() }) }
                is MultiChoiceSegmentButton -> NavEntry(key) { ScreenMultiChoiceSegmentButton(onBack = { backStack.removeLastOrNull() }) }
                is SingleChoiceSegmentedButton -> NavEntry(key) { ScreenSingleChoiceSegmentedButton(onBack = { backStack.removeLastOrNull() }) }
                is Slider -> NavEntry(key) { ScreenSlider(onBack = { backStack.removeLastOrNull() }) }
                is SnackBar -> NavEntry(key) { ScreenSnackBar(onBack = { backStack.removeLastOrNull() }) }
                is SpacerScreen -> NavEntry(key) { ScreenSpacer(onBack = { backStack.removeLastOrNull() }) }
                is Switch -> NavEntry(key) { ScreenSwitchComponent(onBack = { backStack.removeLastOrNull() }) }
                is Tabs -> NavEntry(key) { ScreenTabs(onBack = { backStack.removeLastOrNull() }) }
                is TextButton -> NavEntry(key) { ScreenTextButton(onBack = { backStack.removeLastOrNull() }) }
                is TextField -> NavEntry(key) { ScreenTextField(onBack = { backStack.removeLastOrNull() }) }
                is TimePicker -> NavEntry(key) { ScreenTimePicker(onBack = { backStack.removeLastOrNull() }) }
                is Toolbar -> NavEntry(key) { ScreenToolbar(onBack = { backStack.removeLastOrNull() }) }
                is ToolTips -> NavEntry(key) { ScreenToolTips(onBack = { backStack.removeLastOrNull() }) }
                is BoxScreen -> NavEntry(key) { ScreenBox(onBack = { backStack.removeLastOrNull() }) }
                is TopAppBarScreen -> NavEntry(key) { ScreenTopAppBar(onBack = { backStack.removeLastOrNull() }) }

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
