package br.com.marcottc.dailypulse.android

import android.app.Application
import br.com.marcottc.dailypulse.android.di.viewModelsModule
import br.com.marcottc.dailypulse.di.sharedKoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DailyPulseApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        val allModules = sharedKoinModules + viewModelsModule

        startKoin {
            androidContext(this@DailyPulseApplication)
            modules(allModules)
        }
    }
}