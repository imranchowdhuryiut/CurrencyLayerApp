package com.imran.currencyapp.faker.models

import com.imran.currencyapp.data.model.CurrencyTypes
import com.imran.currencyapp.faker.provider.MapProvider
import com.imran.currencyapp.faker.provider.MapStringProvider
import one.equinox.fritterfactory.FritterFactory
import one.equinox.fritterfactory.mold.MapMold
import one.equinox.fritterfactory.providers.ModelProvider

/**
 * Created by Md. Imran Chowdhury on 5/10/2021.
 */

class CurrencyTypesFaker(private val factory: FritterFactory) {

    fun getList(count: Int = 5, data: HashMap<String, Any>? = hashMapOf()): MutableList<CurrencyTypes> {

        val list = factory.buildList(CurrencyTypes::class.java, count)

        list.forEach {
        }

        return list
    }

    fun getSingleItem(): CurrencyTypes {
        return factory.build(CurrencyTypes::class.java)
    }

    companion object {
        fun provider(factory: FritterFactory): ModelProvider<CurrencyTypes> {
            val mold = MapMold()
            mold.put("currencies", MapStringProvider())
            return ModelProvider(factory, CurrencyTypes::class.java, mold)
        }
    }
}