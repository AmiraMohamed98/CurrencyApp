package com.example.myapplication.ui.home

import com.example.myapplication.data.domain.model.CurrencyRate

data class HomeScreenState(
    val fromCurrencyCode: String = "INR",
    val toCurrencyCode: String = "USD",
    val fromCurrencyValue: String = "0.00",
    val toCurrencyValue: String = "0.00",
    val selection: SelectionState = SelectionState.FROM,
    val currencyRates: Map<String, CurrencyRate> = emptyMap(),
    val error: String? = null
)
enum class SelectionState {
    FROM,
    TO
}