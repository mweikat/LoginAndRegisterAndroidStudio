package teno.app.loginandregisterandroidstudio.loggedIn.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import teno.app.loginandregisterandroidstudio.R
import teno.app.loginandregisterandroidstudio.loggedIn.BaseActivity
import teno.app.loginandregisterandroidstudio.loggedIn.profile.ProfileActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigateToProfile()
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    private fun navigateToProfile() {
        startActivity(Intent(this, ProfileActivity::class.java))
    }
}