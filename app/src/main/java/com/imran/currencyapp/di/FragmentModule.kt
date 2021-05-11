package com.imran.currencyapp.di

import com.imran.currencyapp.view.fragments.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * https://github.com/googlesamples/android-architecture-components/tree/master/GithubBrowserSample/app/src/main/java/com/android/example/github/di
 */
@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun contributeHomeFragment(): HomeFragment

}