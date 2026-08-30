package kh.com.pheaktra.developer.model.request

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserRequest(
    val username: String,
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val gender: String
)

    fun CreateUserRequest.toUpdateUserRequest() = UpdateUserRequest(
    username = username,
    email = email,
    password = password,
    firstName = firstName,
    lastName = lastName,
    age = age,
    gender = gender
)
