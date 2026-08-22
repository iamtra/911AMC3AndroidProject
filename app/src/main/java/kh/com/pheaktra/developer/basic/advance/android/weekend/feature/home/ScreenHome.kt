package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import kh.com.pheaktra.developer.basic.advance.android.weekend.R
import kh.com.pheaktra.developer.basic.advance.android.weekend.data.storage.componentList
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.general.MaterialComponentModel
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme
import kh.com.pheaktra.developer.basic.advance.android.weekend.util.BaseUiState
import kh.com.pheaktra.developer.basic.advance.android.weekend.util.LoadingUtil
import kh.com.pheaktra.developer.basic.advance.android.weekend.util.SystemBarController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenHome(
    onClick: (item: MaterialComponentModel) -> Unit,
    onClickNotification: (String) -> Unit,
    homeVM: HomeVM = viewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    val stateUiState by homeVM.stateUiState.collectAsStateWithLifecycle()

    var triggerState by remember { mutableStateOf(false) }

//    LaunchedEffect(Unit) {
//        homeVM.fetchComponentList()
//    }

    LaunchedEffect(stateUiState) {
        when (val state = stateUiState) {
            is BaseUiState.Loading -> {
                LoadingUtil.showLoading()
            }

            is BaseUiState.Success -> {
                LoadingUtil.hideLoading()
                println("====> Data: ${state.data}")
            }

            is BaseUiState.Failure -> {
                println("====> Code: ${state.code}")
                println("====> Message: ${state.message}")
                LoadingUtil.hideLoading()
            }

            is BaseUiState.Exception -> {
                println("====> Throwable: ${state.throwable}")
                LoadingUtil.hideLoading()
            }

            is BaseUiState.Empty -> {
                LoadingUtil.hideLoading()
            }

            else -> {}
        }
    }

    LaunchedEffect(Unit) {
        println("=====> LaunchedEffect")
    }

    LaunchedEffect(key1 = triggerState) {
        println("=====> LaunchedEffect $triggerState")
    }

    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        println("=====> LifecycleEventEffect ON_START")
    }

    LifecycleEventEffect(Lifecycle.Event.ON_STOP) {
        println("=====> LifecycleEventEffect ON_STOP")
    }

    LifecycleEventEffect(Lifecycle.Event.ON_PAUSE) {
        println("=====> LifecycleEventEffect ON_PAUSE")
    }

    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        println("=====> LifecycleEventEffect ON_RESUME")
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_DESTROY) {
                println("=====> ON_DESTROY")
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    data class Account(
        val accountNo: String,

        )

//    DisposableEffect(lifecycleOwner) {
//        val observer = LifecycleEventObserver { _, event ->
//            when (event) {
//                Lifecycle.Event.ON_CREATE -> {
//                    println("=====> ON_CREATE")
//                }
//
//                Lifecycle.Event.ON_START -> {
//                    println("=====> ON_START")
//                }
//
//                Lifecycle.Event.ON_RESUME -> {
//                    println("=====> ON_RESUME")
//                }
//
//                Lifecycle.Event.ON_PAUSE -> {
//                    println("=====> ON_PAUSE")
//                }
//
//                Lifecycle.Event.ON_STOP -> {
//                    println("=====> ON_STOP")
//                }
//
//                Lifecycle.Event.ON_DESTROY -> {
//                    println("=====> ON_DESTROY")
//                }
//
//                Lifecycle.Event.ON_ANY -> Unit
//            }
//        }
//
//        lifecycleOwner.lifecycle.addObserver(observer)
//
//        onDispose {
//            lifecycleOwner.lifecycle.removeObserver(observer)
//        }
//    }

    SystemBarController(
        useDarkStatusBarIcons = false,
        useDarkNavigationBarIcons = false
    )
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
                actions = {
                    IconButton(
                        onClick = {
                            onClickNotification("Notification")
                        }
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(R.drawable.ic_notification_on),
                            contentDescription = "Light",
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = Color.Yellow,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    triggerState = !triggerState
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_share),
                    contentDescription = "Share",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    ) { paddingValues ->
        when (stateUiState) {
            is BaseUiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .padding(paddingValues)
                ) {
                    items(componentList) { item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable(
                                    onClick = {
                                        onClick(item)
                                    }
                                )
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = item.icon,
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(start = 16.dp)
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
                        }
                        HorizontalDivider(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            thickness = 0.5.dp,
                            color = MaterialTheme.colorScheme.outlineVariant
                        )
                    }
                }
            }

            else -> {

            }
        }
    }
}

@Composable
@Preview
fun ScreenHomePreview() {
    AppTheme {
        ScreenHome(
            onClick = {},
            onClickNotification = {}
        )
    }
}
