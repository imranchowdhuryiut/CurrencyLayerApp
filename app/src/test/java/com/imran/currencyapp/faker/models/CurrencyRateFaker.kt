package com.imran.currencyapp.faker.models

import android.location.LocationProvider
import com.imran.currencyapp.data.model.CurrencyRate
import com.imran.currencyapp.faker.provider.MapProvider
import one.equinox.fritterfactory.FritterFactory
import one.equinox.fritterfactory.mold.MapMold
import one.equinox.fritterfactory.providers.ModelProvider
import one.equinox.fritterfactory.providers.lorem.WordProvider

/**
 * Created by Md. Imran Chowdhury on 5/10/2021.
 */

class CurrencyRateFaker(private val factory: FritterFactory) {

    fun getList(count: Int = 5, data: HashMap<String, Any>? = hashMapOf()): MutableList<CurrencyRate> {

        val list = factory.buildList(CurrencyRate::class.java, count)

        list.forEach {
        }

        return list
    }

    fun getSingleItem(): CurrencyRate {
        return factory.build(CurrencyRate::class.java)
    }

    companion object {
        fun provider(factory: FritterFactory): ModelProvider<CurrencyRate> {
            val mold = MapMold()
            mold.put("privacy", WordProvider(7, 9))
            mold.put("source", WordProvider())
            mold.put("address", WordProvider(15))
            mold.put("terms", WordProvider())
            mold.put("quotes", MapProvider())
            return ModelProvider(factory, CurrencyRate::class.java, mold)
        }
    }
}