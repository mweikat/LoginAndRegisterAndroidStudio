package teno.app.loginandregisterandroidstudio.auth.interactors.loginInteractor

import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call
import teno.app.loginandregisterandroidstudio.auth.interactors.ApiAuthServices
import teno.app.loginandregisterandroidstudio.auth.interactors.loginInteractor.reqandres.LoginRequest
import teno.app.loginandregisterandroidstudio.auth.interactors.loginInteractor.reqandres.LoginResponse
import java.net.SocketTimeoutException
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class LoginInteractorImpl @Inject constructor(
    var apiservice : ApiAuthServices
): LoginInteractor {

    override fun loginWhitEmailAndPassword(email: String, password: String, listener: LoginInteractor.LoginCallBack) {
        val loginRequest = LoginRequest(email, password)
        var call = apiservice.postLogin(loginRequest)
        call.enqueue(object: Callback<LoginResponse> {
            override fun onResponse(call : Call<LoginResponse>, response:Response<LoginResponse>){
                if (response.isSuccessful){
                    listener.onLoginSuccess(response.body())
                }else{
                    listener.onLoginFailure(response.body()?.message.toString())
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                var message:String = ""
                if (t is SocketTimeoutException) {
                    message = "No se ha podido conectar con el servidor, intente más tarde"
                }else{
                    message = "Ha ocurrido un error, intente más tarde"
                }
                //mensaje a un log
                // t.message.toString()
                listener.onLoginFailure(message.toString())
            }
        })
    }

}