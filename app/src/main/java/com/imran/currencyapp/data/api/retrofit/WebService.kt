package com.imran.currencyapp.data.api.retrofit


import com.imran.currencyapp.data.model.CurrencyRate
import com.imran.currencyapp.data.model.CurrencyTypes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Md. Imran Chowdhury on 5/7/2021.
 */
interface WebService {

    @GET("/list")
    fun getCurrencyTypes(
            @Query("access_key") access_key: String
    ): Call<CurrencyTypes>

    @GET("/live")
    fun getCurrencyRates(
            @Query("access_key") access_key: String
    ): Call<CurrencyRate>

}