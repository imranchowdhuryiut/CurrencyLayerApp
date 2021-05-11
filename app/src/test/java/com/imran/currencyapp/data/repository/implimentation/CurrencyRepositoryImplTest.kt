package com.imran.currencyapp.data.repository.implimentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.imran.currencyapp.data.Constants
import com.imran.currencyapp.data.api.retrofit.WebService
import com.imran.currencyapp.data.db.dao.CurrencyDao
import com.imran.currencyapp.data.model.CurrencyRate
import com.imran.currencyapp.data.model.CurrencyTypes
import com.imran.currencyapp.data.repository.interfaces.PrefRepository
import com.imran.currencyapp.helper.*
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify
import retrofit2.Response
import java.util.*

/**
 * Created by Md. Imran Chowdhury on 5/10/2021.
 */
class CurrencyRepositoryImplTest :
        Faker by IFaker(),
        Executor by IExecutor(),
        LiveDataHelper by ILiveDataHelper() {

    private lateinit var repository: CurrencyRepositoryImpl
    private lateinit var dao: CurrencyDao
    private lateinit var api: WebService
    private lateinit var prefRepo: PrefRepository

    override var instantTaskExecutorRule = InstantTaskExecutorRule()
        @Rule get

    @Before
    fun setUp() {
        dao = Mockito.mock(CurrencyDao::class.java)
        api = Mockito.mock(WebService::class.java)
        prefRepo = Mockito.mock(PrefRepository::class.java)

        repository = CurrencyRepositoryImpl(
                prefRepo,
                api,
                executor,
                dao
        )
    }

    @Test
    fun test_notNullObjects() {
        Assert.assertNotNull(repository)
    }

    @Test
    fun testGetCurrencyDataFetchApi() {
        val currencyRates = currencyRateFaker.getSingleItem()
        val currencyTypes = currencyTypeFaker.getSingleItem()

        Mockito.`when`(prefRepo.getLogTime()).thenReturn(0)
        Mockito.`when`(api.getCurrencyRates(Constants.ACCESS_KEY))
                .thenReturn(object : AppMockCall<CurrencyRate>() {
                    override fun execute(): Response<CurrencyRate> {
                        return Response.success(currencyRates)
                    }
                })

        Mockito.`when`(api.getCurrencyTypes(Constants.ACCESS_KEY))
                .thenReturn(object : AppMockCall<CurrencyTypes>() {
                    override fun execute(): Response<CurrencyTypes> {
                        return Response.success(currencyTypes)
                    }
                })
        repository.getCurrencyData()

        verify(prefRepo).getLogTime()
        verify(dao).deleteAll()
        verify(api).getCurrencyRates(Constants.ACCESS_KEY)
        verify(api).getCurrencyTypes(Constants.ACCESS_KEY)
    }

    @Test
    fun testGetCurrencyDataFetchDataDB() {

        Mockito.`when`(prefRepo.getLogTime()).thenReturn(Date().time)

        repository.getCurrencyData()

        verify(prefRepo).getLogTime()
        verify(dao).getAllCurrencyData()
    }

}