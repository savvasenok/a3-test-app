package xyz.savvamirzoyan.a3app.application

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber
import xyz.savvamirzoyan.a3app.BuildConfig
import xyz.savvamirzoyan.a3app.di.AppInjector
import xyz.savvamirzoyan.a3app.di.app.DaggerAppComponent

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().create(this)

    override fun onCreate() {
        super.onCreate()

        initLogging()

        AppInjector.init(this)
    }

    private fun initLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
