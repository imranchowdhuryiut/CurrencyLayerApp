package com.imran.currencyapp.viewmodel.implimentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.imran.currencyapp.data.model.CurrencyInfo
import com.imran.currencyapp.data.repository.interfaces.CurrencyRepository
import com.imran.currencyapp.viewmodel.interfaces.CurrencyViewModel
import com.imran.currencyapp.data.api.network.Resource
import javax.inject.Inject

/**
 * Created by Md. Imran Chowdhury on 5/9/2021.
 */
class ICurrencyViewModel @Inject constructor(
        private val mRepo: CurrencyRepository
): ViewModel(), CurrencyViewModel {

    override fun getCurrencyList(): LiveData<Resource<List<CurrencyInfo>>> {
        return mRepo.getCurrencyData()
    }

}