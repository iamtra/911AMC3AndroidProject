package kh.com.pheaktra.developer.basic.advance.android.weekend.feature.home

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kh.com.pheaktra.developer.basic.advance.android.weekend.data.respository.HomeRepository
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.general.MaterialComponentModel
import kh.com.pheaktra.developer.basic.advance.android.weekend.util.BaseUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@Stable
class HomeVM(
    private val homeRepository: HomeRepository = HomeRepository()
) : ViewModel() {
    private val _stateUiState = MutableStateFlow<BaseUiState<List<MaterialComponentModel>>>(BaseUiState.None)
    val stateUiState = _stateUiState.asStateFlow()

//    fun fetchComponentList() {
//        viewModelScope.launch {
//            _stateUiState.emit(BaseUiState.Loading)
//            delay(1000.milliseconds)
//            _stateUiState.emit(BaseUiState.Success(componentList))
////            _stateUiState.emit(BaseUiState.Failure(code = "404", message = "Not Found"))
////            _stateUiState.emit(BaseUiState.Exception(throwable = Exception("Exception")))
//        }
//    }

    init {
        getHomeData()
    }

    fun getHomeData() {
        viewModelScope.launch {
            homeRepository.getHomeData().collect { componentListUiState ->
                println("=====>Test $componentListUiState")
                _stateUiState.emit(componentListUiState)
            }
        }
    }
}