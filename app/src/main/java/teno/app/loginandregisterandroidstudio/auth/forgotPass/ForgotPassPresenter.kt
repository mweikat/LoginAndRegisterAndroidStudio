package teno.app.loginandregisterandroidstudio.auth.forgotPass

import teno.app.loginandregisterandroidstudio.auth.AuthContract
import teno.app.loginandregisterandroidstudio.auth.interactors.forgotPass.ForgotPassInteractor

class ForgotPassPresenter(forgotInteractor:ForgotPassInteractor):AuthContract.ForgotPassPresenter {

    var view:AuthContract.ForgotPassView? = null
    var forgotInteractor: ForgotPassInteractor? = null

    init{
        this.forgotInteractor = forgotInteractor
    }

    override fun attacheView(view: AuthContract.ForgotPassView) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun isViewAttached(): Boolean {
        return view != null
    }

    override fun sendEmailRecover(email:String) {
        view?.showProgressBar()
        forgotInteractor?.forgotPassSendEmail(email, object: ForgotPassInteractor.ForgotCallBack{
            override fun onForgotSuccess(message:String) {
                if(isViewAttached()){
                    view?.hideProgressBar()
                    view?.showMessageOk(message)
                }
            }

            override fun onForgotFailure(errorMsg: String) {
                if(isViewAttached()){
                    view?.hideProgressBar()
                    view?.showError(errorMsg)
                }
            }

        })
    }

    override fun checkEmail(email:String):Boolean {
        return email.isEmpty()
    }
}