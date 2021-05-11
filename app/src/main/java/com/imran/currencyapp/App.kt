package com.imran.currencyapp

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.facebook.stetho.Stetho
import com.imran.currencyapp.di.AppComponent
import com.imran.currencyapp.di.DaggerAppComponent
import dagger.android.*
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


/**
 * Created by Md. Imran Chowdhury on 5/7/2021.
 * Application class
 */
class App : Application(), HasActivityInjector, HasSupportFragmentInjector {


    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var dispatchingFragmentAndroidInjector: DispatchingAndroidInjector<Fragment>


    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return dispatchingFragmentAndroidInjector
    }

    companion object {
        var appComponent: AppComponent? = null
    }


    override fun onCreate() {
        super.onCreate()

        //Instantiate Dagger
        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build()
        appComponent?.inject(this)

        Stetho.initializeWithDefaults(this);
        //Register activity lifeCycle callback listener for automatically injecting every activity
        //that launches
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks() {
            override fun onActivityCreated(activity: Activity?, bundle: Bundle?) {
                try {
                    activity?.let { AndroidInjection.inject(activity) }
                } catch (ex: Exception) {
                    Log.w("APP", "No Contributor for the activity")
                }
            }
        })
    }


    abstract class ActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {
        override fun onActivityPaused(activity: Activity?) {
        }

        override fun onActivityResumed(activity: Activity?) {
        }

        override fun onActivityStarted(activity: Activity?) {
        }

        override fun onActivityDestroyed(activity: Activity?) {
        }

        override fun onActivitySaveInstanceState(activity: Activity?, bundle: Bundle?) {
        }

        override fun onActivityStopped(activity: Activity?) {
        }

        override fun onActivityCreated(activity: Activity?, bundle: Bundle?) {
        }
    }
}
