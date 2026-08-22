package kh.com.pheaktra.developer.basic.advance.android.weekend.model.response

import kotlinx.serialization.Serializable

@Serializable
data class UserModelResponse(
    val id: Int,
    val username: String,
    val email: String,
    val password: String,
    val createdAt: String,
    val updatedAt: String,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val gender: String,
)
