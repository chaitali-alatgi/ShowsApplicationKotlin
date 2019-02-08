package com.example.chaitali.showsapplicationkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import di.module.ShowsModule
import network.ShowsApi
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var showsApi: ShowsApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeDependencyInjector()
        showsApi.getShows()

    }

    fun initializeDependencyInjector() {
        ShowsApplication.appComponent.inject(ShowsModule)
    }
}
