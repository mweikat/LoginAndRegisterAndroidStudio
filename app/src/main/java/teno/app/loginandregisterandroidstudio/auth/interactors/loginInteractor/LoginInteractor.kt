package teno.app.loginandregisterandroidstudio.auth.interactors.loginInteractor

import teno.app.loginandregisterandroidstudio.auth.interactors.loginInteractor.reqandres.LoginResponse

interface LoginInteractor {

    interface LoginCallBack{
        fun onLoginSuccess(loginResponse: LoginResponse?)
        fun onLoginFailure(errorMsg:String)
    }

    fun loginWhitEmailAndPassword(email:String, password:String, listener: LoginCallBack)
}