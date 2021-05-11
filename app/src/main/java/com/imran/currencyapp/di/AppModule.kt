package com.imran.currencyapp.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.imran.currencyapp.data.Constants
import com.imran.currencyapp.data.api.network.AppExecutor
import com.imran.currencyapp.data.api.network.IAppExecutor
import com.imran.currencyapp.data.api.retrofit.WebService
import com.imran.currencyapp.data.db.AppDb
import com.imran.currencyapp.data.db.dao.CurrencyDao
import com.imran.currencyapp.data.repository.implimentation.CurrencyRepositoryImpl
import com.imran.currencyapp.data.repository.implimentation.PrefRepositoryImpl
import com.imran.currencyapp.data.repository.interfaces.CurrencyRepository
import com.imran.currencyapp.data.repository.interfaces.PrefRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * https://github.com/googlesamples/android-architecture-components/tree/master/GithubBrowserSample/app/src/main/java/com/android/example/github/di
 */
@Module(includes = [(ViewModelModule::class)])
internal class AppModule {

    @Singleton
    @Provides
    fun provideGithubService(
            gson: Gson,
            stethoInterceptor: StethoInterceptor? = null,
            chuckerInterceptor: ChuckerInterceptor? = null
    ): WebService {

        val clientBuilder = OkHttpClient.Builder()

        chuckerInterceptor?.let { clientBuilder.addInterceptor(it) }
        stethoInterceptor?.let { clientBuilder.addNetworkInterceptor(it) }


        return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(clientBuilder.build())
                .build()
                .create(WebService::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): AppDb {
        return Room.databaseBuilder(app, AppDb::class.java, Constants.DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun provideAppExecutor(data: IAppExecutor): AppExecutor {
        return data
    }

 @Singleton
    @Provides
    fun provideContext(app: Application): Context {
        return app.applicationContext
    }


    @Singleton
    @Provides
    fun provideGson(): Gson {
        val builder = GsonBuilder()
        return builder.create()

    }


    @Singleton
    @Provides
    fun provideStethoInterceptor(): StethoInterceptor {
        return StethoInterceptor()
    }

    @Singleton
    @Provides
    fun provideChuckerInterceptor(context: Context): ChuckerInterceptor {
        val chuckerCollector = ChuckerCollector(
                context = context,
                // Toggles visibility of the push notification
                showNotification = true,
                // Allows to customize the retention period of collected data
                retentionPeriod = RetentionManager.Period.ONE_DAY
        )

        // Create the Interceptor
        val chuckerInterceptor = ChuckerInterceptor(
                context = context,
                // The previously created Collector
                collector = chuckerCollector,
                // The max body content length, after this responses will be truncated.
                maxContentLength = 250000L,
                // List of headers to obfuscate in the Chucker UI
                headersToRedact = mutableSetOf("Authrization")
        )

        return chuckerInterceptor
    }


    @Singleton
    @Provides
    fun provideCurrencyDao(db: AppDb): CurrencyDao {
        return db.currencyDao()
    }

    @Singleton
    @Provides
    fun providePrefRepository(repo: PrefRepositoryImpl): PrefRepository {
        return repo
    }


    @Singleton
    @Provides
    fun provideCurrencyRepository(repo: CurrencyRepositoryImpl): CurrencyRepository {
        return repo
    }

    @Singleton
    @Provides
    fun provideSharedPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences(Constants.APP_SHARED_PREF, Context.MODE_PRIVATE)
    }

}