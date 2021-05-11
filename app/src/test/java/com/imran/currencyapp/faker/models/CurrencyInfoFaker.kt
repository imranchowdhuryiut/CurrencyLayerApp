package com.imran.currencyapp.faker.models

import com.imran.currencyapp.data.model.CurrencyInfo
import one.equinox.fritterfactory.FritterFactory
import one.equinox.fritterfactory.mold.MapMold
import one.equinox.fritterfactory.providers.ModelProvider

/**
 * Created by Md. Imran Chowdhury on 5/10/2021.
 */

class CurrencyInfoFaker(private val factory: FritterFactory) {

    fun getList(count: Int = 5, data: HashMap<String, Any>? = hashMapOf()): MutableList<CurrencyInfo> {

        val list = factory.buildList(CurrencyInfo::class.java, count)

        list.forEach {
        }

        return list
    }

    fun getSingleItem(): CurrencyInfo {
        return factory.build(CurrencyInfo::class.java)
    }

    companion object {
        fun provider(factory: FritterFactory): ModelProvider<CurrencyInfo> {
            val mold = MapMold()
            return ModelProvider(factory, CurrencyInfo::class.java, mold)
        }
    }
}