package teno.app.loginandregisterandroidstudio.common.session

import teno.app.loginandregisterandroidstudio.loggedIn.data.User

interface UserLogged {

    suspend fun getUser():User?
    suspend fun setUser(user:User)
    suspend fun delUser()
}