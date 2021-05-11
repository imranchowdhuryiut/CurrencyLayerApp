package com.imran.currencyapp.faker.provider

import com.imran.currencyapp.data.model.CurrencyTypes
import com.imran.currencyapp.faker.models.CurrencyTypesFaker
import javax.inject.Provider

/**
 * Created by Md. Imran Chowdhury on 5/10/2021.
 */
class CurrencyTypesProvider(private val faker: CurrencyTypesFaker) : Provider<CurrencyTypes> {

    override fun get(): CurrencyTypes {
        return faker.getSingleItem()
    }
}