package teno.app.loginandregisterandroidstudio.loggedIn

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.annotation.LayoutRes

abstract class BaseActivity:  ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //habilita el ojo de camara
        //enableEdgeToEdge()
        //requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        //se configura para que no se vea la hora, wifi y fecha ne la parte superior del cel
        //window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(getLayout())
    }

    @LayoutRes
    abstract fun getLayout():Int
}