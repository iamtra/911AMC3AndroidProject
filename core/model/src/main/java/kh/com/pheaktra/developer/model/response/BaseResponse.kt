package kh.com.pheaktra.developer.model.response

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val message: String,
    val result: Boolean,
    val data: T
)
