package com.example.myapplication.data.repository

import com.example.myapplication.data.domain.model.CurrencyRate
import com.example.myapplication.data.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface GlobalCurrencyData {
    fun getCurrencyRatesList(): Flow<Resource<List<CurrencyRate>>>
}