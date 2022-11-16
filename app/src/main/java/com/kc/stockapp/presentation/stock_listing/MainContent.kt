package com.kc.stockapp.presentation.stock_listing

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kc.stockapp.domain.models.StockData

@Composable
fun MainContent(
    stocks: List<StockData>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(stocks.size) { i ->
            val stock = stocks[i]
            StockItem(
                stock = stock
            )
            if(i < stocks.size) {
                Divider(modifier = Modifier.padding(
                    horizontal = 16.dp
                ))
            }
        }
    }
}