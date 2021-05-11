package com.imran.currencyapp.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Md. Imran Chowdhury on 5/9/2021.
 */

data class ErrorResponse(
        @SerializedName("error")
        var error: Errors? = null,
        @SerializedName("success")
        var success: String? = null
)


data class Errors(
    @SerializedName("code")
    var code: Int? = null,
    @SerializedName("info")
    var info: String? = null
)