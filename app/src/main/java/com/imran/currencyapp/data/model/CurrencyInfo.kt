package com.imran.currencyapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Md. Imran Chowdhury on 5/8/2021.
 */

@Entity(tableName = "currency")
data class CurrencyInfo(
        @PrimaryKey
        var code: String = "",
        var name: String = "",
        var rate: Double = 0.0
)