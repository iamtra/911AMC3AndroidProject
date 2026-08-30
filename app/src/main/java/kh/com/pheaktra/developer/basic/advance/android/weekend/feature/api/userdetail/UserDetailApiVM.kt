package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.api.userdetail

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kh.com.pheaktra.developer.basic.advance.android.weekend.data.network.apiService
import kh.com.pheaktra.developer.model.response.UserModelResponse
import kh.com.pheaktra.developer.basic.advance.android.weekend.util.BaseUiState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException


@Stable
class UserDetailApiVM : ViewModel() {

    private var _userDetailUiState =
        MutableStateFlow<BaseUiState<UserModelResponse>>(BaseUiState.None)
    val userDetailUiState = _userDetailUiState.asStateFlow()

    private var _deleteUserUiState =
        MutableStateFlow<BaseUiState<UserModelResponse>>(BaseUiState.None)
    val deleteUserUiState = _deleteUserUiState.asStateFlow()

    fun getUserDetail(id: Int) {
        _userDetailUiState.value = BaseUiState.Loading
        viewModelScope.launch {
            try {
                val response = apiService.getUserDetail(id)
                _userDetailUiState.emit(BaseUiState.Success(response.data))
            } catch (e: CancellationException) {
                throw e
            } catch (e: SocketTimeoutException) {
                _userDetailUiState.emit(
                    BaseUiState.Exception(
                        code = "CONNECTION TIMEOUT",
                        message = "Could not reach 10.0.2.2:3500. Check server binding and firewall.",
                        throwable = e
                    )
                )
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
                _userDetailUiState.emit(
                    BaseUiState.Exception(
                        code = "API ERROR",
                        message = e.message,
                        throwable = e
                    )
                )
            }
        }
    }

    fun onDeleteUser(id: Int) {
        _deleteUserUiState.value = BaseUiState.Loading
        viewModelScope.launch {
            try {
                val response = apiService.deleteUser(id)
                _deleteUserUiState.emit(BaseUiState.Success(response.data))
            } catch (e: CancellationException) {
                throw e
            } catch (e: SocketTimeoutException) {
                _deleteUserUiState.emit(
                    BaseUiState.Exception(
                        code = "CONNECTION TIMEOUT",
                        message = "Could not reach 10.0.2.2:3500. Check server binding and firewall.",
                        throwable = e
                    )
                )
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
                _deleteUserUiState.emit(
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
        _userDetailUiState.value = BaseUiState.None
        _deleteUserUiState.value = BaseUiState.None
    }
}