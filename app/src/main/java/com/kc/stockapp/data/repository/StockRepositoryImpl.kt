package com.kc.stockapp.data.repository

import android.util.Log
import com.kc.stockapp.data.remote.StockApi
import com.kc.stockapp.domain.models.StockData
import com.kc.stockapp.domain.repository.StockRepository
import com.kc.stockapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val api: StockApi): StockRepository {

    override suspend fun getAllStocks(): Flow<Resource<List<StockData>>> {
        return flow<Resource<List<StockData>>> {
            emit(Resource.Loading(true))

            val remoteListings = try {
                val response = api.getAllStocks()
                if (response.isSuccessful) {
                    response.body()?.let { resultResponse ->
                        Log.d("StockRepository", "api response successfull")
                        resultResponse.data
                    }
                } else {
                    listOf()
                }
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            Log.d("StockRepository", "Api list size : ${remoteListings?.size}")
            remoteListings?.let { listings ->

                emit(
                    Resource.Success(
                        data = remoteListings
                    )
                )
                emit(Resource.Loading(false))
            }
        }.flowOn(Dispatchers.IO)
    }
}