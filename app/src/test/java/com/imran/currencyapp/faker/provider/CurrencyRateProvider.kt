package com.imran.currencyapp.faker.provider

import com.imran.currencyapp.data.model.CurrencyRate
import com.imran.currencyapp.faker.models.CurrencyRateFaker
import javax.inject.Provider

/**
 * Created by Md. Imran Chowdhury on 5/10/2021.
 */

class CurrencyRateProvider(private val faker: CurrencyRateFaker) : Provider<CurrencyRate> {

    override fun get(): CurrencyRate {
        return faker.getSingleItem()
    }
}