package kh.com.pheaktra.developer.domain.usecase

import kh.com.pheaktra.developer.domain.BaseUseCase
import kh.com.pheaktra.developer.domain.repository.UserRepository
import kh.com.pheaktra.developer.model.BaseUiState
import kh.com.pheaktra.developer.model.response.BaseResponse
import kh.com.pheaktra.developer.model.response.UserModelResponse
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.SocketTimeoutException
import javax.inject.Inject


class GetUserListUserCase @Inject constructor(
    private val userRepository: UserRepository
) : BaseUseCase<Unit, Flow<BaseUiState<List<UserModelResponse>>>>() {
    override suspend fun execute(params: Unit): Flow<BaseUiState<List<UserModelResponse>>> {
        return flow {
            try {
                val response = userRepository.getUserList()
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
                        message = "Could not reach 10.0.2.2:3500. Check server binding and firewall.",
                        throwable = e
                    )
                )
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
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