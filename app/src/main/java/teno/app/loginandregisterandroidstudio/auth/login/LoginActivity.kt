package teno.app.loginandregisterandroidstudio.auth.login

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import teno.app.loginandregisterandroidstudio.R
import teno.app.loginandregisterandroidstudio.auth.AuthContract
import teno.app.loginandregisterandroidstudio.auth.BaseAuthActivity
import teno.app.loginandregisterandroidstudio.auth.forgotPass.ForgotPassActivity
import teno.app.loginandregisterandroidstudio.auth.interactors.loginInteractor.LoginInteractorImpl
import teno.app.loginandregisterandroidstudio.auth.register.RegisterActivity
import teno.app.loginandregisterandroidstudio.ready.main.MainActivity
import java.util.regex.Pattern

class LoginActivity : BaseAuthActivity() , AuthContract.LoginView {

    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (findViewById<Button>(teno.app.loginandregisterandroidstudio.R.id.btnLogin)).setOnClickListener{
            sigIn()
        }

        //presenter
        presenter = LoginPresenter(LoginInteractorImpl())
        presenter.attacheView(this)

        //register button
        val textRegister = findViewById<Button>(R.id.register)
        textRegister.text = Html.fromHtml(getString(R.string.logInRegisterText),Html.FROM_HTML_MODE_COMPACT)
        textRegister.setOnClickListener{
            navigateToRegister()
        }

        val textForgotPass = findViewById<Button>(R.id.forgotBtn)
        textForgotPass.text = Html.fromHtml(getString(R.string.logInForgot),Html.FROM_HTML_MODE_COMPACT)
        textForgotPass.setOnClickListener{
            navigateToForgotPass()
        }

    }

    override fun getLayout(): Int {
        return R.layout.activity_login
    }

    override fun showError(msgError: String) {
        msgSnackBar(0,msgError)
        //Toast.makeText(this, msgError, Toast.LENGTH_SHORT).show()
    }

    override fun showProgressBar() {
        (findViewById<ProgressBar>(R.id.progressBarLogin)).visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        (findViewById<ProgressBar>(R.id.progressBarLogin)).visibility = View.GONE
    }

    override fun sigIn() {
        //val btnEmail = findViewById<Button>(R.id.editTextText_login)
        // val btnPass = findViewById<Button>(R.id.editTextTextPassword_login)
        //Toast.makeText(this, "Boton prueba", Toast.LENGTH_SHORT).show()
        val email:String = (findViewById<TextInputLayout>(R.id.email)).editText?.text.toString().trim()
        val pass:String = (findViewById<TextInputLayout>(R.id.pass)).editText?.text.toString().trim()

        if(presenter.checkEmptyFields(email,pass))
            msgSnackBar(R.string.logInMsg1,"")
            //val contextView = findViewById<View>(R.id.main)
            //Snackbar.make(contextView, R.string.logInMsg1, Snackbar.LENGTH_SHORT).show()

            //Toast.makeText(this, "Favor Ingrese su Email y Contraseña", Toast.LENGTH_SHORT).show()
        else {
            if(!validEmail(email))
                msgSnackBar(R.string.logInMsg2,"")
                //Toast.makeText(this, "Debe ingresar un email válido",Toast.LENGTH_LONG).show()
            else
                presenter.loginEmailAndPassword(email, pass)
        }
    }

    override fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun navigateToRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    override fun navigateToForgotPass() {
        startActivity(Intent(this, ForgotPassActivity::class.java))
    }

    private fun validEmail(email: String): Boolean {

        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        val result:Boolean = pattern.matcher(email).matches()
        return result
    }

    private fun msgSnackBar(msg:Int, msgText:String){

        val contextView = findViewById<View>(R.id.main)
        if(msg==0)
            Snackbar.make(contextView, msgText, Snackbar.LENGTH_SHORT).setAction(R.string.logInSnackText) {
                // Responds to click on the action
            }.show()
         else
            Snackbar.make(contextView, msg, Snackbar.LENGTH_SHORT).setAction(R.string.logInSnackText) {
                // Responds to click on the action
            }.show()
    }
}