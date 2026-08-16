package kh.com.pheaktra.developer.basic.advance.android.weekend.data.respository

import kh.com.pheaktra.developer.basic.advance.android.weekend.data.storage.componentList
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.MaterialComponentModel
import kh.com.pheaktra.developer.basic.advance.android.weekend.util.BaseUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration.Companion.milliseconds

class HomeRepository {

    fun getHomeData(): Flow<BaseUiState<List<MaterialComponentModel>>> {
        return flow {
            emit(BaseUiState.Loading)
            delay(1000.milliseconds)
            emit(BaseUiState.Success(componentList))
        }
    }
}