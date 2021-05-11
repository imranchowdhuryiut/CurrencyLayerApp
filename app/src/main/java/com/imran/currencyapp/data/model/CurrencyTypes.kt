package com.imran.currencyapp.data.model
import com.google.gson.annotations.SerializedName


/**
 * Created by Md. Imran Chowdhury on 5/8/2021.
 */


data class CurrencyTypes(
    @SerializedName("currencies")
    var currencies: Map<String, String>? = null,
    @SerializedName("privacy")
    var privacy: String? = null,
    @SerializedName("success")
    var success: Boolean? = null,
    @SerializedName("terms")
    var terms: String? = null
)