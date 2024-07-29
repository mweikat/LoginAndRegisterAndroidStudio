package teno.app.loginandregisterandroidstudio.auth.register

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.Button
import teno.app.loginandregisterandroidstudio.R
import teno.app.loginandregisterandroidstudio.auth.BaseAuthActivity
import teno.app.loginandregisterandroidstudio.auth.login.LoginActivity

class ResultRegisterOKActivity : BaseAuthActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val volverText = findViewById<Button>(R.id.btnLogin)
        volverText.text = Html.fromHtml("<u>Inicie Sesión</u>", Html.FROM_HTML_MODE_COMPACT)
        volverText.setOnClickListener{
            navigateToLogin()
        }

    }

    override fun getLayout(): Int {
        return R.layout.activity_result_register_okactivity
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}