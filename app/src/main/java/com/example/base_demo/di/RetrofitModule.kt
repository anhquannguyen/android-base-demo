package com.example.base_demo.di

import com.example.base_demo.BuildConfig
import com.example.base_demo.data.remote.ApiHeader
import com.example.base_demo.data.remote.ApiServices
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    fun retrofit(okHttpClient: OkHttpClient, factory: GsonConverterFactory): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(factory)
            .build()

    @Provides
    fun okHttpClient(
        apiInterceptor: Interceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(apiInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    fun factory(): GsonConverterFactory {
        val builder = Gson()
        return GsonConverterFactory.create(builder)
    }

    @Provides
    fun apiInterceptor(headers: ApiHeader): Interceptor = Interceptor { chain ->
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder().headers(headers.build())
            .method(originalRequest.method, originalRequest.body)
            .build()
        chain.proceed(newRequest)
    }

    @Provides
    fun apiService(retrofit: Retrofit): ApiServices = retrofit.create(ApiServices::class.java)

}