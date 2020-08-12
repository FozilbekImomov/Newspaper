package uz.fozilbekimomov.newspaper.core.app

import android.content.Context
import androidx.multidex.MultiDex
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber
import uz.fozilbekimomov.newspaper.BuildConfig
import uz.fozilbekimomov.newspaper.core.cache.MyCache
import uz.fozilbekimomov.newspaper.core.di.component.DaggerAppComponent


class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        MyCache.init(this)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}