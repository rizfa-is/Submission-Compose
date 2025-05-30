package com.issog.submissioncompose.core.utils.base

import android.app.Application
import com.issog.submissioncompose.core.di.databaseModule
import com.issog.submissioncompose.core.di.networkModule
import com.issog.submissioncompose.core.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module

abstract class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@BaseApplication)
            addModule(
                this,
                arrayListOf(databaseModule, networkModule, repositoryModule)
            )
        }
    }

    open fun addModule(koin: KoinApplication, modules: ArrayList<Module>) {}
}