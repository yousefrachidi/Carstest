package com.youssefra.renault.di

import android.content.Context
import com.youssefra.renault.BuildConfig
import com.youssefra.renault.data.remote.services.CarApiService
import com.youssefra.renault.data.repository.CarRepository
import com.youssefra.renault.views.car.CarViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(@ApplicationContext context: Context): Retrofit {
        var okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
        val okhttpClient = okHttpClientBuilder.build()

        return Retrofit.Builder()
            .client(okhttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCarApi(retrofit: Retrofit): CarApiService {
        return retrofit.create(CarApiService::class.java)
    }

//    @Provides
//    @Singleton
//    fun provideCarApiService(retrofit: Retrofit): CarApiService {
//        return retrofit.create(CarApiService::class.java)
//    }

    @Provides
    @Singleton
    fun provideCarRepository(carApiService: CarApiService): CarRepository {
        return CarRepository(carApiService)
    }

    @Provides
    @Singleton
    fun provideCarViewModel(repository: CarRepository): CarViewModel {
        return CarViewModel(repository)
    }


}