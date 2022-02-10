package com.dyh.sample

import android.app.Application
import androidx.multidex.MultiDex
import com.dyh.sample.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)

        startKoin {
//            androidLogger(Level.DEBUG)
            androidContext(this@MainApplication)

            modules(
                listOf(
                    viewModelModule,
                    AppModule,
                    NetworkModule
                )
            )
        }

    }
}