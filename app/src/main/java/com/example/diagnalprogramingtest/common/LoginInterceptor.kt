package com.example.diagnalprogramingtest.common

import android.content.Context
import com.example.diagnalprogramingtest.di.Module.BASE_URL
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import javax.inject.Inject

class LoginInterceptor @Inject constructor(var context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val fileName = chain.request().url.toString().replace(BASE_URL, "")
        val responseString = context.readJsonFromAssets(fileName)
        try {

            return chain.proceed(chain.request()).newBuilder()
                .code(200)
                .request(chain.request())
                .protocol(Protocol.HTTP_2)
                .body(
                    responseString
                        .toByteArray()
                        .toResponseBody(
                            "application/json".toMediaTypeOrNull()
                        )
                )
                .addHeader("content-type", "application/json")
                .build()

        } catch (e: Exception) {
            e.printStackTrace()
            return chain.proceed(chain.request())
        }

    }
}
