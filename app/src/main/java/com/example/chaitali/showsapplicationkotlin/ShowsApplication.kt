package com.example.chaitali.showsapplicationkotlin

import android.app.Application
import di.component.DaggerIAppComponent
import di.component.IAppComponent
import di.module.AppModule
import di.module.NetworkModule

class ShowsApplication: Application() {
    companion object {
        lateinit var appComponent: IAppComponent
    }

    override fun onCreate() {
        super.onCreate()
       initializeDependencyInjector()
    }

    fun initializeDependencyInjector() {
        appComponent = DaggerIAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule)
                .build()
        appComponent.inject(this)
    }
}