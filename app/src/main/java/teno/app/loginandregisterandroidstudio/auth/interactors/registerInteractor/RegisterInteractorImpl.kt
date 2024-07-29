package teno.app.loginandregisterandroidstudio.auth.interactors.registerInteractor

import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import teno.app.loginandregisterandroidstudio.auth.interactors.ApiAuthServices
import teno.app.loginandregisterandroidstudio.auth.interactors.registerInteractor.reqandres.RegisterRequest
import teno.app.loginandregisterandroidstudio.auth.interactors.registerInteractor.reqandres.RegisterResponse

class RegisterInteractorImpl:RegisterInteractor {

    private val apiServiceReg: ApiAuthServices by lazy{
        ApiAuthServices.create()
    }

    override fun registerWhitEmailAndPass( email: String, password: String, name:String, lastName:String, listener: RegisterInteractor.RegisterCallBack) {
        val registerRq = RegisterRequest(email,password, name, lastName)
        val call = apiServiceReg.postRegister(registerRq)
        call.enqueue(object : Callback<RegisterResponse>{
            override  fun onResponse(call: Call<RegisterResponse>, response:Response<RegisterResponse>){
                if (response.isSuccessful)
                    listener.onRegisterSuccess()
                else {
                    //listener.onRegisterFailure(response.body()?.message.toString())
                    //listener.onRegisterFailure(response.errorBody().toString())
                    val errorBodyString = response.errorBody()?.string()
                    val jsonObject = JSONObject(errorBodyString?.toString()?.trim())
                    //val errorObject = jsonObject.getJSONObject("error")
                    val errorMessage = jsonObject.getString("message")
                    listener.onRegisterFailure(errorMessage)
                }
            }
            override fun onFailure(p0: Call<RegisterResponse>, p1: Throwable) {
                listener.onRegisterFailure(p1.message.toString())
            }
        })
    }


}