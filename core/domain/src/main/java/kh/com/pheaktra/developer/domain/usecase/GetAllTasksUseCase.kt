package kh.com.pheaktra.developer.domain.usecase

import kh.com.pheaktra.developer.domain.BaseNoneUseCase
import kh.com.pheaktra.developer.domain.repository.TaskRepository
import kh.com.pheaktra.developer.model.BaseUiState
import kh.com.pheaktra.developer.model.request.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GetAllTasksUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) : BaseNoneUseCase<Unit, Flow<BaseUiState<List<TaskModel>>>>() {
    override fun execute(params: Unit): Flow<BaseUiState<List<TaskModel>>> {
        return taskRepository.getAllTasks()
            .map { BaseUiState.Success(it) as BaseUiState<List<TaskModel>> }
            .onStart { emit(BaseUiState.Loading) }
            .catch { emit(BaseUiState.Exception(message = it.message, throwable = it)) }
    }
}
