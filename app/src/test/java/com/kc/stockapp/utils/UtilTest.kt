package com.kc.stockapp.utils

import com.kc.stockapp.domain.models.StockData
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UtilTest {

    val stockList = mutableListOf<StockData>()

    // ltp, quantity, avg_price, close
    @Before
    fun setUp(){
        val stockData1 = StockData("26.00", 90.0, null, null, null, null, null, null, null, null, 100.5, null, 575, null, null, null, null, null, null,)
        val stockData2 = StockData("450.05", 180.5, null, null, null, null, null, null, null, null, 200.15, null, 3, null, null, null, null, null, null,)
        val stockData3 = StockData("390.06", 300.5, null, null, null, null, null, null, null, null, 400.2, null, 40, null, null, null, null, null, null,)
        val stockData4 = StockData("63.57", 550.15, null, null, null, null, null, null, null, null, 500.45, null, 9, null, null, null, null, null, null,)
        stockList.add(stockData1)
        stockList.add(stockData2)
        stockList.add(stockData3)
        stockList.add(stockData4)
    }

    @Test
    fun testCurrentValueSumValid(){
        val currentValueSum = Utils.getCurrentValueSum(stockList)
        val expected: Double = 78900.0
        Assert.assertEquals(currentValueSum, expected, expected)
    }

    @Test
    fun testCurrentValueSumInValid(){
        val currentValueSum = Utils.getCurrentValueSum(stockList)
        val expected: Double = 0.0
        Assert.assertNotEquals(currentValueSum, expected, expected)
    }

    @Test
    fun testTotalInvestmentValid(){
        val totalInvestment = Utils.getInvestmentValueSum(stockList)
        val expected: Double = 32474.68
        Assert.assertEquals(totalInvestment,expected, expected)
    }

    @Test
    fun testTotalInvestmentInValid(){
        val totalInvestment = Utils.getInvestmentValueSum(stockList)
        val expected: Double = 0.0
        Assert.assertNotEquals(totalInvestment, expected, expected)
    }

    @Test
    fun testTodaysProfitLossValid(){
        val todaysProfitLoss = Utils.getTodayProfitLoss(stockList)
        val expected: Double = -9637.15
        Assert.assertEquals(todaysProfitLoss,expected, expected)
    }

    @Test
    fun testTodaysProfitLossInValid(){
        val todaysProfitLoss = Utils.getTodayProfitLoss(stockList)
        val expected: Double = 0.0
        Assert.assertNotEquals(todaysProfitLoss, expected, expected)
    }

    @Test
    fun testProfitLossValid(){
        val profitLoss = Utils.getTotalProfitLoss(stockList)
        val expected: Double = 46425.32
        Assert.assertEquals(profitLoss,expected, expected)
    }

    @Test
    fun testProfitLossInValid(){
        val profitLoss = Utils.getTotalProfitLoss(stockList)
        val expected: Double = 0.0
        Assert.assertNotEquals(profitLoss, expected, expected)
    }
}