package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.room.task

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kh.com.pheaktra.developer.domain.usecase.DeleteTaskByIdUseCase
import kh.com.pheaktra.developer.domain.usecase.GetAllTasksUseCase
import kh.com.pheaktra.developer.domain.usecase.SearchTasksUseCase
import kh.com.pheaktra.developer.model.BaseUiState
import kh.com.pheaktra.developer.model.request.TaskModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@Stable
@HiltViewModel
class TaskVM @Inject constructor(
    getAllTasksUseCase: GetAllTasksUseCase,
    searchTasksUseCase: SearchTasksUseCase,
    private val deleteTaskByIdUseCase: DeleteTaskByIdUseCase,
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val tasksState: StateFlow<BaseUiState<List<TaskModel>>> =
        _searchQuery
            .flatMapLatest { query ->
                if (query.isNotEmpty()) {
                    searchTasksUseCase.invoke(query)
                } else {
                    getAllTasksUseCase.invoke(Unit)
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = BaseUiState.Loading
            )

    fun searchTasks(query: String) {
        _searchQuery.value = query
    }

    fun deleteTask(taskId: Int) {
        viewModelScope.launch {
            deleteTaskByIdUseCase(taskId).collectLatest { _ -> }
        }
    }

    fun onDispose() {
        _searchQuery.value = ""
    }
}
