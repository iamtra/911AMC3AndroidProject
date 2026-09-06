package kh.com.pheaktra.developer.domain.usecase

import kh.com.pheaktra.developer.domain.BaseUseCase
import kh.com.pheaktra.developer.domain.repository.TaskRepository
import kh.com.pheaktra.developer.model.BaseUiState
import kh.com.pheaktra.developer.model.request.TaskModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetTaskByIdUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) : BaseUseCase<Int, Flow<BaseUiState<TaskModel?>>>() {
    override suspend fun execute(params: Int): Flow<BaseUiState<TaskModel?>> {
        return flow {
            emit(BaseUiState.Loading)
            try {
                val task = taskRepository.getTaskById(params)
                emit(BaseUiState.Success(task))
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                emit(BaseUiState.Exception(message = e.message, throwable = e))
            }
        }
    }
}
