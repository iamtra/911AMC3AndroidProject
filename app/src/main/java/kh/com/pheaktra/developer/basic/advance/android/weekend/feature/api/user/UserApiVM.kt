package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.api.user

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kh.com.pheaktra.developer.domain.usecase.GetUserListUserCase
import kh.com.pheaktra.developer.model.BaseUiState
import kh.com.pheaktra.developer.model.response.UserModelResponse
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import javax.inject.Inject


@Stable
@HiltViewModel
class UserApiVM @Inject constructor(
    private val getUserListUserCase: GetUserListUserCase
) : ViewModel() {
    private var _userListUiState =
        MutableStateFlow<BaseUiState<List<UserModelResponse>>>(BaseUiState.None)
    val userListUiState = _userListUiState.asStateFlow()
    fun getUserList() {
        _userListUiState.value = BaseUiState.Loading
        viewModelScope.launch {
            getUserListUserCase.invoke(Unit).collect { data ->
                _userListUiState.emit(data)
            }
        }
    }

    fun onDispose() {
        _userListUiState.value = BaseUiState.None
    }

    override fun onCleared() {
        super.onCleared()
        onDispose()
    }
}