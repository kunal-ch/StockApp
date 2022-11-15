package com.kc.stockapp.data.remote

import com.kc.stockapp.BuildConfig
import com.kc.stockapp.domain.models.AllStocksResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StockApi {

    @GET("v3/{api_key}")
    suspend fun getAllStocks(
        @Path("api_key") apiKey: String = BuildConfig.API_KEY,
    ) : Response<AllStocksResponse>
}