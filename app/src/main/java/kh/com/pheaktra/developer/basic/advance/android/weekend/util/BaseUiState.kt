package kh.com.pheaktra.developer.basic.advance.android.weekend.util

sealed interface BaseUiState<out T> {

    data object None : BaseUiState<Nothing>

    data object Loading : BaseUiState<Nothing>

    data class Success<T>(
        val data: T
    ) : BaseUiState<T>

    data class Failure(
        val code: String? = null,
        val message: String
    ) : BaseUiState<Nothing>

    data class Exception(
        val throwable: Throwable
    ) : BaseUiState<Nothing>

    data object Empty : BaseUiState<Nothing>
}