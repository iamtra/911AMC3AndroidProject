package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.stateviewmodel

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.UserModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@Stable
class StateVM() : ViewModel() {
    private var _userListUiState = MutableStateFlow<MutableList<UserModel>>(mutableListOf())
    val userListUiState = _userListUiState.asStateFlow()

    fun save(user: UserModel) {
        val list = _userListUiState.value + mutableListOf(user)
        _userListUiState.value = list.toMutableList()
    }
    fun delete(user: UserModel) {
        _userListUiState.value = _userListUiState.value.filter { it != user }.toMutableList()
    }
}