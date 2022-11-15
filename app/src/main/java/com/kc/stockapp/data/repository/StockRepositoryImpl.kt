package com.kc.stockapp.data.repository

import com.kc.stockapp.data.remote.StockApi
import com.kc.stockapp.domain.models.StockData
import com.kc.stockapp.domain.repository.StockRepository
import com.kc.stockapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StockRepositoryImpl @Inject constructor(
    private val api: StockApi): StockRepository {

    override suspend fun getAllStocks(): Flow<Resource<List<StockData>>> {
        TODO("Not yet implemented")
    }
}