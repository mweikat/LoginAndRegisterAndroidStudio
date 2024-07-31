package teno.app.loginandregisterandroidstudio.auth.forgotPass

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import teno.app.loginandregisterandroidstudio.R
import teno.app.loginandregisterandroidstudio.auth.AuthContract
import teno.app.loginandregisterandroidstudio.auth.BaseAuthActivity
import teno.app.loginandregisterandroidstudio.common.utils.Functions
import java.util.regex.Pattern
import javax.inject.Inject

@AndroidEntryPoint
class ForgotPassActivity : BaseAuthActivity(), AuthContract.ForgotPassView {

    @Inject
    lateinit var presenter: ForgotPassPresenter

    @Inject
    lateinit var functions: Functions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (findViewById<Button>(teno.app.loginandregisterandroidstudio.R.id.btnForgot)).setOnClickListener{
            sendEmailRecover()
        }
        //presenter
        presenter.attacheView(this)

        val myToolbar = findViewById<MaterialToolbar>(R.id.toolBarForgot)
        myToolbar.setNavigationOnClickListener {
            finish()
        }

        // Configurar el ícono de navegación como botón de retroceso
        myToolbar.setNavigationOnClickListener {
            onBackPressed() // Simula la acción de retroceso
        }
    }

    override fun getLayout(): Int {
        return R.layout.activity_forgot_pass
    }

    override fun showError(msgError: String) {
        functions.msgSnackBar(0,msgError,findViewById<View>(android.R.id.content))
    }

    override fun showProgressBar() {
        (findViewById<ProgressBar>(R.id.progressBarForgot)).visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        (findViewById<ProgressBar>(R.id.progressBarForgot)).visibility = View.GONE
    }

    override fun sendEmailRecover() {

        val email:String = (findViewById<TextInputLayout>(R.id.emailFogot)).editText?.text.toString().trim()
        if(presenter.checkEmail(email)) {
            functions.msgSnackBar(R.string.forgotMsg1,"", findViewById<View>(android.R.id.content))
        }else {
            if(!functions.validEmail(email))
                functions.msgSnackBar(R.string.logInMsg2,"",findViewById<View>(android.R.id.content))
            //Toast.makeText(this, "Debe ingresar un email válido",Toast.LENGTH_LONG).show()
            else
                presenter.sendEmailRecover(email)
        }
    }

    override fun showMessageOk(message:String) {
        (findViewById<TextInputLayout>(R.id.emailFogot)).isHintEnabled = false
        (findViewById<TextInputLayout>(R.id.emailFogot)).isEnabled = false
        (findViewById<ExtendedFloatingActionButton>(R.id.btnForgot)).isEnabled = false
        (findViewById<TextView>(R.id.textViewMsgSucess)).text = message
        (findViewById<TextView>(R.id.textViewMsgSucess)).visibility = View.VISIBLE
    }


}