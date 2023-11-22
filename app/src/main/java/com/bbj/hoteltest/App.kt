package com.bbj.hoteltest

import android.app.Application
import com.bbj.hoteltest.di.appModule
import com.bbj.hoteltest.di.dataModule
import com.bbj.hoteltest.di.databaseModule
import com.bbj.hoteltest.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(networkModule, dataModule, appModule,databaseModule)
        }
    }

}