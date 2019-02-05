package di.component

import com.example.chaitali.showsapplicationkotlin.ShowsApplication
import dagger.Component
import di.module.NetworkModule
import di.module.AppModule
import di.module.ShowsModule
import javax.inject.Singleton


@Singleton
@Component(modules = [(AppModule:: class), (NetworkModule::class)])
interface IAppComponent {
    fun inject(application: ShowsApplication)
}