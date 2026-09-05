package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.api.user

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kh.com.pheaktra.developer.basic.advance.android.weekend.R
import kh.com.pheaktra.developer.model.general.MaterialComponentModel
import kh.com.pheaktra.developer.model.response.UserModelResponse
import kh.com.pheaktra.developer.basic.advance.android.weekend.util.LoadingUtil
import kh.com.pheaktra.developer.model.BaseUiState

@Composable
fun ScreenUserApi(
    item: MaterialComponentModel,
    onBack: () -> Unit = {},
    onClickUser: (Int) -> Unit = {},
    onCreateUser: () -> Unit = {},
    userApiVM: UserApiVM = viewModel()
) {
    val context = LocalContext.current

    val userListUiState by userApiVM.userListUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        if (userListUiState is BaseUiState.Success) {
            return@LaunchedEffect
        }
        userApiVM.getUserList()
    }

    LaunchedEffect(key1 = userListUiState) {
        when (val state = userListUiState) {
            is BaseUiState.Loading -> LoadingUtil.showLoading()

            is BaseUiState.Success -> {
                LoadingUtil.hideLoading()
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
    Scaffold(
        modifier = Modifier.navigationBarsPadding(),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = {
                            userApiVM.onDispose()
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
                    Row {
                        Column {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = item.title,
                                textAlign = TextAlign.Start,
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold,
                            )

                        }
                    }
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onCreateUser,
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_add),
                        contentDescription = "Add user",
                        modifier = Modifier.size(24.dp)
                    )
                },
                text = {
                    Text(
                        text = "New User",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                    )
                },
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            when (val state = userListUiState) {
                is BaseUiState.Success -> {
                    items(
                        count = state.data.size,
                        key = {
                            state.data[it].id
                        }
                    ) { index ->
                        UserCard(
                            user = state.data[index],
                            modifier = Modifier
                                .padding(
                                    horizontal = 16.dp,
                                    vertical = 8.dp
                                )
                                .clickable(
                                    onClick = {
                                        onClickUser(state.data[index].id)
                                    }
                                )
                        )
                    }
                }

                is BaseUiState.Failure -> {
                    item {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = state.message,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                    }
                }

                is BaseUiState.Exception -> {
                    item {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            state.message?.let {
                                Text(
                                    text = it,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }

                    }
                }

                else -> {}
            }
        }
    }
}

@Composable
fun UserCard(
    user: UserModelResponse,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            // Header
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                // Fake profile avatar
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(
                            MaterialTheme.colorScheme.primaryContainer
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = user.firstName
                            .firstOrNull()
                            ?.uppercase()
                            ?: "U",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }

                Spacer(
                    modifier = Modifier.width(12.dp)
                )

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "${user.firstName} ${user.lastName}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "@${user.username}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Text(
                    text = "#${user.id}",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            HorizontalDivider(
                modifier = Modifier.padding(
                    vertical = 16.dp
                )
            )

            UserInfoRow(
                label = "Email",
                value = user.email
            )

            UserInfoRow(
                label = "Age",
                value = user.age.toString()
            )

            UserInfoRow(
                label = "Gender",
                value = user.gender
            )
        }
    }
}

@Composable
private fun UserInfoRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
    }
}