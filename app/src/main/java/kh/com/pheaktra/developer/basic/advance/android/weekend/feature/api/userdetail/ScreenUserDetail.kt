package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.api.userdetail

import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cake
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kh.com.pheaktra.developer.basic.advance.android.weekend.R
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.request.CreateUserRequest
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.response.UserModelResponse
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.response.getFullName
import kh.com.pheaktra.developer.basic.advance.android.weekend.util.BaseUiState
import kh.com.pheaktra.developer.basic.advance.android.weekend.util.LoadingUtil

@Composable
fun ScreenUserDetail(
    userId: Int,
    onBack: () -> Unit = {},
    onEdit: (UserModelResponse) -> Unit = {},
    userDetailApiVM: UserDetailApiVM = viewModel()
) {
    val context = LocalContext.current
    val userDetailUiState by userDetailApiVM.userDetailUiState.collectAsStateWithLifecycle()
    val deleteUserUiState by userDetailApiVM.deleteUserUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        userDetailApiVM.getUserDetail(userId)
    }

    LaunchedEffect(key1 = userDetailUiState) {
        when (val state = userDetailUiState) {
            is BaseUiState.Loading -> LoadingUtil.showLoading()

            is BaseUiState.Success -> {
                LoadingUtil.hideLoading()
            }

            is BaseUiState.Failure -> {
                LoadingUtil.hideLoading()
            }

            is BaseUiState.Exception -> {
                LoadingUtil.hideLoading()
            }

            else -> {
                LoadingUtil.hideLoading()
            }
        }
    }

    LaunchedEffect(key1 = deleteUserUiState) {
        when (val state = deleteUserUiState) {
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
            userDetailApiVM.onDispose()
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
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(48.dp)
                        )
                    }
                },
                title = {
                    Row() {
                        Column() {
                            when (val state = userDetailUiState) {
                                is BaseUiState.Success -> {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = state.data.getFullName(),
                                        textAlign = TextAlign.Start,
                                        style = MaterialTheme.typography.headlineMedium,
                                        fontWeight = FontWeight.Bold,
                                    )
                                }

                                else -> {
                                    Text(
                                        modifier = Modifier.fillMaxWidth(),
                                        text = "User Detail",
                                        textAlign = TextAlign.Start,
                                        fontWeight = FontWeight.Bold
                                    )

                                }
                            }
                        }
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            val userDetail =
                                (userDetailUiState as? BaseUiState.Success<UserModelResponse>)
                            userDetail?.data?.let { user ->
                                onEdit(user)
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_edit),
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(48.dp)
                        )
                    }
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
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
        ) {
            when (val state = userDetailUiState) {
                is BaseUiState.Success -> {
                    item {
                        UserProfileHeader(state.data)
                    }
                    item {
                        UserInformationCard(state.data)
                    }
                    item {
                        Button(
                            onClick = {
                                userDetailApiVM.onDeleteUser(userId)
                            },
                            enabled = true,
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(
                                text = "Delete",
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }

                else -> {}
            }
        }
    }
}

@Composable
private fun UserProfileHeader(
    user: UserModelResponse
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(96.dp)
                .clip(CircleShape)
                .background(
                    MaterialTheme.colorScheme.primaryContainer
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "${user.firstName[0]}",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = user.getFullName(),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        Text(
            text = "@${user.username}",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Surface(
            shape = RoundedCornerShape(50),
            color = MaterialTheme.colorScheme.secondaryContainer
        ) {
            Text(
                text = "User ID: ${user.id}",
                modifier = Modifier.padding(
                    horizontal = 12.dp,
                    vertical = 6.dp
                ),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}

@Composable
private fun UserInformationCard(
    user: UserModelResponse
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Text(
                text = "Personal Information",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            UserDetailItem(
                icon = Icons.Outlined.Person,
                label = "Full Name",
                value = "${user.firstName} ${user.lastName}"
            )

            HorizontalDivider()

            UserDetailItem(
                icon = Icons.Outlined.Email,
                label = "Email",
                value = user.email
            )

            HorizontalDivider()

            UserDetailItem(
                icon = Icons.Outlined.Cake,
                label = "Age",
                value = "${user.age} years old"
            )

            HorizontalDivider()

            UserDetailItem(
                icon = Icons.Outlined.PersonOutline,
                label = "Gender",
                value = user.gender
            )
        }
    }
}

@Composable
private fun UserDetailItem(
    icon: ImageVector,
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(
                    MaterialTheme.colorScheme.primaryContainer
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(22.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(
            modifier = Modifier.width(12.dp)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(
                modifier = Modifier.height(2.dp)
            )

            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
        }
    }
}