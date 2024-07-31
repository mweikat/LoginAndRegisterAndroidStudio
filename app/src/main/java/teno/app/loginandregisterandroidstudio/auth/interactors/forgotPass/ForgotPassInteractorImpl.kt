package teno.app.loginandregisterandroidstudio.auth.interactors.forgotPass

import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call
import teno.app.loginandregisterandroidstudio.auth.interactors.ApiAuthServices
import teno.app.loginandregisterandroidstudio.auth.interactors.forgotPass.reqandres.ForgotRes
import teno.app.loginandregisterandroidstudio.auth.interactors.forgotPass.reqandres.ForgotRq
import java.net.SocketTimeoutException
import javax.inject.Inject

class ForgotPassInteractorImpl @Inject constructor(
    private var apiservice:ApiAuthServices
) : ForgotPassInteractor{

    override fun forgotPassSendEmail(email: String, listener: ForgotPassInteractor.ForgotCallBack) {
        val forgotRq = ForgotRq(email)
        val call = apiservice.postForgetPassEmail(forgotRq)
        call.enqueue(object:Callback<ForgotRes>{

            override fun onResponse(p0: Call<ForgotRes>, response: Response<ForgotRes>) {
                if (response.isSuccessful)
                    listener.onForgotSuccess(response.body()?.message.toString())
                else
                    listener.onForgotFailure(response.body()?.message.toString())
            }

            override fun onFailure(p0: Call<ForgotRes>, t: Throwable) {

                if (t is SocketTimeoutException) {
                    listener.onForgotFailure("No se ha podido conectar con el servidor, intente más tarde")
                }else{
                    listener.onForgotFailure("Ha ocurrido un error, intente más tarde")
                }


            }
        })

    }
}