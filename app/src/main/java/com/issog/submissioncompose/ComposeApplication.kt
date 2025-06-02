package com.issog.submissioncompose

import com.issog.submissioncompose.core.utils.base.BaseApplication
import com.issog.submissioncompose.di.useCaseModule
import com.issog.submissioncompose.di.viewModelModule
import org.koin.core.KoinApplication
import org.koin.core.module.Module

class ComposeApplication: BaseApplication() {
    override fun addModule(koin: KoinApplication, modules: ArrayList<Module>) {
        super.addModule(koin, modules)
        modules.add(useCaseModule)
        modules.add(viewModelModule)
        koin.modules(modules)
    }
}