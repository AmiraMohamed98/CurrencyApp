package com.example.myapplication.data.repository

import com.example.myapplication.data.domain.model.CurrencyRate
import com.example.myapplication.data.domain.model.Resource
import com.example.myapplication.data.domain.model.toCurrencyRates
import com.example.myapplication.data.domain.service.CurrencyApi
import com.example.myapplication.data.local.dao.CurrencyRateDao
import com.example.myapplication.data.local.entity.toCurrencyRate
import com.example.myapplication.data.local.entity.toCurrencyRateEntity
import com.example.myapplication.util.ApiConst.ACCESS_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException



class GlobalCurrencyRepository(
    private val api: CurrencyApi,
    private val dao: CurrencyRateDao
) :GlobalCurrencyData{
    override fun getCurrencyRatesList(): Flow<Resource<List<CurrencyRate>>> =
        flow {
        val localCurrencyRates = getLocalCurrencyRates()

        emit(Resource.Success(localCurrencyRates))

        try {
            val newRates = getRemoteCurrencyRates()


            updateLocalCurrencyRates(newRates)
            emit(Resource.Success(newRates))


        } catch (e: IOException) {

            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection",
                    data = localCurrencyRates
                )
            )
        } catch (e: Exception) {

            emit(
                Resource.Error(
                    message = "Oops, something went wrong! ${e.message}",
                    data = localCurrencyRates
                )
            )
        }

    }


    private suspend fun getLocalCurrencyRates(): List<CurrencyRate> {
        return dao.getAllCurrencyRates().map { it.toCurrencyRate() }
    }


    private suspend fun getRemoteCurrencyRates(): List<CurrencyRate> {
        val response = api.getLatest(ACCESS_KEY)
        return response.toCurrencyRates()
    }



    private suspend fun updateLocalCurrencyRates(currencyRates: List<CurrencyRate>) {
        dao.upsertAll(currencyRates.map { it.toCurrencyRateEntity() })
    }
}