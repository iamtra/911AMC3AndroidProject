package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.room.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kh.com.pheaktra.developer.basic.advance.android.weekend.R
import kh.com.pheaktra.developer.basic.advance.android.weekend.ui.theme.AppTheme
import kh.com.pheaktra.developer.model.BaseUiState
import kh.com.pheaktra.developer.model.request.TaskModel

@Composable
fun ScreenCreateUpdateTask(
    task: TaskModel? = null,
    onBack: () -> Unit,
    onConfirm: (TaskModel) -> Unit,
    viewModel: CreateTaskVM = hiltViewModel(),
) {
    var title by remember { mutableStateOf(task?.title ?: "") }
    var description by remember { mutableStateOf(task?.description ?: "") }
    var completedYN by remember { mutableStateOf(task?.completedYN ?: false) }

    val operationState by viewModel.operationState.collectAsState()

    LaunchedEffect(operationState) {
        if (operationState is BaseUiState.Success) {
            onConfirm(
                TaskModel(
                    taskId = task?.taskId ?: 0,
                    title = title,
                    description = description,
                    completedYN = completedYN
                )
            )
            viewModel.resetState()
        }
    }

    ScreenCreateUpdateContent(
        task = task,
        title = title,
        onTitleChange = { title = it },
        description = description,
        onDescriptionChange = { description = it },
        completedYN = completedYN,
        onCompletedChange = { completedYN = it },
        operationState = operationState,
        onBack = onBack,
        onSave = {
            val taskToSave = TaskModel(
                taskId = task?.taskId ?: 0,
                title = title,
                description = description,
                completedYN = completedYN
            )
            if (task == null) {
                viewModel.createTask(taskToSave)
            } else {
                viewModel.updateTask(taskToSave)
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenCreateUpdateContent(
    task: TaskModel?,
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    completedYN: Boolean,
    onCompletedChange: (Boolean) -> Unit,
    operationState: BaseUiState<Unit>,
    onBack: () -> Unit,
    onSave: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (task == null) "Create Task" else "Update Task") },
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
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = onTitleChange,
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = description,
                onValueChange = onDescriptionChange,
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = completedYN, onCheckedChange = onCompletedChange)
                Text(text = "Completed")
            }
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = onSave,
                modifier = Modifier.fillMaxWidth(),
                enabled = title.isNotBlank() && description.isNotBlank() && (operationState !is BaseUiState.Loading)
            ) {
                Text(if (task == null) "Create" else "Update")
            }

            if (operationState is BaseUiState.Exception) {
                Text(
                    text = (operationState as BaseUiState.Exception).message ?: "Error occurred",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenCreateUpdateTaskPreview() {
    AppTheme {
        ScreenCreateUpdateContent(
            task = null,
            title = "",
            onTitleChange = {},
            description = "",
            onDescriptionChange = {},
            completedYN = false,
            onCompletedChange = {},
            operationState = BaseUiState.None,
            onBack = {},
            onSave = {}
        )
    }
}
