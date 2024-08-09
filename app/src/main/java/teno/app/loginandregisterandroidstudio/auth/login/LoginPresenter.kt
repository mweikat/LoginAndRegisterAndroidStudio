package teno.app.loginandregisterandroidstudio.auth.login

import dagger.hilt.InstallIn
import kotlinx.coroutines.runBlocking
import teno.app.loginandregisterandroidstudio.auth.AuthContract
import teno.app.loginandregisterandroidstudio.auth.interactors.loginInteractor.LoginInteractor
import teno.app.loginandregisterandroidstudio.auth.interactors.loginInteractor.reqandres.LoginResponse
import teno.app.loginandregisterandroidstudio.common.jwt.JwtTokenManager
import teno.app.loginandregisterandroidstudio.common.session.SessionManager
import teno.app.loginandregisterandroidstudio.loggedIn.data.User
import javax.inject.Inject

class LoginPresenter @Inject constructor (private var loginInteractor: LoginInteractor) : AuthContract.LoginPresenter {

    var view:AuthContract.LoginView? = null
    @Inject lateinit var jwTokenManager: JwtTokenManager
    @Inject lateinit var sessionManager: SessionManager

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
        view?.navigateToMain()
        view?.showProgressBar()
        loginInteractor.loginWhitEmailAndPassword(email,password,object: LoginInteractor.LoginCallBack{
            override fun onLoginSuccess(loginResponse: LoginResponse?) {
                if(isViewAttached()){
                    if (loginResponse != null) {
                        runBlocking{
                            jwTokenManager.saveAccessJwt(loginResponse.token)
                            sessionManager.setUser(User(loginResponse.name,loginResponse.lastName, loginResponse.email))
                        }
                    }
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