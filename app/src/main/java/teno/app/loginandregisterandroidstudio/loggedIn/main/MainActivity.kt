package teno.app.loginandregisterandroidstudio.loggedIn.main

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.MaterialToolbar
import teno.app.loginandregisterandroidstudio.R
import teno.app.loginandregisterandroidstudio.loggedIn.BaseActivity
import teno.app.loginandregisterandroidstudio.loggedIn.profile.ProfileActivity

class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayoutMain)
        var materialToolbar = findViewById<MaterialToolbar>(R.id.topAppBarMain)

        val actionBarDrawerToggle = ActionBarDrawerToggle(this,drawerLayout, materialToolbar,R.string.loggedMainOpen,R.string.loggedMainClose)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        //navigateToProfile()
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    private fun navigateToProfile() {
        startActivity(Intent(this, ProfileActivity::class.java))
    }
}