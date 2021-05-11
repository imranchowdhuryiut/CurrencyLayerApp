package com.imran.currencyapp.faker.provider

import one.equinox.fritterfactory.util.RandomFactory
import javax.inject.Provider

/**
 * Created by Md. Imran Chowdhury on 5/10/2021.
 */

class MapProvider: Provider<Map<String, Double>> {

    private var random = RandomFactory().get()

    override fun get(): Map<String, Double> {

        return mapOf(
                "aaa" to 1.0,
                "bbb" to 2.0
        )
    }

}