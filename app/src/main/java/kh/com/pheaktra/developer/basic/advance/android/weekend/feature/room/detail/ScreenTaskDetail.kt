package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.room.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
fun ScreenTaskDetail(
    taskId: String,
    onBack: () -> Unit,
    onEdit: (TaskModel) -> Unit,
    viewModel: TaskDetailVM = hiltViewModel(),
) {
    val taskState by viewModel.taskState.collectAsState()
    val deleteState by viewModel.deleteState.collectAsState()

    LaunchedEffect(taskId) {
        viewModel.getTask(taskId.toIntOrNull() ?: 0)
    }

    LaunchedEffect(deleteState) {
        if (deleteState is BaseUiState.Success) {
            onBack()
        }
    }

    ScreenTaskDetailContent(
        taskState = taskState,
        onBack = onBack,
        onEdit = onEdit,
        onDelete = {
            (taskState as? BaseUiState.Success<TaskModel?>)?.data?.taskId?.let {
                viewModel.deleteTask(it)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTaskDetailContent(
    taskState: BaseUiState<TaskModel?>,
    onBack: () -> Unit,
    onEdit: (TaskModel) -> Unit,
    onDelete: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Task Detail") },
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
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            when (val state = taskState) {
                is BaseUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                is BaseUiState.Success -> {
                    val task = state.data
                    if (task != null) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxSize()
                        ) {
                            Text(
                                text = task.title,
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = if (task.completedYN) "Status: Completed" else "Status: Pending",
                                style = MaterialTheme.typography.bodyLarge,
                                color = if (task.completedYN) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Description:",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = task.description,
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Row(modifier = Modifier.fillMaxWidth()) {
                                OutlinedButton(
                                    onClick = { onEdit(task) },
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Icon(Icons.Default.Edit, contentDescription = null)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("Edit")
                                }
                                Spacer(modifier = Modifier.width(16.dp))
                                Button(
                                    onClick = onDelete,
                                    modifier = Modifier.weight(1f),
                                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                                ) {
                                    Icon(Icons.Default.Delete, contentDescription = null)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text("Delete")
                                }
                            }
                        }
                    } else {
                        Text("Task not found", modifier = Modifier.align(Alignment.Center))
                    }
                }

                is BaseUiState.Exception -> {
                    Text(
                        text = "Error: ${state.message}",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                else -> {}
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenTaskDetailPreview() {
    AppTheme {
        ScreenTaskDetailContent(
            taskState = BaseUiState.Success(
                TaskModel(1, "Sample Task", "This is a sample task description.", false)
            ),
            onBack = {},
            onEdit = {},
            onDelete = {}
        )
    }
}
