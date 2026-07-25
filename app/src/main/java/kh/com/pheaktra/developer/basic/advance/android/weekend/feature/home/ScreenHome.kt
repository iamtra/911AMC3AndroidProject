package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kh.com.pheaktra.developer.basic.advance.android.weekend.R
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.MaterialComponentModel
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme

val componentList = listOf(
    MaterialComponentModel(1, "Badge", "A small circle that can contain a number or icon", "Badge"),
    MaterialComponentModel(2, "Bottom Sheet", "A sheet that slides up from the bottom of the screen", "BottomSheet"),
    MaterialComponentModel(3, "Buttons", "Different types of buttons: Filled, Tonal, Outlined, etc.", "Buttons"),
    MaterialComponentModel(4, "Cards", "Contain content and actions about a single subject", "Cards"),
    MaterialComponentModel(5, "Carousel", "A horizontally scrollable list of items", "Carousel"),
    MaterialComponentModel(6, "CheckBox", "Allows users to select one or more items", "CheckBox"),
    MaterialComponentModel(7, "Chip", "Compact elements that represent an attribute, text, entity, or action", "Chip"),
    MaterialComponentModel(8, "Column", "A layout composable that places its children in a vertical sequence", "Column"),
    MaterialComponentModel(9, "DatePicker", "Allows users to select a date", "DatePicker"),
    MaterialComponentModel(10, "Dialog", "Inform users about a task and can contain critical information", "Dialog"),
    MaterialComponentModel(11, "FullScreenDialog", "A dialog that covers the entire screen", "FullScreenDialog"),
    MaterialComponentModel(12, "Elevated Button", "A button with a shadow that increases when pressed", "ElevatedButton"),
    MaterialComponentModel(13, "Filled Tonal Button", "A button with a secondary color background", "FilledTonalButton"),
    MaterialComponentModel(14, "Icon Button", "A button that displays an icon", "IconButton"),
    MaterialComponentModel(15, "LazyColumn", "A vertically scrolling list that only composes visible items", "LazyColumn"),
    MaterialComponentModel(16, "LazyRow", "A horizontally scrolling list that only composes visible items", "LazyRow"),
    MaterialComponentModel(17, "Menu", "Displays a list of choices on a temporary surface", "Menu"),
    MaterialComponentModel(18, "Bottom Navigation Bar", "Provides quick access to primary destinations", "BottomNavigationBar"),
    MaterialComponentModel(19, "Navigation Drawer", "Provides access to destinations in your app", "NavigationDrawer"),
    MaterialComponentModel(20, "Outline Button", "A button with a border and no background", "OutlineButton"),
    MaterialComponentModel(21, "Progress Indicator", "Indicates the status of an ongoing process", "ProgressIndicator"),
    MaterialComponentModel(22, "Radio", "Allows users to select one option from a set", "Radio"),
    MaterialComponentModel(23, "Row", "A layout composable that places its children in a horizontal sequence", "Row"),
    MaterialComponentModel(24, "Scaffold", "Implements the basic Material Design visual layout structure", "Scaffold"),
    MaterialComponentModel(25, "Multi Choice Segment Button", "Allows selecting multiple segments", "MultiChoiceSegmentButton"),
    MaterialComponentModel(26, "Single Choice Segmented Button", "Allows selecting a single segment", "SingleChoiceSegmentedButton"),
    MaterialComponentModel(27, "Slider", "Allows users to make selections from a range of values", "Slider"),
    MaterialComponentModel(28, "SnackBar", "Provides brief messages about app processes at the bottom", "SnackBar"),
    MaterialComponentModel(29, "Spacer", "Represents an empty space with flexible size", "Spacer"),
    MaterialComponentModel(30, "Switch", "Allows users to toggle between two states", "Switch"),
    MaterialComponentModel(31, "Tabs", "Organize content across different screens and views", "Tabs"),
    MaterialComponentModel(32, "Text Button", "A button with no border and no background", "TextButton"),
    MaterialComponentModel(33, "TextField", "Allows users to enter and edit text", "TextField"),
    MaterialComponentModel(34, "TimePicker", "Allows users to select a time", "TimePicker"),
    MaterialComponentModel(35, "Toolbar", "A standard app bar used at the top of screens", "Toolbar"),
    MaterialComponentModel(36, "ToolTips", "Provide informative text when users hover over or long-press", "ToolTips"),
    MaterialComponentModel(37, "Box", "A layout composable that stacks its children on top of each other", "Box"),
    MaterialComponentModel(38, "TopAppBar", "Displays information and actions at the top of a screen", "TopAppBar"),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenHome(
    onClickComponent: (route: String) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Box(
                        modifier = Modifier.size(48.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier.size(36.dp),
                            painter = painterResource(R.drawable.ic_light),
                            contentDescription = "Light",
                        )
                    }
                },
                title = {
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = stringResource(R.string.app_name)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = Color.Yellow
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            items(componentList) { item ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            onClick = {
                                onClickComponent(item.route)
                            }
                        )
                        .padding(16.dp)
                ) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = item.description,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    thickness = 0.5.dp,
                    color = MaterialTheme.colorScheme.outlineVariant
                )
            }
        }
    }
}

@Composable
@Preview
fun ScreenHomePreview() {
    AppTheme {
        ScreenHome(
            onClickComponent = {}
        )
    }
}
