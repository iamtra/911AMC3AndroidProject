package kh.com.pheaktra.developer.model.response

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserResponse(
    val username: String,
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val gender: String,
    val createdAt: String,
    val updatedAt: String,
)
