package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.api.createuser

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kh.com.pheaktra.developer.model.BaseUiState
import kh.com.pheaktra.developer.model.request.CreateUserRequest
import kh.com.pheaktra.developer.model.request.UpdateUserRequest
import kh.com.pheaktra.developer.model.response.UserModelResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


import dagger.hilt.android.lifecycle.HiltViewModel
import kh.com.pheaktra.developer.domain.usecase.CreateUserUseCase
import kh.com.pheaktra.developer.domain.usecase.UpdateUserParams
import kh.com.pheaktra.developer.domain.usecase.UpdateUserUseCase
import javax.inject.Inject


@Stable
@HiltViewModel
class CreateUserVM @Inject constructor(
    private val createUserUseCase: CreateUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase
) : ViewModel() {

    private var _createUserUiState =
        MutableStateFlow<BaseUiState<UserModelResponse>>(BaseUiState.None)
    val createUserUiState = _createUserUiState.asStateFlow()

    fun createUser(body: CreateUserRequest) {
        _createUserUiState.value = BaseUiState.Loading
        viewModelScope.launch {
            createUserUseCase.invoke(body).collect {
                _createUserUiState.emit(it)
            }
        }
    }

    fun updateUser(id: Int, body: UpdateUserRequest) {
        _createUserUiState.value = BaseUiState.Loading
        viewModelScope.launch {
            updateUserUseCase.invoke(UpdateUserParams(id, body)).collect {
                _createUserUiState.emit(it)
            }
        }
    }

    fun onDispose() {
        _createUserUiState.value = BaseUiState.None
    }
}