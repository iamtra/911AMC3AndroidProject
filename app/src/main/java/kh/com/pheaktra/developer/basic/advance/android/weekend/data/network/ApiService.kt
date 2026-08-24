package kh.com.pheaktra.developer.basic.advance.android.weekend.data.network

import kh.com.pheaktra.developer.basic.advance.android.weekend.model.response.BaseResponse
import kh.com.pheaktra.developer.basic.advance.android.weekend.model.response.UserModelResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users")
    suspend fun getUserList() : BaseResponse<List<UserModelResponse>>

    @GET("users/{id}")
    suspend fun getUserDetail( @Path("id") id: Int) : BaseResponse<UserModelResponse>
}