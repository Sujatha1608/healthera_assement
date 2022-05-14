package sampleproject.com.my.skeletonApp.di.modules

import sampleproject.com.my.skeletonApp.AppPreference
import sampleproject.com.my.skeletonApp.AppResourceProvider
import sampleproject.com.my.skeletonApp.core.Router
import sampleproject.com.my.skeletonApp.core.util.SchedulerProvider
import sampleproject.com.my.skeletonApp.rest.GeneralService
import android.app.Application
import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import sampleproject.com.my.skeletonApp.di.AppSchedulers
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
class NetworkModule {

    companion object {
        // API Repository
        private const val URL = "https://34574e81-855b-4c10-8987-935950fdd23c.mock.pstmn.io/"

    }

    @Provides
    @Singleton
    fun getContext(application: Application): Context{
        return application
    }

    @Provides
    @Singleton
    fun getRouter(): Router {
        return Router()
    }

    @Provides
    @Singleton
    internal fun provideSchedulerProvider(): SchedulerProvider = AppSchedulers()

    @Provides
    @Singleton
    fun provideGson(): Gson =
        GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

    @Provides
    @Named("real")
    @Singleton
    fun provideOkHttpClientCredential(application: Application): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val cacheDir = File(application.cacheDir, UUID.randomUUID().toString())
        val cache = Cache(cacheDir, 10 * 1024 * 1024)

        return OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideGeneralService(gson: Gson, @Named("real") okHttpClient: OkHttpClient): GeneralService {
        val baseUrl = URL
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient)
            .build().create(GeneralService::class.java)
    }

    @Provides
    @Singleton
    fun getSharedPreferences(context: Context): AppPreference{
        return AppPreference(context)
    }

    @Provides
    @Singleton
    fun getResourceProvider(context: Context): AppResourceProvider{
        return AppResourceProvider(context)
    }
}