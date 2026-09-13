package kh.com.pheaktra.developer.domain.usecase

import kh.com.pheaktra.developer.domain.BaseNoneUseCase
import kh.com.pheaktra.developer.domain.repository.TaskRepository
import kh.com.pheaktra.developer.model.BaseUiState
import kh.com.pheaktra.developer.model.request.TaskModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllTasksUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) : BaseNoneUseCase<Unit, Flow<BaseUiState<List<TaskModel>>>>() {
    override fun execute(params: Unit): Flow<BaseUiState<List<TaskModel>>> {
        return flow {
            try {
                val response = taskRepository.getAllTasks()
                response.collect {
                    emit(BaseUiState.Success(it))
                }
            } catch (e: CancellationException) {
                emit(BaseUiState.Exception(message = e.message, throwable = e))
            }
        }
    }
}
