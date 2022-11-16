package com.kc.stockapp.presentation.stock_listing

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kc.stockapp.domain.repository.StockRepository
import com.kc.stockapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockListingViewModel @Inject constructor(
    private val repository: StockRepository
): ViewModel() {

    var state by mutableStateOf(StockListingState())

    init {
        getAllStocks()
    }

    private fun getAllStocks() {
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
                                listings.forEach {
                                    Log.d("StockListingViewModel","${it.company_name}")
                                }
                            }
                        }
                        is Resource.Error -> Unit
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }
}