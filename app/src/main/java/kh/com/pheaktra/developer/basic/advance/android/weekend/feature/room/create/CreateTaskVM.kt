package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.room.create

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kh.com.pheaktra.developer.domain.usecase.CreateTaskUseCase
import kh.com.pheaktra.developer.domain.usecase.UpdateTaskUseCase
import kh.com.pheaktra.developer.model.BaseUiState
import kh.com.pheaktra.developer.model.request.TaskModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@Stable
@HiltViewModel
class CreateTaskVM @Inject constructor(
    private val createTaskUseCase: CreateTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase
) : ViewModel() {

    private val _operationState = MutableStateFlow<BaseUiState<Unit>>(BaseUiState.None)
    val operationState = _operationState.asStateFlow()

    fun createTask(task: TaskModel) {
        viewModelScope.launch {
            createTaskUseCase(task).collectLatest { state ->
                _operationState.value = state
            }
        }
    }

    fun updateTask(task: TaskModel) {
        viewModelScope.launch {
            updateTaskUseCase(task).collectLatest { state ->
                _operationState.value = state
            }
        }
    }

    fun resetState() {
        _operationState.value = BaseUiState.None
    }
}
