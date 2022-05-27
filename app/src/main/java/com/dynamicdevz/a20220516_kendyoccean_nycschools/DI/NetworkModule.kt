package com.dynamicdevz.a20220516_kendyoccean_nycschools.DI

import com.dynamicdevz.a20220516_kendyoccean_nycschools.network.IServiceApi
import com.dynamicdevz.a20220516_kendyoccean_nycschools.network.SchoolRepository
import com.dynamicdevz.a20220516_kendyoccean_nycschools.network.SchoolRepositoryImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun providesGson(): Gson = Gson()

    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    fun providesServiceApi(okHttpClient: OkHttpClient, gson: Gson): IServiceApi =
        Retrofit.Builder()
            .baseUrl(IServiceApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(IServiceApi::class.java)

    @Provides
    fun providesIOScheduler(): Scheduler = Schedulers.io()

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable =
        CompositeDisposable()

    @Provides
    fun providesRepository(apiIServiceApi: IServiceApi): SchoolRepository =
        SchoolRepositoryImpl(apiIServiceApi)
}