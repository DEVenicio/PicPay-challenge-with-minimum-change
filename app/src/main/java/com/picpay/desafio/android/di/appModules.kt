package com.picpay.desafio.android.di

import com.google.gson.GsonBuilder
import com.picpay.desafio.android.BuildConfig
import com.picpay.desafio.android.data.repository.UserRepositoryImp
import com.picpay.desafio.android.data.services.PicPayService
import com.picpay.desafio.android.data.usecases.UserUseCaseImpl
import com.picpay.desafio.android.domain.repositories.UserRepository
import com.picpay.desafio.android.domain.usecase.UserUseCase
import com.picpay.desafio.android.presentation.viewmodels.UserViewModel
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val networkModule = module {

    single { OkHttpClient.Builder().build() }
    single { GsonBuilder().create() }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }


    //Services
    single<PicPayService> { get<Retrofit>().create(PicPayService::class.java) }

}

val repositoriesModule = module {
    single<UserRepository> { UserRepositoryImp(get()) }
}

val viewModelModule = module {
    viewModel<UserViewModel> { UserViewModel(get()) }
}

val useCaseModule = module {
    factory<UserUseCase> { UserUseCaseImpl(get()) }
}


val  appModules = listOf(
    networkModule,
    repositoriesModule,
    viewModelModule,
    useCaseModule
)