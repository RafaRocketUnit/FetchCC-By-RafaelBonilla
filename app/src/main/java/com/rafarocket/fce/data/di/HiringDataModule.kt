package com.rafarocket.fce.data.di

import com.rafarocket.fce.data.HiringRepository
import com.rafarocket.fce.data.HiringRepositoryRetrofitImpl
import com.rafarocket.fce.data.HiringServiceApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Module for Dagger Hilt injection.
 */
@Module
@InstallIn(SingletonComponent::class)
interface HiringDataModule {

    @Binds
    fun HiringRepositoryRetrofitImpl.bindHiringRepository(): HiringRepository

    companion object {

        private const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"

        @Provides
        fun provideRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Provides
        fun providesHiringServiceApi(retrofit: Retrofit): HiringServiceApi {
            return retrofit.create(HiringServiceApi::class.java)
        }
    }
}