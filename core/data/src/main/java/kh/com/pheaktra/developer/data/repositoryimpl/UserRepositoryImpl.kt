package kh.com.pheaktra.developer.data.repositoryimpl

import kh.com.pheaktra.developer.domain.repository.UserRepository
import kh.com.pheaktra.developer.model.request.CreateUserRequest
import kh.com.pheaktra.developer.model.request.UpdateUserRequest
import kh.com.pheaktra.developer.model.response.BaseResponse
import kh.com.pheaktra.developer.model.response.UserModelResponse
import kh.com.pheaktra.developer.network.ApiService
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserRepository {
    override suspend fun getUserList(): BaseResponse<List<UserModelResponse>> {
        return apiService.getUserList()
    }

    override suspend fun getUserDetail(id: Int): BaseResponse<UserModelResponse> {
        return apiService.getUserDetail(id)
    }

    override suspend fun createUser(user: CreateUserRequest): BaseResponse<UserModelResponse> {
        return apiService.createUser(user)
    }

    override suspend fun updateUser(
        id: Int,
        user: UpdateUserRequest
    ): BaseResponse<UserModelResponse> {
        return apiService.updateUser(id, user)
    }

    override suspend fun deleteUser(id: Int): BaseResponse<UserModelResponse> {
        return apiService.deleteUser(id)
    }
}