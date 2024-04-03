package inc.fabudi.musicapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import inc.fabudi.musicapp.Utils
import inc.fabudi.musicapp.network.MusicApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideMusicApiService(): MusicApiService =
        getRetrofit(okHttpClient).create(MusicApiService::class.java)

    @Provides
    @Singleton
    fun getRetrofit(okHttpClient: OkHttpClient?): Retrofit {
        return Retrofit.Builder().baseUrl(Utils.environmentVariables["API_ENDPOINT"])
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).client(okHttpClient!!)
            .build()
    }

    @JvmStatic
    @get:Singleton
    @get:Provides
    val okHttpClient: OkHttpClient
        get() = OkHttpClient.Builder().connectTimeout(3000, TimeUnit.MILLISECONDS)
            .readTimeout(200000, TimeUnit.MILLISECONDS).writeTimeout(250000, TimeUnit.MILLISECONDS)
            .addNetworkInterceptor {
                it.proceed(
                    it.request().newBuilder().header(
                        "User-Agent",
                        Utils.environmentVariables["USER-AGENT"]
                    ).build()
                )
            }.addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY
                )
            ).build()
}