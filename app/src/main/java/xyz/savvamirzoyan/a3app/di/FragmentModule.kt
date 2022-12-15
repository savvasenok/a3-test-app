package xyz.savvamirzoyan.a3app.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import xyz.savvamirzoyan.a3app.features.main.MainFragment

@Module
interface FragmentModule {

    @PerFragment
    @ContributesAndroidInjector
    fun provideMainFragment(): MainFragment
}
