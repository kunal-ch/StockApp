package com.kc.stockapp.presentation.stock_listing

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kc.stockapp.domain.repository.StockRepository
import com.kc.stockapp.utils.NetworkUtils
import com.kc.stockapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockListingViewModel @Inject constructor(
    private val mContext : Application,
    private val repository: StockRepository
): ViewModel() {

    var state by mutableStateOf(StockListingState())

    init {
        getAllStocks()
    }

    private fun getAllStocks() {
        if (!NetworkUtils.isNetworkAvailable(context = mContext)){
            Log.d(TAG, "isNetworkAvailable: No")
            state = state.copy(isLoading = false, error = "Internet connection required")
        } else {
            Log.d(TAG, "isNetworkAvailable: Yes")
            viewModelScope.launch {
                repository
                    .getAllStocks()
                    .collect { result ->
                        when (result) {
                            is Resource.Success -> {
                                result.data?.let { listings ->
                                    state = state.copy(
                                        stocks = listings
                                    )
                                    /*listings.forEach {
                                        Log.d("TAG","${it.company_name}")
                                    }*/
                                }
                            }
                            is Resource.Error -> state = state.copy(isLoading = false, error = result.message)
                            is Resource.Loading -> {
                                state = state.copy(isLoading = result.isLoading)
                            }
                        }
                    }
            }
        }
    }

    companion object {
        const val TAG = "StockListingViewModel"
    }
}