package com.imran.currencyapp.faker.provider

import com.imran.currencyapp.data.model.CurrencyInfo
import com.imran.currencyapp.faker.models.CurrencyInfoFaker
import javax.inject.Provider

/**
 * Created by Md. Imran Chowdhury on 5/10/2021.
 */

class CurrencyInfoProvider(private val faker: CurrencyInfoFaker) : Provider<CurrencyInfo> {

    override fun get(): CurrencyInfo {
        return faker.getSingleItem()
    }
}