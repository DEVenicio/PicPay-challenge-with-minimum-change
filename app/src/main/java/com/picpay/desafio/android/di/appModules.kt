package com.picpay.desafio.android.di

import com.google.gson.GsonBuilder
import com.picpay.desafio.android.PicPayService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import com.picpay.desafio.android.BuildConfig
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single { OkHttpClient.Builder().build() }
    single { GsonBuilder().create() }

    single {
        println("BASE_URL ${BuildConfig.BASE_URL}")
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }


    //Services
    single<PicPayService> { get<Retrofit>().create(PicPayService::class.java) }

}

val appModules = listOf(
    networkModule,
)