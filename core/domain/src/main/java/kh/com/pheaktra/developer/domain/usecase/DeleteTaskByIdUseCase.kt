package kh.com.pheaktra.developer.domain.usecase

import kh.com.pheaktra.developer.domain.BaseUseCase
import kh.com.pheaktra.developer.domain.repository.TaskRepository
import kh.com.pheaktra.developer.model.BaseUiState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteTaskByIdUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) : BaseUseCase<Int, Flow<BaseUiState<Unit>>>() {
    override suspend fun execute(params: Int): Flow<BaseUiState<Unit>> {
        return flow {
            emit(BaseUiState.Loading)
            try {
                taskRepository.deleteTaskById(params)
                emit(BaseUiState.Success(Unit))
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                emit(BaseUiState.Exception(message = e.message, throwable = e))
            }
        }
    }
}
