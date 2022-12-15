package xyz.savvamirzoyan.a3app.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import xyz.savvamirzoyan.a3app.features.start.MainActivity

@Module
interface ActivityModule {

    @PerActivity
    @ContributesAndroidInjector
    fun provideMainActivity(): MainActivity
}
