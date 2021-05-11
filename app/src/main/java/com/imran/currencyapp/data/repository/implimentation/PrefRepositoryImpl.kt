package com.imran.currencyapp.data.repository.implimentation

import android.content.SharedPreferences
import com.imran.currencyapp.data.repository.interfaces.PrefRepository
import javax.inject.Inject

/**
 * Created by Md. Imran Chowdhury on 5/8/2021.
 */
class PrefRepositoryImpl @Inject constructor(
        private val mSharedPreferences: SharedPreferences
): PrefRepository {

    companion object {
        private const val SP_FETCH_TIME = "SP_FETCH_TIME"
    }


    override fun logTime(time: Long) {
        saveLongToSP(time, SP_FETCH_TIME)
    }

    override fun getLogTime(): Long {
        return getLongFromSP(SP_FETCH_TIME)
    }

    private fun getLongFromSP(code: String): Long {
        return mSharedPreferences.getLong(code, 0L)
    }


    private fun saveLongToSP(value: Long, code: String) {
        val e = mSharedPreferences.edit()
        e.putLong(code, value)
        e.apply()
    }

}