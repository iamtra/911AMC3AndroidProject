package kh.com.pheaktra.developer.domain.repository

import kh.com.pheaktra.developer.model.request.CreateUserRequest
import kh.com.pheaktra.developer.model.request.UpdateUserRequest
import kh.com.pheaktra.developer.model.response.BaseResponse
import kh.com.pheaktra.developer.model.response.UserModelResponse

interface UserRepository {
    suspend fun getUserList(): BaseResponse<List<UserModelResponse>>

    suspend fun getUserDetail(id: Int): BaseResponse<UserModelResponse>

    suspend fun createUser(user: CreateUserRequest): BaseResponse<UserModelResponse>

    suspend fun updateUser(id: Int, user: UpdateUserRequest): BaseResponse<UserModelResponse>

    suspend fun deleteUser(id: Int): BaseResponse<UserModelResponse>
}