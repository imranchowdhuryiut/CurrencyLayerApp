package com.imran.currencyapp.faker

import com.imran.currencyapp.data.model.CurrencyInfo
import com.imran.currencyapp.data.model.CurrencyRate
import com.imran.currencyapp.data.model.CurrencyTypes
import com.imran.currencyapp.faker.models.CurrencyInfoFaker
import com.imran.currencyapp.faker.models.CurrencyRateFaker
import com.imran.currencyapp.faker.models.CurrencyTypesFaker
import one.equinox.fritterfactory.FritterFactory

/**
 * Created by Md. Imran Chowdhury on 5/10/2021.
 */

class   FakerFactory {

    companion object {
        fun create(): FritterFactory {
            val fritterFactory = FritterFactory()

            fritterFactory.addProvider(CurrencyInfo::class.java, CurrencyInfoFaker.provider(fritterFactory))
            fritterFactory.addProvider(CurrencyRate::class.java, CurrencyRateFaker.provider(fritterFactory))
            fritterFactory.addProvider(CurrencyTypes::class.java, CurrencyTypesFaker.provider(fritterFactory))
            return fritterFactory
        }
    }

}