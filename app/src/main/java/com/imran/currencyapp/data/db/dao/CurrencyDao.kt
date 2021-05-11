package com.imran.currencyapp.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.imran.currencyapp.data.model.CurrencyInfo

/**
 * Created by Md. Imran Chowdhury on 5/9/2021.
 */


@Dao
abstract class CurrencyDao: BaseDao<CurrencyInfo>() {
    @Query("SELECT * FROM currency")
    abstract fun getAllCurrencyData(): List<CurrencyInfo>

    @Query("DELETE FROM currency")
    abstract fun deleteAll()

}