package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.home

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.MaterialComponentModel
import kh.com.pheaktra.developer.basic.advance.android.weekend.util.BaseUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

@Stable
class HomeVM : ViewModel() {
    private val _stateUiState = MutableStateFlow<BaseUiState<List<MaterialComponentModel>>>(BaseUiState.None)
    val stateUiState = _stateUiState.asStateFlow()

    fun fetchComponentList() {
        viewModelScope.launch {
            _stateUiState.emit(BaseUiState.Loading)
            delay(1000.milliseconds)
            _stateUiState.emit(BaseUiState.Success(componentList))
//            _stateUiState.emit(BaseUiState.Failure(code = "404", message = "Not Found"))
//            _stateUiState.emit(BaseUiState.Exception(throwable = Exception("Exception")))
        }
    }
}