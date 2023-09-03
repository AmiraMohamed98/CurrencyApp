package com.example.myapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.local.dao.CurrencyRateDao
import com.example.myapplication.data.local.entity.CurrencyRateEntity

@Database(
    entities = [CurrencyRateEntity::class],
    version = 1
)
abstract class CurrencyRoomDatabase :RoomDatabase(){
    abstract val currencyRateDao: CurrencyRateDao
}