package com.kc.stockapp.domain.models

data class AllStocksResponse(
    val client_id: String?,
    val data: List<StockData>?,
    val error: String?,
    val response_type: String?,
    val timestamp: Long?
)