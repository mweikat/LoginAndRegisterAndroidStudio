package teno.app.loginandregisterandroidstudio.auth.forgotPass

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import teno.app.loginandregisterandroidstudio.R
import teno.app.loginandregisterandroidstudio.auth.AuthContract
import teno.app.loginandregisterandroidstudio.auth.BaseAuthActivity
import teno.app.loginandregisterandroidstudio.auth.interactors.forgotPass.ForgotPassInteractorImpl
import java.util.regex.Pattern

class ForgotPassActivity : BaseAuthActivity(), AuthContract.ForgotPassView {

    private lateinit var presenter: ForgotPassPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (findViewById<Button>(teno.app.loginandregisterandroidstudio.R.id.btnForgot)).setOnClickListener{
            sendEmailRecover()
        }
        //presenter
        presenter = ForgotPassPresenter(ForgotPassInteractorImpl())
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
        msgSnackBar(0,msgError)
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
            msgSnackBar(R.string.forgotMsg1,"")
        }else {
            if(!validEmail(email))
                msgSnackBar(R.string.logInMsg2,"")
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