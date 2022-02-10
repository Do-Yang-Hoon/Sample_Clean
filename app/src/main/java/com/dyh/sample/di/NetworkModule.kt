package com.dyh.sample.di

import com.dyh.data.ApiService
import com.dyh.data.PostsRepositoryImp
import com.dyh.domain.repository.PostsRepository
import com.dyh.domain.usecase.GetPostsUseCase
import com.dyh.sample.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 30L

val NetworkModule = module {

    single { createService(get()) }

    single {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        val networkClient = OkHttpClient.Builder().apply {
            connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            readTimeout(TIME_OUT, TimeUnit.SECONDS)
            addInterceptor(httpLoggingInterceptor)
            retryOnConnectionFailure(true)
        }.build()

        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(networkClient)
            .build()
    }


}


fun createService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}

fun createPostRepository(apiService: ApiService): PostsRepository {
    return PostsRepositoryImp(apiService)
}

fun createGetPostsUseCase(postsRepository: PostsRepository): GetPostsUseCase {
    return GetPostsUseCase(postsRepository)
}
