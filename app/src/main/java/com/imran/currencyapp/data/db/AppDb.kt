package com.imran.currencyapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.imran.currencyapp.data.db.dao.CurrencyDao
import com.imran.currencyapp.data.model.CurrencyInfo
/**
 * Created by Md. Imran Chowdhury on 5/9/2021.
 */

@Database(
        entities = [
            CurrencyInfo::class
        ],
        version = 1,
        exportSchema = false
)
abstract class AppDb: RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
}

