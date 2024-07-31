package teno.app.loginandregisterandroidstudio.auth.register

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import teno.app.loginandregisterandroidstudio.R
import teno.app.loginandregisterandroidstudio.auth.AuthContract
import teno.app.loginandregisterandroidstudio.auth.BaseAuthActivity
import teno.app.loginandregisterandroidstudio.common.utils.Functions
import javax.inject.Inject

@AndroidEntryPoint
class RegisterActivity : BaseAuthActivity(), AuthContract.RegisterView {

    @Inject
    lateinit var presenter: RegisterPresenter

    @Inject
    lateinit var functions: Functions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/

        val volverText = findViewById<Button>(R.id.btnLogin)
        volverText.text = Html.fromHtml("<u>Inicie Sesión</u>", Html.FROM_HTML_MODE_COMPACT)
        volverText.setOnClickListener{
            navigateToLogin()
        }
        //presenter
        presenter.attacheView(this)

        (findViewById<Button>(R.id.btnRegister)).setOnClickListener{
            regIn()
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_register
    }

    override fun showError(msgError: String) {
        functions.msgSnackBar(0,msgError,findViewById<View>(android.R.id.content))
    }

    override fun showProgressBar() {
        (findViewById<ProgressBar>(R.id.progressBarRegister)).visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        (findViewById<ProgressBar>(R.id.progressBarRegister)).visibility = View.GONE
    }

    override fun regIn() {
        val email:String = (findViewById<TextInputLayout>(R.id.emailregister)).editText?.text.toString().trim()
        val pass:String = (findViewById<TextInputLayout>(R.id.pass1)).editText?.text.toString().trim()
        val pass2:String = (findViewById<TextInputLayout>(R.id.pass2)).editText?.text.toString().trim()
        val name:String = (findViewById<TextInputLayout>(R.id.name)).editText?.text.toString().trim()
        val lastName:String = (findViewById<TextInputLayout>(R.id.lastName)).editText?.text.toString().trim()

        if(presenter.checkEmptyFields(email,pass,pass2, name, lastName))
            functions.msgSnackBar(R.string.regInMsg1,"",findViewById<View>(android.R.id.content))
        else{
            if(!functions.validEmail(email))
                functions.msgSnackBar(R.string.logInMsg2,"",findViewById<View>(android.R.id.content))
            else if(pass != pass2)
                functions.msgSnackBar(R.string.regInMsg2,"",findViewById<View>(android.R.id.content))
            else
                presenter.registerEmailAndPassword(email, pass, name, lastName)
        }

    }

    override fun navigateToLogin() {
        finish()
    }

    override fun navigateSuccessReg() {
        startActivity(Intent(this, ResultRegisterOKActivity::class.java))
    }

}