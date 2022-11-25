package com.kc.stockapp.presentation.stock_listing

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kc.stockapp.domain.models.StockData
import com.kc.stockapp.utils.Utils

@Composable
fun StockItem(
    stock: StockData,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            stock.symbol?.let { symbol ->
                Text(
                    text = symbol,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
            }

            stock.ltp?.let { ltp ->
                Text(
                    text = "LTP: ₹ ${String.format("%.2f", ltp)}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            stock.quantity?.let { quantity ->
                Text(
                    text = quantity.toString(),
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
            }

            Utils.getProfitLoss(stock)?.let { pnl ->
                Text(
                    text = "P/L: ₹ ${String.format("%.2f", pnl)}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
            }
        }
    }
}