package com.example.myapplication.module

import android.app.Application
import androidx.room.Room
import com.example.myapplication.data.domain.service.CurrencyApi
import com.example.myapplication.data.local.CurrencyRoomDatabase
import com.example.myapplication.data.repository.GlobalCurrencyData
import com.example.myapplication.data.repository.GlobalCurrencyRepository
import com.example.myapplication.util.ApiConst.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCurrencyApi(): CurrencyApi {
        val retrofit = Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(CurrencyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(application: Application): CurrencyRoomDatabase {
        return Room
            .databaseBuilder(
                application,
                CurrencyRoomDatabase::class.java,
                "currency_db"
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideRepository(
        api: CurrencyApi,
        db: CurrencyRoomDatabase
    ): GlobalCurrencyData {
        return GlobalCurrencyRepository(
            api = api,
            dao = db.currencyRateDao
        )
    }

}