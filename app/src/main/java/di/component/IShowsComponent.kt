package di.component

import com.example.chaitali.showsapplicationkotlin.MainActivity
import dagger.Component
import dagger.Subcomponent
import di.module.ShowsModule
import javax.inject.Scope
import javax.inject.Singleton


@Subcomponent(modules = arrayOf(ShowsModule::class))
interface IShowsComponent {
    fun inject(activity: MainActivity)
}