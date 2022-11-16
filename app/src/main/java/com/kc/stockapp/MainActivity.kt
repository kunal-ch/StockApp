package com.kc.stockapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kc.stockapp.presentation.stock_listing.StockListingScreen
import com.kc.stockapp.theme.StockAppTheme
import com.kc.stockapp.theme.upStoxColor
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        TopAppBar(
                            title = {
                                Text(text = "Upstox Holding")
                            },
                            backgroundColor = upStoxColor,
                            contentColor = Color.White,
                            elevation = 0.dp
                        )
                        StockListingScreen()
                    }
                }
            }
        }
    }
}