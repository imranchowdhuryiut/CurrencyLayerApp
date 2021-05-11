package com.imran.currencyapp.data.repository.implimentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.imran.currencyapp.data.Constants
import com.imran.currencyapp.data.api.apiExt.parseErrorBody
import com.imran.currencyapp.data.model.CurrencyInfo
import com.imran.currencyapp.data.repository.interfaces.CurrencyRepository
import com.imran.currencyapp.data.repository.interfaces.PrefRepository
import com.imran.currencyapp.data.db.dao.CurrencyDao
import com.imran.currencyapp.data.model.CurrencyRate
import com.imran.currencyapp.data.model.CurrencyTypes
import com.imran.currencyapp.data.api.network.AppExecutor
import com.imran.currencyapp.data.api.network.Resource
import com.imran.currencyapp.data.api.retrofit.WebService
import retrofit2.Response
import java.util.*
import javax.inject.Inject

/**
 * Created by Md. Imran Chowdhury on 5/8/2021.
 */
class CurrencyRepositoryImpl @Inject constructor(
        private val mPrefRepo: PrefRepository,
        private val mWebService: WebService,
        private val mExecutor: AppExecutor,
        private val mDao: CurrencyDao
): CurrencyRepository {

    override fun getCurrencyData(): LiveData<Resource<List<CurrencyInfo>>> {
        val liveData = MutableLiveData<Resource<List<CurrencyInfo>>>()
        liveData.postValue(Resource.loading(null))
        mExecutor.networkThread {
            val lastFetchedTime = mPrefRepo.getLogTime()
            val currentTime = Calendar.getInstance().time.time
            val period = 30 * 60 * 1000

            if (lastFetchedTime + period > currentTime) {
                val data = mDao.getAllCurrencyData()
                liveData.postValue(Resource.success(data))
            } else {
                val currencyTypes = mWebService.getCurrencyTypes(Constants.ACCESS_KEY).execute()
                val currencyRates = mWebService.getCurrencyRates(Constants.ACCESS_KEY).execute()
                val result = assembleFetchedData(currencyTypes, currencyRates)
                liveData.postValue(result)
            }
        }
        return liveData
    }


    private fun assembleFetchedData(
            currencyTypesResponse: Response<CurrencyTypes>?,
            currencyRatesResponse: Response<CurrencyRate>?
    ): Resource<List<CurrencyInfo>> {
        try {
            if (currencyTypesResponse?.isSuccessful == true && currencyRatesResponse?.isSuccessful == true) {
                val currencyTypes = currencyTypesResponse.body()
                val currencyRates = currencyRatesResponse.body()
                if (currencyRates != null && currencyTypes != null) {
                    val currencyInfoList = mutableListOf<CurrencyInfo>()
                    val currencySource = currencyRates.source
                    currencyTypes.currencies?.keys?.forEach {
                        val model = CurrencyInfo()
                        model.name = currencyTypes.currencies?.get(it) ?: ""
                        model.code = it
                        model.rate = currencyRates.quotes?.get(currencySource + it) ?: 1.0
                        currencyInfoList.add(model)
                    }
                    mDao.deleteAll()
                    mDao.save(currencyInfoList)
                    mPrefRepo.logTime(Date().time)
                    return Resource.success(currencyInfoList)
                } else {
                    return Resource.error("Something went wrong", null)
                }
            } else {
                val message = parseErrorBody(currencyRatesResponse)
                return Resource.error(message, null)
            }
        } catch (exception: Exception) {
            Log.d("CurrencyRepositoryImpl", exception.message ?: "")
            return Resource.error("Something went wrong", null)
        }
    }
}