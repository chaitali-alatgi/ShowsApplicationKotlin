package di.module

import android.content.Context
import com.example.chaitali.showsapplicationkotlin.ShowsApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application : ShowsApplication) {

    @Provides
    @Singleton
    fun providesApplication() : ShowsApplication{
        return application
    }

    @Provides
    @Singleton
    fun providesContext() : Context {
        return application.applicationContext
    }

}