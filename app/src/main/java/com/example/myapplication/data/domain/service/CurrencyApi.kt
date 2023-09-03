package com.example.myapplication.data.domain.service

import com.example.myapplication.data.domain.model.Latest
import com.example.myapplication.util.ApiConst.LATEST_ENDPOINT
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CurrencyApi
{
    @GET(LATEST_ENDPOINT)
    suspend fun getLatest(@Query("access_key")accessKey: String): Latest
    @GET("api/{date}")
    suspend fun getHistoricalDay(@Path("date")date:String, @Query("access_key")accessKey: String):
            Latest

}