package teno.app.loginandregisterandroidstudio.loggedIn

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.annotation.LayoutRes
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import teno.app.loginandregisterandroidstudio.auth.login.LoginActivity
import teno.app.loginandregisterandroidstudio.common.session.SessionManager
import javax.inject.Inject

@AndroidEntryPoint
abstract class BaseActivity:  ComponentActivity() {

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //habilita el ojo de camara
        //enableEdgeToEdge()
        //requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        //se configura para que no se vea la hora, wifi y fecha ne la parte superior del cel
        //window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(getLayout())
        lifecycleScope.launch {
            sessionManager.sessionExpiredFlow.collect {
                // Redirige al Login en caso de obtener un 401
                startActivity(Intent(this@BaseActivity, LoginActivity::class.java))
            }
        }
    }

    @LayoutRes
    abstract fun getLayout():Int
}