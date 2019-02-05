package di.module

import dagger.Module
import dagger.Provides
import network.ShowsApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object ShowsModule {

    @Provides
    @Singleton
    fun providesRetrofit(retrofit: Retrofit) : ShowsApi {
        return retrofit.create(ShowsApi::class.java)
    }

 }
