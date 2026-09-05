package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.api.userdetail

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kh.com.pheaktra.developer.model.BaseUiState
import kh.com.pheaktra.developer.model.response.UserModelResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


import dagger.hilt.android.lifecycle.HiltViewModel
import kh.com.pheaktra.developer.domain.usecase.DeleteUserUseCase
import kh.com.pheaktra.developer.domain.usecase.GetUserDetailUseCase
import javax.inject.Inject


@Stable
@HiltViewModel
class UserDetailApiVM @Inject constructor(
    private val getUserDetailUseCase: GetUserDetailUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
) : ViewModel() {

    private var _userDetailUiState =
        MutableStateFlow<BaseUiState<UserModelResponse>>(BaseUiState.None)
    val userDetailUiState = _userDetailUiState.asStateFlow()

    private var _deleteUserUiState =
        MutableStateFlow<BaseUiState<UserModelResponse>>(BaseUiState.None)
    val deleteUserUiState = _deleteUserUiState.asStateFlow()

    fun getUserDetail(id: Int) {
        _userDetailUiState.value = BaseUiState.Loading
        viewModelScope.launch {
            getUserDetailUseCase.invoke(id).collect {
                _userDetailUiState.emit(it)
            }
        }
    }

    fun onDeleteUser(id: Int) {
        _deleteUserUiState.value = BaseUiState.Loading
        viewModelScope.launch {
            deleteUserUseCase.invoke(id).collect {
                _deleteUserUiState.emit(it)
            }
        }
    }

    fun onDispose() {
        _userDetailUiState.value = BaseUiState.None
        _deleteUserUiState.value = BaseUiState.None
    }
}