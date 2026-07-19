package kh.com.pheaktra.developer.basic.advance.android.weekend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.bottomsheet.ScreenBottomSheet
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.checkbox.ScreenCheckBox
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.chip.ScreenChip
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.datepicker.ScreenDatePickerDialog
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.dialog.ScreenFullScreenDialog
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.dialog.ScreenProgressIndicator
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.menu.ScreenMenu
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.navigationbar.ScreenBottomNavigationBar
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.navigationdrawer.ScreenNavigationDrawer
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.progressindicator.ScreenDialog
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.radio.ScreenRadio
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.slider.ScreenSlider
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.snackbar.ScreenSnackBar
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.switchcomponent.ScreenSwitchComponent
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.tab.ScreenTabs
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.textfield.ScreenTextField
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.timepicker.ScreenTimePicker
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.toolbar.ScreenToolbar
import kh.com.pheaktra.developer.basic.advance.android.weekend.feature.toolstips.ScreenToolTips
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            AppTheme {
////               ScreenScaffold()
////                ScreenLazyRow()
////                ScreenTopAppBar()
////                ScreenBadge()
////                ScreenOutlineButton()
////                ScreenElevatedButton()
////                ScreenTextButton()
////                ScreenButton()
////                ScreenSingleChoiceSegmentedButton()
////                ScreenMultiChoiceSegmentButton {  }
////                ScreenCarousel()
////                ScreenCheckBox { }
////                ScreenChip {  }
////                ScreenDatePickerDialog {  }
////                ScreenTimePicker {  }
////                ScreenDialog {  }
////                ScreenProgressIndicator {  }
////                ScreenFullScreenDialog {  }
////                ScreenMenu {  }
////                ScreenBottomNavigationBar {  }
////                ScreenNavigationDrawer {
////
////                }
////                ScreenRadio(
////                    onBack = {}
////                )
////                ScreenBottomSheet(
////                    onBack = {}
////                )
////                ScreenSlider(
////                    onBack = {}
////                )
////                ScreenSnackBar(
////                    onBack = {}
////                )
////                ScreenSwitchComponent()
//                ScreenTabs()
//            ScreenTextField(
//                onBack = {}
//            )
//            ScreenToolbar()
            ScreenToolTips()
        }
    }
}