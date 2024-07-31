package teno.app.loginandregisterandroidstudio.loggedIn.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import teno.app.loginandregisterandroidstudio.R
import teno.app.loginandregisterandroidstudio.loggedIn.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }
}