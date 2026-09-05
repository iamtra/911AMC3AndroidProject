package kh.com.pheaktra.developer.domain.usecase

import kh.com.pheaktra.developer.domain.BaseUseCase
import kh.com.pheaktra.developer.domain.repository.UserRepository
import kh.com.pheaktra.developer.model.BaseUiState
import kh.com.pheaktra.developer.model.request.UpdateUserRequest
import kh.com.pheaktra.developer.model.response.UserModelResponse
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.SocketTimeoutException
import javax.inject.Inject

data class UpdateUserParams(
    val id: Int,
    val request: UpdateUserRequest
)

class UpdateUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<UpdateUserParams, Flow<BaseUiState<UserModelResponse>>>() {
    override suspend fun execute(params: UpdateUserParams): Flow<BaseUiState<UserModelResponse>> {
        return flow {
            try {
                val response = userRepository.updateUser(params.id, params.request)
                if (response.result) {
                    emit(BaseUiState.Success(response.data))
                } else {
                    emit(BaseUiState.Failure(code = "API ERROR", message = response.message))
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: SocketTimeoutException) {
                emit(
                    BaseUiState.Exception(
                        code = "CONNECTION TIMEOUT",
                        message = "Could not reach server. Check your connection.",
                        throwable = e
                    )
                )
            } catch (e: Exception) {
                emit(
                    BaseUiState.Exception(
                        code = "API ERROR",
                        message = e.message,
                        throwable = e
                    )
                )
            }
        }
    }
}
