package kh.com.pheaktra.developer.domain.usecase

import kh.com.pheaktra.developer.domain.BaseNoneUseCase
import kh.com.pheaktra.developer.domain.BaseUseCase
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
            emit(BaseUiState.Loading)
            try {
                val tasks = taskRepository.getAllTasks()
                emit(BaseUiState.Success(tasks))
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                emit(BaseUiState.Exception(message = e.message, throwable = e))
            }
        }
    }
}
