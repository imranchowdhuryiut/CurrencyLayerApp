package com.imran.currencyapp.viewmodel.interfaces

import androidx.lifecycle.LiveData
import com.imran.currencyapp.data.model.CurrencyInfo
import com.imran.currencyapp.data.api.network.Resource

/**
 * Created by Md. Imran Chowdhury on 5/9/2021.
 */
interface CurrencyViewModel {
    fun getCurrencyList(): LiveData<Resource<List<CurrencyInfo>>>

}