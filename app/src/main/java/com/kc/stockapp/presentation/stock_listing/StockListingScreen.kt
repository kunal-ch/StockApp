package com.kc.stockapp.presentation.stock_listing

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kc.stockapp.utils.NetworkUtils
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun StockListingScreen(
    viewModel: StockListingViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scope = rememberCoroutineScope()
    val sheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )

    BottomSheetScaffold(
        scaffoldState = sheetScaffoldState,
        sheetElevation = 0.dp,
        sheetBackgroundColor = Color.Transparent,
        sheetPeekHeight = 49.dp,
        sheetContent = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                IconButton(onClick = {
                    scope.launch {
                        if (sheetState.isCollapsed) {
                            sheetState.expand()
                        } else if (sheetState.isExpanded) {
                            sheetState.collapse()
                        }
                    }
                }) {
                    val icon = if (sheetState.isExpanded) {
                        Icons.Filled.KeyboardArrowDown
                    } else {
                        Icons.Filled.KeyboardArrowUp
                    }

                    Icon(
                        imageVector = icon,
                        contentDescription = "Icon button"
                    )
                }

                BottomSheetContent(state.stocks)
            }
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if(state.isLoading) {
                CircularProgressIndicator()
            } else if(state.error != null) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colors.error
                )
            }
            if(state.error == null) {
                MainContent(state.stocks)
            }
        }
    }
}