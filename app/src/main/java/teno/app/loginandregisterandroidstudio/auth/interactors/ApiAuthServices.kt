package teno.app.loginandregisterandroidstudio.auth.interactors

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import teno.app.loginandregisterandroidstudio.BuildConfig
import teno.app.loginandregisterandroidstudio.auth.interactors.forgotPass.reqandres.ForgotRes
import teno.app.loginandregisterandroidstudio.auth.interactors.forgotPass.reqandres.ForgotRq
import teno.app.loginandregisterandroidstudio.auth.interactors.loginInteractor.reqandres.LoginResponse

import teno.app.loginandregisterandroidstudio.auth.interactors.loginInteractor.reqandres.LoginRequest
import teno.app.loginandregisterandroidstudio.auth.interactors.registerInteractor.reqandres.RegisterRequest
import teno.app.loginandregisterandroidstudio.auth.interactors.registerInteractor.reqandres.RegisterResponse

interface ApiAuthServices {

    @POST(value = "/api/v1/auth/login")
    fun postLogin(@Body request: LoginRequest):
            Call<LoginResponse>

    @POST(value = "/api/v1/auth/register")
    fun postRegister(@Body request: RegisterRequest):
            Call<RegisterResponse>

    @POST(value="/api/v1/auth/reset_pass/mail")
    fun postForgetPassEmail(@Body request: ForgotRq):
            Call<ForgotRes>

    /*companion object Factory{
        private const val BASE_URL:String = BuildConfig.API_BASE_URL;

        fun create(): ApiAuthServices {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiAuthServices::class.java)
        }
    }*/
}