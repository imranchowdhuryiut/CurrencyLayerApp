package com.imran.currencyapp.faker.provider

import one.equinox.fritterfactory.util.RandomFactory
import javax.inject.Provider

/**
 * Created by Md. Imran Chowdhury on 5/10/2021.
 */

class MapStringProvider: Provider<Map<String, String>> {

    private var random = RandomFactory().get()

    override fun get(): Map<String, String> {

        return mapOf(
                "aaa" to "jjj",
                "bbb" to "jjj"
        )
    }

}