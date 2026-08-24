package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.api.user

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kh.com.pheaktra.developer.basic.advance.android.weekend.data.network.apiService
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.response.UserModelResponse
import kh.com.pheaktra.developer.basic.advance.android.weekend.util.BaseUiState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException


@Stable
class UserApiVM : ViewModel() {
    private var _userListUiState =
        MutableStateFlow<BaseUiState<List<UserModelResponse>>>(BaseUiState.None)
    val userListUiState = _userListUiState.asStateFlow()
    fun getUserList() {
        _userListUiState.value = BaseUiState.Loading
        viewModelScope.launch {
//            println("=====> getUserList")
            try {
                val response = apiService.getUserList()
//                println("=====> API SUCCESS: Received ${response.data.size} users")
//                println("=====> response: $response")
                _userListUiState.emit(BaseUiState.Success(response.data))
            } catch (e: CancellationException) {
                throw e
            } catch (e: SocketTimeoutException) {
//                println("=====> CONNECTION TIMEOUT: Could not reach 10.0.2.2:3500. Check server binding and firewall.")
                _userListUiState.emit(
                    BaseUiState.Exception(
                        code = "CONNECTION TIMEOUT",
                        message = "Could not reach 10.0.2.2:3500. Check server binding and firewall.",
                        throwable = e
                    )
                )
                e.printStackTrace()
            } catch (e: Exception) {
//                println("=====> API ERROR: ${e.message}")
                e.printStackTrace()
                _userListUiState.emit(
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