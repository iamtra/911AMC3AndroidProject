package kh.com.pheaktra.developer.model.request

import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserRequest(
    val username: String,
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val gender: String
)
