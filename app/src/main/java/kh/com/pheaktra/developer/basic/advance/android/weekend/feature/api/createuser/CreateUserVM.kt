package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.api.createuser

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kh.com.pheaktra.developer.basic.advance.android.weekend.data.network.apiService
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.request.CreateUserRequest
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.request.UpdateUserRequest
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.response.UserModelResponse
import kh.com.pheaktra.developer.basic.advance.android.weekend.util.BaseUiState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException


@Stable
class CreateUserVM : ViewModel() {

    private var _createUserUiState =
        MutableStateFlow<BaseUiState<UserModelResponse>>(BaseUiState.None)
    val createUserUiState = _createUserUiState.asStateFlow()

    fun createUser(body: CreateUserRequest) {
        _createUserUiState.value = BaseUiState.Loading
        viewModelScope.launch {
            try {
                val response = apiService.createUser(body)
                _createUserUiState.emit(BaseUiState.Success(response.data))
            } catch (e: CancellationException) {
                throw e
            } catch (e: SocketTimeoutException) {
                _createUserUiState.emit(
                    BaseUiState.Exception(
                        code = "CONNECTION TIMEOUT",
                        message = "Could not reach 10.0.2.2:3500. Check server binding and firewall.",
                        throwable = e
                    )
                )
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
                _createUserUiState.emit(
                    BaseUiState.Exception(
                        code = "API ERROR",
                        message = e.message,
                        throwable = e
                    )
                )
            }
        }
    }

    fun updateUser(id: Int, body: UpdateUserRequest) {
        _createUserUiState.value = BaseUiState.Loading
        viewModelScope.launch {
            try {
                val response = apiService.updateUser(id, body)
                _createUserUiState.emit(BaseUiState.Success(response.data))
            } catch (e: CancellationException) {
                throw e
            } catch (e: SocketTimeoutException) {
                _createUserUiState.emit(
                    BaseUiState.Exception(
                        code = "CONNECTION TIMEOUT",
                        message = "Could not reach 10.0.2.2:3500. Check server binding and firewall.",
                        throwable = e
                    )
                )
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
                _createUserUiState.emit(
                    BaseUiState.Exception(
                        code = "API ERROR",
                        message = e.message,
                        throwable = e
                    )
                )
            }
        }
    }

    fun onDispose() {
        _createUserUiState.value = BaseUiState.None
    }
}