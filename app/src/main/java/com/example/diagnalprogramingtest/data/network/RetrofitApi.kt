package com.example.diagnalprogramingtest.data.network



import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitApi {

    @GET("CONTENTLISTINGPAGE-PAGE{pageNumber}.json")
    suspend fun getData(@Path("pageNumber")  pageNumber:Int): ResponseBody
}