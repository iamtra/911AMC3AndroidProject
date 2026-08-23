package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.api

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kh.com.pheaktra.developer.basic.advance.android.weekend.data.network.apiService
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.response.UserModelResponse
import kh.com.pheaktra.developer.basic.advance.android.weekend.util.BaseUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


@Stable
class UserApiVM : ViewModel() {
    private var _userListUiState =
        MutableStateFlow<BaseUiState<List<UserModelResponse>>>(BaseUiState.None)
    val userListUiState = _userListUiState.asStateFlow()

    fun getUserList() {
        viewModelScope.launch {
            println("=====> getUserList")
            try {
                val response = apiService.getUserList()
                println("=====> API SUCCESS: Received ${response.size} users")
                println("=====> response: $response")
            } catch (e: kotlinx.coroutines.CancellationException) {
                throw e
            } catch (e: java.net.SocketTimeoutException) {
                println("=====> CONNECTION TIMEOUT: Could not reach 10.0.2.2:3500. Check server binding and firewall.")
                e.printStackTrace()
            } catch (e: Exception) {
                println("=====> API ERROR: ${e.message}")
                e.printStackTrace()
            }
        }
    }

    init {
        getUserList()
    }
}