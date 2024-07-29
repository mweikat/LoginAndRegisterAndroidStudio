package teno.app.loginandregisterandroidstudio.auth.interactors.forgotPass

interface ForgotPassInteractor {

    interface ForgotCallBack{
        fun onForgotSuccess(message:String)
        fun onForgotFailure(errorMsg:String)
    }

    fun forgotPassSendEmail(email:String, listener: ForgotCallBack)
}