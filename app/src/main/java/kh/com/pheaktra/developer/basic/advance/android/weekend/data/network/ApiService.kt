package kh.com.pheaktra.developer.basic.advance.android.weekend.data.network

import kh.com.pheaktra.developer.basic.advance.android.weekend.model.request.CreateUserRequest
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.request.UpdateUserRequest
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.response.BaseResponse
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.response.UserModelResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @GET("users")
    suspend fun getUserList(): BaseResponse<List<UserModelResponse>>

    @GET("users/{id}")
    suspend fun getUserDetail(@Path("id") id: Int): BaseResponse<UserModelResponse>

    @POST("/users")
    suspend fun createUser(@Body user: CreateUserRequest): BaseResponse<UserModelResponse>

    @PUT("/users/{id}")
    suspend fun updateUser(
        @Path("id") id: Int,
        @Body user: UpdateUserRequest
    ): BaseResponse<UserModelResponse>

    @DELETE("/users/{id}")
    suspend fun deleteUser(@Path("id") id: Int): BaseResponse<UserModelResponse>
}