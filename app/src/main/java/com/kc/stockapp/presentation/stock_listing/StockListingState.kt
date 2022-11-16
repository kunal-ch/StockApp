package com.kc.stockapp.presentation.stock_listing

import com.kc.stockapp.domain.models.StockData

data class StockListingState(
    val stocks: List<StockData> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
