package teno.app.loginandregisterandroidstudio.common.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import teno.app.loginandregisterandroidstudio.BuildConfig
import teno.app.loginandregisterandroidstudio.auth.interactors.ApiAuthServices
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL:String = BuildConfig.API_BASE_URL;

    @Provides
    @Singleton
    fun providesRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesApiAuthServices(retrofit: Retrofit):ApiAuthServices{
        return retrofit.create(ApiAuthServices::class.java)
    }
}