package teno.app.loginandregisterandroidstudio.auth.register

import teno.app.loginandregisterandroidstudio.auth.AuthContract
import teno.app.loginandregisterandroidstudio.auth.interactors.registerInteractor.RegisterInteractor
import javax.inject.Inject

class RegisterPresenter @Inject constructor(private var registerInteractor: RegisterInteractor):AuthContract.RegisterPresenter {

    var view:AuthContract.RegisterView? = null

    override fun attacheView(view: AuthContract.RegisterView) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun isViewAttached(): Boolean {
        return view != null
    }

    override fun registerEmailAndPassword(email: String, password: String, name: String, lastName: String) {
        view?.showProgressBar()
        registerInteractor.registerWhitEmailAndPass(email,password,name,lastName, object:RegisterInteractor.RegisterCallBack{
            override fun onRegisterSuccess() {
                if(isViewAttached()){
                    view?.hideProgressBar()
                    view?.navigateSuccessReg()
                }
            }

            override fun onRegisterFailure(errorMsg: String) {
                if(isViewAttached()){
                    view?.hideProgressBar()
                    view?.showError(errorMsg)
                }
            }

        })
    }

    override fun checkEmptyFields(email: String, password: String, password2: String, name:String, lastName:String): Boolean {
        return email.isEmpty() || password.isEmpty() || password2.isEmpty() || name.isEmpty() || lastName.isEmpty()
    }
}