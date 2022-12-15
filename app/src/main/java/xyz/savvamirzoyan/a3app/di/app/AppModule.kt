package xyz.savvamirzoyan.a3app.di.app

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import at.allaboutapps.retrofit.converter.unwrap.UnwrapConverterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import xyz.savvamirzoyan.a3app.BuildConfig
import xyz.savvamirzoyan.a3app.di.viewmodel.ViewModelModule
import xyz.savvamirzoyan.a3app.networking.UserAgentInterceptor
import xyz.savvamirzoyan.a3app.networking.services.ApiService
import java.util.Date
import javax.inject.Singleton

@Module(
    includes = [
        ViewModelModule::class,
        AppModule.Bindings::class,
    ],
)
class AppModule {

    private val json = Json { ignoreUnknownKeys = true }
    private val contentType = "application/json".toMediaType()

    @Module
    interface Bindings {
        @Binds
        fun context(app: Application): Context
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(Date::class.java, Rfc3339DateJsonAdapter())
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttp(agentInterceptor: UserAgentInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(agentInterceptor)

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }

        return builder.build()
    }

    @Singleton
    @Provides
    fun provideApiService(
        okHttp: OkHttpClient,
        moshi: Moshi,
    ): ApiService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_API_URL)
            .client(okHttp)
            .addConverterFactory(UnwrapConverterFactory())
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
            .create(ApiService::class.java)
    }

    @Reusable
    @Provides
    fun preferences(app: Application): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(app)
}
