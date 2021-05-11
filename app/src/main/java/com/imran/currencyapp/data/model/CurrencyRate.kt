package com.imran.currencyapp.data.model
import com.google.gson.annotations.SerializedName


/**
 * Created by Md. Imran Chowdhury on 5/8/2021.
 */



data class CurrencyRate(
    @SerializedName("privacy")
    var privacy: String? = null,
    @SerializedName("quotes")
    var quotes: Map<String, Double>? = null,
    @SerializedName("source")
    var source: String? = null,
    @SerializedName("success")
    var success: Boolean? = null,
    @SerializedName("terms")
    var terms: String? = null,
    @SerializedName("timestamp")
    var timestamp: Int? = null
)