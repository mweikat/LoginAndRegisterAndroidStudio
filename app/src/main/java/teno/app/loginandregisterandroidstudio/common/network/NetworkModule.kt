package teno.app.loginandregisterandroidstudio.common.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import teno.app.loginandregisterandroidstudio.BuildConfig
import teno.app.loginandregisterandroidstudio.auth.interactors.ApiAuthServices
import teno.app.loginandregisterandroidstudio.common.session.SessionManager
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL:String = BuildConfig.API_BASE_URL;

    @Provides
    @Singleton
    fun provideOkHttpClient(sessionManager: SessionManager): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                val url: String = chain.request().url().toString()
                val jwt = runBlocking { sessionManager.getJwt() }
                if (!jwt.isNullOrEmpty() && jwt!="null" && !url.contains("auth")) {
                    requestBuilder.addHeader("Authorization", "Bearer $jwt")
                }
                val response = chain.proceed(requestBuilder.build())
                if (response.code() == 401) {
                    runBlocking { sessionManager.notifySessionExpired() }
                }
                response
            }
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient):Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesApiAuthServices(retrofit: Retrofit):ApiAuthServices{
        return retrofit.create(ApiAuthServices::class.java)
    }
}