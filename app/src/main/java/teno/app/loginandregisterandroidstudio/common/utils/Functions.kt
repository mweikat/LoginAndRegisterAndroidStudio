package teno.app.loginandregisterandroidstudio.common.utils

import android.util.Patterns
import android.view.View
import com.google.android.material.snackbar.Snackbar
import teno.app.loginandregisterandroidstudio.R
import java.util.regex.Pattern
import javax.inject.Inject

class Functions @Inject constructor(){

    fun validEmail(email: String): Boolean {

        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        val result:Boolean = pattern.matcher(email).matches()
        return result
    }

    fun msgSnackBar(msg:Int, msgText:String, contextView:View){

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