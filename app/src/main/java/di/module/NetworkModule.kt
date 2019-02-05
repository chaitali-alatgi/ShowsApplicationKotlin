package di.module

import com.example.chaitali.showsapplicationkotlin.ShowsApplication
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import utils.BASE_URL
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @Singleton
    internal fun provideLoggingInterceptor() : HttpLoggingInterceptor{
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Provides
    @Singleton
    internal fun providesCache(application : ShowsApplication) : Cache {
        val cacheSize = 15 * 1024 * 1024   // 15 MB
        return Cache(application.getCacheDir(), cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient) : Retrofit{
        val builder : Retrofit.Builder =  Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(CustomGSONConverter())
                .addConverterFactory(GsonConverterFactory.create())
        return  builder.build()
    }

    @Provides
    @Singleton
    internal fun provideCacheOverrideInterceptor(): Interceptor {
        return Interceptor { chain ->
            val maxStale = 60 * 60 * 24 * 7 // tolerate 1-week stale
            chain.proceed(chain.request())
                    .newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-age=" + maxStale)
                    .build()
        }
    }

    @Provides
    @Singleton
    internal fun providesOkHttpClient(cache: Cache, loggingInterceptor: HttpLoggingInterceptor, networkInterceptor: Interceptor) : OkHttpClient {
        val client : OkHttpClient.Builder = OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(networkInterceptor)
        return client.build()
    }
}