package kh.com.pheaktra.developer.basic.advance.android.weekend.data.network

import kh.com.pheaktra.developer.basic.advance.android.weekend.model.response.UserModelResponse
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUserList() : List<UserModelResponse>
}