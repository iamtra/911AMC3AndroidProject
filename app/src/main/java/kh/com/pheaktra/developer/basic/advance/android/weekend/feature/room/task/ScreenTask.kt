package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.room.task

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kh.com.pheaktra.developer.basic.advance.android.weekend.R
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme
import kh.com.pheaktra.developer.model.BaseUiState
import kh.com.pheaktra.developer.model.request.TaskModel

@Composable
fun ScreenTask(
    title: String,
    onBack: () -> Unit,
    onDetail: (TaskModel) -> Unit,
    onCreate: () -> Unit,
    viewModel: TaskVM = hiltViewModel()
) {
    val tasksState by viewModel.tasksState.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.onDispose()
        }
    }
    ScreenTaskContent(
        title = title,
        searchQuery = searchQuery,
        onSearchQueryChange = {
            searchQuery = it
            viewModel.searchTasks(it)
        },
        tasksState = tasksState,
        onBack = onBack,
        onDetail = onDetail,
        onCreate = onCreate,
        onDeleteTask = { viewModel.deleteTask(it.taskId) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTaskContent(
    title: String,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    tasksState: BaseUiState<List<TaskModel>>,
    onBack: () -> Unit,
    onDetail: (TaskModel) -> Unit,
    onCreate: () -> Unit,
    onDeleteTask: (TaskModel) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_arrow_back),
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onCreate) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Task")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = { Text("Search tasks...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) }
            )

            when (val state = tasksState) {
                is BaseUiState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                is BaseUiState.Success -> {
                    TaskList(
                        tasks = state.data,
                        onTaskClick = onDetail,
                        onDeleteClick = onDeleteTask
                    )
                }

                is BaseUiState.Exception -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Error: ${state.message}", color = MaterialTheme.colorScheme.error)
                    }
                }

                is BaseUiState.Empty -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "No tasks found")
                    }
                }

                else -> {}
            }
        }
    }
}

@Composable
fun TaskList(
    tasks: List<TaskModel>,
    onTaskClick: (TaskModel) -> Unit,
    onDeleteClick: (TaskModel) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(tasks) { task ->
            TaskItem(task = task, onClick = { onTaskClick(task) }, onDelete = { onDeleteClick(task) })
        }
    }
}

@Composable
fun TaskItem(
    task: TaskModel,
    onClick: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = task.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1
                )
                Text(
                    text = if (task.completedYN) "Completed" else "Pending",
                    style = MaterialTheme.typography.labelSmall,
                    color = if (task.completedYN) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                )
            }
            IconButton(onClick = onDelete) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = MaterialTheme.colorScheme.error)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenTaskPreview() {
    AppTheme {
        ScreenTaskContent(
            title = "Task List",
            searchQuery = "",
            onSearchQueryChange = {},
            tasksState = BaseUiState.Success(
                listOf(
                    TaskModel(1, "Task 1", "Description 1", true),
                    TaskModel(2, "Task 2", "Description 2", false)
                )
            ),
            onBack = {},
            onDetail = {},
            onCreate = {},
            onDeleteTask = {}
        )
    }
}
