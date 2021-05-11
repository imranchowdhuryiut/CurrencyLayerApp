package com.imran.currencyapp.data.repository.interfaces

import androidx.lifecycle.LiveData
import com.imran.currencyapp.data.model.CurrencyInfo
import com.imran.currencyapp.data.api.network.Resource

/**
 * Created by Md. Imran Chowdhury on 5/8/2021.
 */
interface CurrencyRepository {
    fun getCurrencyData(): LiveData<Resource<List<CurrencyInfo>>>
}