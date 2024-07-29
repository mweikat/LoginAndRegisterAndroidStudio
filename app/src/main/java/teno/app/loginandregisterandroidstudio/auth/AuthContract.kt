package teno.app.loginandregisterandroidstudio.auth

interface AuthContract {

    interface LoginView {
        fun showError(msgError:String)
        fun showProgressBar()
        fun hideProgressBar()
        fun sigIn()
        fun navigateToMain()
        fun navigateToRegister()
        fun navigateToForgotPass()
    }

    interface LoginPresenter{
        fun attacheView(view:LoginView)
        fun detachView()
        fun isViewAttached() : Boolean
        fun loginEmailAndPassword(email:String, password:String)
        fun checkEmptyFields(email:String, password:String):Boolean
    }

    interface RegisterView {
        fun showError(msgError:String)
        fun showProgressBar()
        fun hideProgressBar()
        fun regIn()
        fun navigateToLogin()
        fun navigateSuccessReg()
    }

    interface RegisterPresenter {
        fun attacheView(view:RegisterView)
        fun detachView()
        fun isViewAttached() : Boolean
        fun registerEmailAndPassword(email:String, password:String, name:String, lastName:String)
        fun checkEmptyFields(email:String, password:String, password2: String, name:String, lastName:String):Boolean
    }

    interface ForgotPassView{
        fun showError(msgError: String)
        fun showProgressBar()
        fun hideProgressBar()
        fun sendEmailRecover()
        fun showMessageOk(message:String)
    }

    interface ForgotPassPresenter{
        fun attacheView(view:ForgotPassView)
        fun detachView()
        fun isViewAttached() : Boolean
        fun sendEmailRecover(email:String)
        fun checkEmail(email:String):Boolean
    }
}