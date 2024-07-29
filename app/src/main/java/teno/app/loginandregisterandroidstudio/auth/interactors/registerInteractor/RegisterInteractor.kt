package teno.app.loginandregisterandroidstudio.auth.interactors.registerInteractor

interface RegisterInteractor {

    interface RegisterCallBack{
        fun onRegisterSuccess()
        fun onRegisterFailure(errorMsg:String)
    }

    fun registerWhitEmailAndPass(email:String, password:String, name:String, lastName:String, listener: RegisterCallBack)
}