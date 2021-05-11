package com.imran.currencyapp.data.api.apiExt

import com.google.gson.Gson
import com.imran.currencyapp.data.model.ErrorResponse
import retrofit2.Response

/**
 * Created by Md. Imran Chowdhury on 5/8/2021.
 */

fun <T> parseErrorBody(response: Response<T>? = null): String {
    var userMsg = "Something went wrong"
    try {
        val error = Gson().fromJson(response?.errorBody()?.string(), ErrorResponse::class.java)
        userMsg = error.error?.info ?: "Something went wrong"
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
    return userMsg
}