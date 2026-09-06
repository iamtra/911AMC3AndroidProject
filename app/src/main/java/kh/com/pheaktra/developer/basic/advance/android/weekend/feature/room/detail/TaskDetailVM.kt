package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.room.detail

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kh.com.pheaktra.developer.domain.usecase.DeleteTaskByIdUseCase
import kh.com.pheaktra.developer.domain.usecase.GetTaskByIdUseCase
import kh.com.pheaktra.developer.model.BaseUiState
import kh.com.pheaktra.developer.model.request.TaskModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@Stable
@HiltViewModel
class TaskDetailVM @Inject constructor(
    private val getTaskByIdUseCase: GetTaskByIdUseCase,
    private val deleteTaskByIdUseCase: DeleteTaskByIdUseCase
) : ViewModel() {

    private val _taskState = MutableStateFlow<BaseUiState<TaskModel?>>(BaseUiState.None)
    val taskState = _taskState.asStateFlow()

    private val _deleteState = MutableStateFlow<BaseUiState<Unit>>(BaseUiState.None)
    val deleteState = _deleteState.asStateFlow()

    fun getTask(taskId: Int) {
        viewModelScope.launch {
            getTaskByIdUseCase(taskId).collectLatest { state ->
                _taskState.value = state
            }
        }
    }

    fun deleteTask(taskId: Int) {
        viewModelScope.launch {
            deleteTaskByIdUseCase(taskId).collectLatest { state ->
                _deleteState.value = state
            }
        }
    }
}
