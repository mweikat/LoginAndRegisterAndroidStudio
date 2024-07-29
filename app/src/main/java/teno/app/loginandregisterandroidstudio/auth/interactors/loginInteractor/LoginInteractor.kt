package teno.app.loginandregisterandroidstudio.auth.interactors.loginInteractor

interface LoginInteractor {

    interface LoginCallBack{
        fun onLoginSuccess()
        fun onLoginFailure(errorMsg:String)
    }

    fun loginWhitEmailAndPassword(email:String, password:String, listener: LoginCallBack)
}