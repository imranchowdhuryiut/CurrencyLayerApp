package com.imran.currencyapp

import com.imran.currencyapp.data.repository.implimentation.CurrencyRepositoryImplTest
import com.imran.currencyapp.data.repository.implimentation.PrefRepositoryImplTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

/**
 * Created by Md. Imran Chowdhury on 5/10/2021.
 */


@RunWith(Suite::class)
@Suite.SuiteClasses(
        CurrencyRepositoryImplTest::class,
        PrefRepositoryImplTest::class
)
class UnitTestSuite