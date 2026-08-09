package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.stateviewmodel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kh.com.pheaktra.developer.basic.advance.android.weekend.R
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.MaterialComponentModel
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.UserModel
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenStateViewModel(
    item: MaterialComponentModel,
    onBack: () -> Unit,
    stateVM: StateVM = viewModel()
) {
    val userList by stateVM.userListUiState.collectAsStateWithLifecycle()

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var isShowDialog by remember { mutableStateOf(false) }

    LaunchedEffect(userList) {
        println("=====> UI $userList")
    }

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
        floatingActionButton = {
            FilledTonalButton(
                onClick = {
                    isShowDialog = true
                }
            ) {
                Text("Add")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            items(userList.size) { index ->
                val user = userList[index]
                val (firstName, lastName) = user
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .padding(horizontal = 16.dp)
                        .padding(vertical = 8.dp)
                        .background(
                            color = MaterialTheme.colorScheme.background,
                            shape = MaterialTheme.shapes.medium
                        )
                ) {
                    Text(
                        modifier = Modifier
                            .padding(8.dp),
                        text = "$firstName $lastName"
                    )
                    TextButton(
                        onClick = {
                            stateVM.delete(user)
                        }
                    ) {
                        Text("Delete")
                    }
                }
            }
        }

        if (isShowDialog) {
            BasicAlertDialog(
                onDismissRequest = {

                },
                modifier = Modifier,
            ) {
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .background(
                            color = MaterialTheme.colorScheme.background,
                            shape = MaterialTheme.shapes.medium
                        )
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        label = {
                            Text("First Name")
                        },
                        placeholder = {
                            Text("Enter Your First Name")
                        },
                        value = firstName,
                        onValueChange = { value ->
                            firstName = value
                        }
                    )
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        label = {
                            Text("Last Name")
                        },
                        placeholder = {
                            Text("Enter Your First Name")
                        },
                        value = lastName,
                        onValueChange = { value ->
                            lastName = value
                        }
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Row(
                        Modifier.fillMaxWidth()
                    ) {
                        TextButton(
                            modifier = Modifier
                                .weight(1f)
                                .padding(16.dp),
                            onClick = {
                                isShowDialog = false
                            },
                        ) {
                            Text("Cancel")
                        }
                        Button(
                            modifier = Modifier
                                .weight(1f)
                                .padding(16.dp),
                            onClick = {
                                stateVM.save(
                                    user = UserModel(
                                        firstName = firstName,
                                        lastName = lastName
                                    )
                                )
                                isShowDialog = false
                            },
                            enabled = firstName.isNotEmpty() && lastName.isNotEmpty()
                        ) {
                            Text("Save")
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ScreenStateViewModelPreview() {
    AppTheme() {
        ScreenStateViewModel(
            item = MaterialComponentModel(
                1,
                "State (ViewModel)",
                "StateViewModel description",
                { "" },
                ""
            ),
            onBack = {},
        )
    }
}