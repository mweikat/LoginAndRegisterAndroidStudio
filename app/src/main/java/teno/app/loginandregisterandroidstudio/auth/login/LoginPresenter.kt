package teno.app.loginandregisterandroidstudio.auth.login

import teno.app.loginandregisterandroidstudio.auth.AuthContract
import teno.app.loginandregisterandroidstudio.auth.interactors.loginInteractor.LoginInteractor
import javax.inject.Inject

class LoginPresenter @Inject constructor (private var loginInteractor: LoginInteractor) : AuthContract.LoginPresenter {

    var view:AuthContract.LoginView? = null

    override fun attacheView(view: AuthContract.LoginView) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun isViewAttached(): Boolean {
        return view != null
    }

    override fun loginEmailAndPassword(email: String, password: String) {
        view?.showProgressBar()
        loginInteractor.loginWhitEmailAndPassword(email,password,object: LoginInteractor.LoginCallBack{
            override fun onLoginSuccess() {
                if(isViewAttached()){
                    view?.hideProgressBar()
                    view?.navigateToMain()
                }
            }

            override fun onLoginFailure(errorMsg: String) {

                if(isViewAttached()){
                    view?.hideProgressBar()
                    view?.showError(errorMsg)
                }
            }

        })
    }

    override fun checkEmptyFields(email: String, password: String):Boolean {
        return email.isEmpty() || password.isEmpty()
    }
}