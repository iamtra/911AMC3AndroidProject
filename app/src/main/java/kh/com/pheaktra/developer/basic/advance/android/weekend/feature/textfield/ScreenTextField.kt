package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.textfield

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kh.com.pheaktra.developer.basic.advance.android.weekend.R
import kh.com.pheaktra.developer.model.general.MaterialComponentModel
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTextField(
    item: MaterialComponentModel,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    val focusRequester = remember { FocusRequester() }

    var text by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordTransform by remember { mutableStateOf(false) }
    var textActionDone by remember { mutableStateOf("") }

    val isInvalidText = remember(text) {
        text.length > 10
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    Scaffold(
        modifier = Modifier
            .navigationBarsPadding()
            .clickable(
                onClick = {
                    focusManager.clearFocus()
                },
                indication = null,
                interactionSource = null
            ),
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
                    Text(item.title)
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
            )
        },
        bottomBar = {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    println("====> $text")
                }
            ) {
                Text("Submit")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .navigationBarsPadding()
                .verticalScroll(state = scrollState)
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp)
                    .focusRequester(focusRequester),
                value = textActionDone,
                onValueChange = { value ->
                    text = value
                },
                enabled = true,
                readOnly = false,
                textStyle = MaterialTheme.typography.titleLarge.copy(
//                    color = MaterialTheme.colorScheme.onPrimary,
//                    background = MaterialTheme.colorScheme.primary,
//                    fontSize = 24.sp,
//                    fontWeight = FontWeight.Bold,
                ),
                label = {
                    Text("Action Done")
                },
                placeholder = {
                    Text("Enter your username")
                },
                isError = isInvalidText,
                supportingText = {
                    if (isInvalidText) {
                        Text("This is a supporting text")
                    }
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_user),
                        contentDescription = "User Icon"
                    )
                },
                trailingIcon = {
                    if (text.isNotEmpty()) {
                        Icon(
                            modifier = Modifier.clickable(
                                onClick = {
                                    text = ""
                                }
                            ),
                            painter = painterResource(R.drawable.ic_cancel),
                            contentDescription = "User Icon"
                        )
                    }
                },
                singleLine = true,
//                prefix = {
//                    Text("Prefix")
//                },
//                suffix = {
//                    Text("Suffix")
//                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        Toast.makeText(context, "Keyboard action done", Toast.LENGTH_SHORT).show()
                    }
                )
            )

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp),
                value = text,
                onValueChange = { value ->
                    text = value
                },
                enabled = true,
                readOnly = false,
                textStyle = MaterialTheme.typography.titleLarge.copy(
//                    color = MaterialTheme.colorScheme.onPrimary,
//                    background = MaterialTheme.colorScheme.primary,
//                    fontSize = 24.sp,
//                    fontWeight = FontWeight.Bold,
                ),
                label = {
                    Text("Action Next")
                },
                placeholder = {
                    Text("Enter your username")
                },
                isError = isInvalidText,
                supportingText = {
                    if (isInvalidText) {
                        Text("This is a supporting text")
                    }
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_user),
                        contentDescription = "User Icon"
                    )
                },
                trailingIcon = {
                    if (text.isNotEmpty()) {
                        Icon(
                            modifier = Modifier.clickable(
                                onClick = {
                                    text = ""
                                }
                            ),
                            painter = painterResource(R.drawable.ic_cancel),
                            contentDescription = "User Icon"
                        )
                    }
                },
                singleLine = true,
//                prefix = {
//                    Text("Prefix")
//                },
//                suffix = {
//                    Text("Suffix")
//                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
//                keyboardActions = KeyboardActions(
//                    onNext = {
//                        Toast.makeText(context, "Keyboard action done", Toast.LENGTH_SHORT).show()
//                    }
//                )
            )

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp),
                value = text,
                onValueChange = { value ->
                    text = value
                },
                enabled = true,
                readOnly = false,
                textStyle = MaterialTheme.typography.titleLarge.copy(
//                    color = MaterialTheme.colorScheme.onPrimary,
//                    background = MaterialTheme.colorScheme.primary,
//                    fontSize = 24.sp,
//                    fontWeight = FontWeight.Bold,
                ),
                label = {
                    Text("Action Go")
                },
                placeholder = {
                    Text("Enter your username")
                },
                isError = isInvalidText,
                supportingText = {
                    if (isInvalidText) {
                        Text("This is a supporting text")
                    }
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_user),
                        contentDescription = "User Icon"
                    )
                },
                trailingIcon = {
                    if (text.isNotEmpty()) {
                        Icon(
                            modifier = Modifier.clickable(
                                onClick = {
                                    text = ""
                                }
                            ),
                            painter = painterResource(R.drawable.ic_cancel),
                            contentDescription = "User Icon"
                        )
                    }
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Go
                ),
                keyboardActions = KeyboardActions(
                    onGo = {
                        Toast.makeText(context, "Keyboard action go", Toast.LENGTH_SHORT).show()
                    }
                )
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp),
                value = text,
                onValueChange = { value ->
                    text = value
                },
                enabled = true,
                readOnly = false,
                textStyle = MaterialTheme.typography.titleLarge.copy(
//                    color = MaterialTheme.colorScheme.onPrimary,
//                    background = MaterialTheme.colorScheme.primary,
//                    fontSize = 24.sp,
//                    fontWeight = FontWeight.Bold,
                ),
                label = {
                    Text("Action Search")
                },
                placeholder = {
                    Text("Enter your username")
                },
                isError = isInvalidText,
                supportingText = {
                    if (isInvalidText) {
                        Text("This is a supporting text")
                    }
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_user),
                        contentDescription = "User Icon"
                    )
                },
                trailingIcon = {
                    if (text.isNotEmpty()) {
                        Icon(
                            modifier = Modifier.clickable(
                                onClick = {
                                    text = ""
                                }
                            ),
                            painter = painterResource(R.drawable.ic_cancel),
                            contentDescription = "User Icon"
                        )
                    }
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        Toast.makeText(context, "Keyboard action search", Toast.LENGTH_SHORT).show()
                    }
                )
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp),
                value = number,
                onValueChange = { value ->
                    number = value
                },
                enabled = true,
                readOnly = false,
                label = {
                    Text("Keyboard Number")
                },
                placeholder = {
                    Text("Enter your username")
                },
                isError = isInvalidText,
                supportingText = {
                    if (isInvalidText) {
                        Text("This is a supporting text")
                    }
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_user),
                        contentDescription = "User Icon"
                    )
                },
                trailingIcon = {
                    if (text.isNotEmpty()) {
                        Icon(
                            modifier = Modifier.clickable(
                                onClick = {
                                    text = ""
                                }
                            ),
                            painter = painterResource(R.drawable.ic_cancel),
                            contentDescription = "User Icon"
                        )
                    }
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        Toast.makeText(context, "Keyboard action search", Toast.LENGTH_SHORT).show()
                    }
                )
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp),
                value = number,
                onValueChange = { value ->
                    number = value
                },
                label = {
                    Text("Keyboard Uri")
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_user),
                        contentDescription = "User Icon"
                    )
                },
                trailingIcon = {
                    if (text.isNotEmpty()) {
                        Icon(
                            modifier = Modifier.clickable(
                                onClick = {
                                    text = ""
                                }
                            ),
                            painter = painterResource(R.drawable.ic_cancel),
                            contentDescription = "User Icon"
                        )
                    }
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Uri
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        Toast.makeText(context, "Keyboard action search", Toast.LENGTH_SHORT).show()
                    }
                )
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp),
                value = number,
                onValueChange = { value ->
                    number = value
                },
                label = {
                    Text("Keyboard number password")
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        Toast.makeText(context, "Keyboard action search", Toast.LENGTH_SHORT).show()
                    }
                )
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp),
                value = password,
                onValueChange = { value ->
                    password = value
                },
                label = {
                    Text("Keyboard password")
                },
                singleLine = true,
                visualTransformation = if (isPasswordTransform) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        Toast.makeText(context, "Keyboard action search", Toast.LENGTH_SHORT).show()
                    }
                ),
                trailingIcon = {
                    Icon(
                        modifier = Modifier.clickable(
                            onClick = {
                                isPasswordTransform = !isPasswordTransform
                            }
                        ),
                        painter = painterResource(
                            if (isPasswordTransform) R.drawable.ic_visibility_off else R.drawable.ic_visibility
                        ),
                        contentDescription = "Password Icon",
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ScreenTextFieldPreview() {
    AppTheme {
        ScreenTextField(
            item = MaterialComponentModel(
                1,
                "TextField",
                "TextField description",
                { "" },
                ""
            ),
            onBack = {}
        )
    }
}
