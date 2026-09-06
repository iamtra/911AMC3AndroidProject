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
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@Stable
@HiltViewModel
class TaskVM @Inject constructor(
    private val getAllTasksUseCase: GetAllTasksUseCase,
    private val deleteTaskByIdUseCase: DeleteTaskByIdUseCase,
    private val searchTasksUseCase: SearchTasksUseCase,
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    private val _refreshTrigger = MutableSharedFlow<Unit>(replay = 1).apply { tryEmit(Unit) }

    @OptIn(ExperimentalCoroutinesApi::class)
    val tasksState: StateFlow<BaseUiState<List<TaskModel>>> =
        combine(_searchQuery, _refreshTrigger) { query, _ -> query }
            .flatMapLatest { query ->
                if (query.isEmpty()) {
                    getAllTasksUseCase(Unit)
                } else {
                    searchTasksUseCase(query)
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = BaseUiState.Loading,
            )

    fun searchTasks(query: String) {
        _searchQuery.value = query
    }

    fun deleteTask(taskId: Int) {
        viewModelScope.launch {
            deleteTaskByIdUseCase(taskId).collectLatest { state ->
                if (state is BaseUiState.Success) {
                    _refreshTrigger.emit(Unit)
                }
            }
        }
    }

    fun onDispose() {
        viewModelScope.launch {
            _searchQuery.value = ""
            _refreshTrigger.emit(Unit)
        }
    }
}
