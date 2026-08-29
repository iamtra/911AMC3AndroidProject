package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.api.createuser

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.outlined.Cake
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenu
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kh.com.pheaktra.developer.basic.advance.android.weekend.R
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.request.CreateUserRequest
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.request.toUpdateUserRequest
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.response.UserModelResponse
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.response.getFullName
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme
import kh.com.pheaktra.developer.basic.advance.android.weekend.util.BaseUiState
import kh.com.pheaktra.developer.basic.advance.android.weekend.util.LoadingUtil

@Composable
fun ScreenCreateUser(
    user: UserModelResponse?,
    onBack: () -> Unit = {},
    createUserVM: CreateUserVM = viewModel()
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val createUserUiState by createUserVM.createUserUiState.collectAsStateWithLifecycle()

//    LaunchedEffect(Unit) {
//        createUserVM.getUserDetail(userId)
//    }

    LaunchedEffect(key1 = createUserUiState) {
        when (val state = createUserUiState) {
            is BaseUiState.Loading -> LoadingUtil.showLoading()

            is BaseUiState.Success -> {
                LoadingUtil.hideLoading()
                onBack()
            }

            is BaseUiState.Failure -> {
                LoadingUtil.hideLoading()
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()

            }

            is BaseUiState.Exception -> {
                LoadingUtil.hideLoading()
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }

            else -> {
                LoadingUtil.hideLoading()
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            createUserVM.onDispose()
        }
    }

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
                            modifier = Modifier.size(48.dp),
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = "Back",

                            )
                    }
                },
                title = {
                    Text(
                        text = if (user == null) "Create User" else "Edit User",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                    )
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        CreateUserForm(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
                .verticalScroll(scrollState),
            user = user,
            onSubmit = { body ->
                if (user == null) {
                    createUserVM.createUser(body)
                } else {
                    createUserVM.updateUser(user.id, body.toUpdateUserRequest())
                }
            }
        )
    }
}

@Composable
fun CreateUserForm(
    modifier: Modifier = Modifier,
    user: UserModelResponse?,
    onSubmit: (CreateUserRequest) -> Unit,
) {
    var username by rememberSaveable {
        mutableStateOf(user?.username.orEmpty())
    }

    var email by rememberSaveable {
        mutableStateOf(user?.email.orEmpty())
    }

    var password by rememberSaveable {
        mutableStateOf(user?.password.orEmpty())
    }

    var firstName by rememberSaveable {
        mutableStateOf(user?.firstName.orEmpty())
    }

    var lastName by rememberSaveable {
        mutableStateOf(user?.lastName.orEmpty())
    }

    var age by rememberSaveable {
        mutableStateOf(user?.age?.toString().orEmpty())
    }

    var gender by rememberSaveable {
        mutableStateOf(user?.gender.orEmpty())
    }

    var passwordVisible by rememberSaveable {
        mutableStateOf(false)
    }

    var genderExpanded by remember {
        mutableStateOf(false)
    }

    val genders = listOf(
        "Male",
        "Female",
        "Other",
    )

    val isFormValid by remember(
        username,
        email,
        password,
        firstName,
        lastName,
        age,
        gender,
    ) {
        derivedStateOf {
            username.isNotBlank() &&
                    email.isNotBlank() &&
                    password.isNotBlank() &&
                    firstName.isNotBlank() &&
                    lastName.isNotBlank() &&
                    age.toIntOrNull() != null &&
                    gender.isNotBlank()
        }
    }
    Column(
        modifier = modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        // Username
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = {
                Text("Username")
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )

        // Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = {
                Text("Email")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )

        // Password
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = {
                Text("Password")
            },
            singleLine = true,
            visualTransformation = if (passwordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
            ),
            trailingIcon = {
                IconButton(
                    onClick = {
                        passwordVisible = !passwordVisible
                    },
                ) {
                    Icon(
                        imageVector = if (passwordVisible) {
                            Icons.Default.Visibility
                        } else {
                            Icons.Default.VisibilityOff
                        },
                        contentDescription = null,
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
        )

        // First Name
        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = {
                Text("First Name")
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )

        // Last Name
        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = {
                Text("Last Name")
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )

        // Age
        OutlinedTextField(
            value = age,
            onValueChange = { value ->
                if (value.all { it.isDigit() }) {
                    age = value
                }
            },
            label = {
                Text("Age")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
        )

        // Gender
        ExposedDropdownMenuBox(
            expanded = genderExpanded,
            onExpandedChange = {
                genderExpanded = !genderExpanded
            },
        ) {
            OutlinedTextField(
                value = gender,
                onValueChange = {},
                readOnly = true,
                label = {
                    Text("Gender")
                },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = genderExpanded,
                    )
                },
                modifier = Modifier
                    .menuAnchor(type = ExposedDropdownMenuAnchorType.PrimaryEditable)
                    .fillMaxWidth(),
            )

            ExposedDropdownMenu(
                expanded = genderExpanded,
                onDismissRequest = {
                    genderExpanded = false
                },
            ) {
                genders.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            Text(item)
                        },
                        onClick = {
                            gender = item
                            genderExpanded = false
                        },
                    )
                }
            }
        }

        Button(
            onClick = {
                onSubmit(
                    CreateUserRequest(
                        username = username.trim(),
                        email = email.trim(),
                        password = password,
                        firstName = firstName.trim(),
                        lastName = lastName.trim(),
                        age = age.toInt(),
                        gender = gender,
                    ),
                )
            },
            enabled = isFormValid,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = if (user == null) "Create User" else "Edit User"
            )
        }

        Spacer(
            modifier = Modifier.height(16.dp),
        )
    }
}

@Preview
@Composable
fun ScreenCreateUserPreview() {
    AppTheme {
        ScreenCreateUser(
            user = UserModelResponse(
                id = 1,
                username = "pheaktra",
                email = "pheaktra@example.com",
                password = "123456",
                createdAt = "2026-08-29T09:00:00.000Z",
                updatedAt = "2026-08-29T09:00:00.000Z",
                firstName = "Pheaktra",
                lastName = "Buon",
                age = 25,
                gender = "Male",
            ),
            onBack = {},
        )
    }
}