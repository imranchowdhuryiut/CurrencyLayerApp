package com.imran.currencyapp.data.repository.interfaces

/**
 * Created by Md. Imran Chowdhury on 5/8/2021.
 */
interface PrefRepository {
    fun logTime(time: Long)
    fun getLogTime(): Long
}