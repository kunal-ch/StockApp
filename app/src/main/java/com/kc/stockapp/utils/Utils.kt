package com.kc.stockapp.utils

import com.kc.stockapp.domain.models.StockData

object Utils {

    fun getStockCurrentValue(stockData: StockData): Double? {
        if (stockData.ltp != null && stockData.quantity != null){
            return (stockData.ltp * stockData.quantity)
        }
        return null
    }

    fun getStockInvestmentValue(stockData: StockData): Double? {
        stockData.avg_price?.let {
            val avg_price = it.toDoubleOrNull()
            if (avg_price != null && stockData.quantity != null){
                return (avg_price * stockData.quantity)
            }
        }
        return null
    }

    fun getProfitLoss(stockData: StockData): Double?{
        val currentValue = getStockCurrentValue(stockData)
        val investmentValue = getStockInvestmentValue(stockData)
        if (currentValue != null && investmentValue != null){
            return currentValue-investmentValue
        }
        return null
    }

    fun getCurrentValueSum(stockList: List<StockData>): Double{
        var currentValueSum = 0.0
        for (stock in stockList){
            getStockCurrentValue(stock)?.let {
                currentValueSum += it
            }
        }
        return currentValueSum
    }

    fun getInvestmentValueSum(stockList: List<StockData>): Double{
        var investmentValueSum = 0.0
        for (stock in stockList){
            getStockInvestmentValue(stock)?.let {
                investmentValueSum += it
            }
        }
        return investmentValueSum
    }

    fun getTotalProfitLoss(stockList: List<StockData>): Double{
        var totalProfitLoss = 0.0
        val currentValueSum = getCurrentValueSum(stockList)
        val investmentValueSum = getInvestmentValueSum(stockList)
        totalProfitLoss = currentValueSum - investmentValueSum
        return totalProfitLoss
    }

    fun getTodayProfitLoss(stockList: List<StockData>): Double{
        var result = 0.0
        for (stock in stockList){
            val close = stock.close?:0.0
            val ltp = stock.ltp?:0.0
            val quantity = stock.quantity?:0
            val pnl = (close - ltp) * quantity
            result += pnl
        }
        return result
    }
}