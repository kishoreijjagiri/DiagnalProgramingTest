package com.example.diagnalprogramingtest.di

import android.content.Context
import com.example.diagnalprogramingtest.common.LoginInterceptor
import com.example.diagnalprogramingtest.data.network.RetrofitApi
import com.example.diagnalprogramingtest.domain.Repository
import com.example.diagnalprogramingtest.data.RepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton
    @Provides
    fun provideOKHttp(interceptor: LoginInterceptor): OkHttpClient =
        OkHttpClient().newBuilder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(interceptor)
            .retryOnConnectionFailure(true).build()


    @Provides
    fun provideInterceptore(@ApplicationContext context: Context): LoginInterceptor =
        LoginInterceptor(context)


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(RetrofitApi::class.java)

    @Provides
    fun provideRepository(api: RetrofitApi): Repository = RepositoryImp(api)

    const val BASE_URL = "https://github.com/"
}


