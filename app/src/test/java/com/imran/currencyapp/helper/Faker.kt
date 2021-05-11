package com.imran.currencyapp.helper

import com.imran.currencyapp.faker.FakerFactory
import com.imran.currencyapp.faker.models.CurrencyInfoFaker
import com.imran.currencyapp.faker.models.CurrencyRateFaker
import com.imran.currencyapp.faker.models.CurrencyTypesFaker
import one.equinox.fritterfactory.FritterFactory


/**
 * Created by Sadman Sarar on 10/23/18.
 */

interface Faker {
    var fakerFactory: FritterFactory
    var currencyInfoFaker: CurrencyInfoFaker
    var currencyTypeFaker: CurrencyTypesFaker
    var currencyRateFaker: CurrencyRateFaker
}

class IFaker : Faker {

    override var fakerFactory: FritterFactory = FakerFactory.create()
    override var currencyInfoFaker: CurrencyInfoFaker = CurrencyInfoFaker(fakerFactory)
    override var currencyTypeFaker: CurrencyTypesFaker = CurrencyTypesFaker(fakerFactory)
    override var currencyRateFaker: CurrencyRateFaker = CurrencyRateFaker(fakerFactory)

}