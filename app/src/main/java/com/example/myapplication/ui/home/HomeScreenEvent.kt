package com.example.myapplication.ui.home

sealed class HomeScreenEvent{
    object FromCurrencySelect: HomeScreenEvent()
    object ToCurrencySelect: HomeScreenEvent()
    object SwapIconClicked: HomeScreenEvent()
    data class BottomSheetItemClicked(val value: String): HomeScreenEvent()
    data class NumberButtonClicked(val value: String): HomeScreenEvent()
}
