package kh.com.pheaktra.developer.basic.advance.android.weekend.model.response

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val message: String,
    val data: T
)
