package com.kc.stockapp.domain.repository

import com.kc.stockapp.domain.models.StockData
import com.kc.stockapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    suspend fun getAllStocks(): Flow<Resource<List<StockData>>>
}