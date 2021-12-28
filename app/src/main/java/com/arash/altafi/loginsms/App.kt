package com.arash.altafi.loginsms

import android.app.Application
import android.util.Log
import com.arash.altafi.loginsms.kotlin.KotlinViewModel
import com.arash.altafi.loginsms.kotlin.api.ApiService
import com.arash.altafi.loginsms.kotlin.api.retrofitApi
import com.arash.altafi.loginsms.kotlin.repositories.AuthRepository
import com.arash.altafi.loginsms.kotlin.repositories.AuthRepositoryImpl
import com.arash.altafi.loginsms.kotlin.sources.RemoteAuthDataSource
import com.arash.altafi.loginsms.kotlin.utils.AppSignatureHashHelper
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        val appSignatureHashHelper = AppSignatureHashHelper(this)
        Log.e("ArashAltafiSample", "code: ${appSignatureHashHelper.appSignatures[0]}")

        val myModule = module {

            //Api
            single<ApiService> { retrofitApi() }

            //Repository
            factory<AuthRepository> { AuthRepositoryImpl(RemoteAuthDataSource(get())) }

            //ViewModels
            viewModel { KotlinViewModel(get()) }

        }

        startKoin {
            androidContext(this@App)
            modules(myModule)
        }

    }

}