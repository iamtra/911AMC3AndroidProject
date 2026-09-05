package kh.com.pheaktra.developer.network

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


object RetrofitClient {
    val networkJson = Json {
        ignoreUnknownKeys = true
    }

    val loggingInterceptor = HttpLoggingInterceptor().apply {
//    level = if (BuildConfig.DEBUG) {
//        HttpLoggingInterceptor.Level.BODY
//    } else {
//        HttpLoggingInterceptor.Level.NONE
//    }
        level = HttpLoggingInterceptor.Level.BODY
    }

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
//    .retryOnConnectionFailure(true)
        .connectTimeout(
            30,
            TimeUnit.SECONDS
        )
        .readTimeout(
            30,
            TimeUnit.SECONDS
        )
        .writeTimeout(
            30,
            TimeUnit.SECONDS
        )
        .build()

    private const val BASE_URL = "http://10.0.2.2:3500/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(
            networkJson.asConverterFactory(
                "application/json".toMediaType()
            )
        )
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}
