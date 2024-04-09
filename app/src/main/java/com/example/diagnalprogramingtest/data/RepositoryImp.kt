package com.example.diagnalprogramingtest.data

import android.util.Log
import com.example.diagnalprogramingtest.data.dto.Page
import com.example.diagnalprogramingtest.data.network.RetrofitApi
import com.example.diagnalprogramingtest.domain.Repository
import com.google.gson.Gson
import org.json.JSONObject
import javax.inject.Inject

class RepositoryImp @Inject constructor (val retrofitApi: RetrofitApi) : Repository {
    override suspend fun getData(pageNumber:Int): Page {


        val jsonString = retrofitApi.getData(pageNumber).string()
        Log.v("kishore",jsonString)
        return Gson().fromJson(JSONObject(jsonString).optJSONObject("page")?.toString(), Page::class.java)

    }

}