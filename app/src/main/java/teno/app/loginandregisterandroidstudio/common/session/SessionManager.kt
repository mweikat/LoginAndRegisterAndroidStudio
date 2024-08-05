package teno.app.loginandregisterandroidstudio.common.session

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.runBlocking
import teno.app.loginandregisterandroidstudio.common.jwt.JwtTokenManager
import teno.app.loginandregisterandroidstudio.loggedIn.data.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(
    private val jwtTokenManager: JwtTokenManager,
    private val userLogged: UserLogged
) {

    private val _sessionExpiredFlow = MutableSharedFlow<Unit>(extraBufferCapacity = 1)
    val sessionExpiredFlow = _sessionExpiredFlow.asSharedFlow()

    suspend fun notifySessionExpired() {
        jwtTokenManager.clearAllTokens()
        _sessionExpiredFlow.emit(Unit) //notificamos el cierre de session por 401
    }

    suspend fun logout() {
        jwtTokenManager.clearAllTokens()
        _sessionExpiredFlow.emit(Unit) //notificamos el cierre de session manual
    }

    fun getJwt(): String = runBlocking {
        jwtTokenManager.getAccessJwt().toString()
    }

    suspend fun setUser(user:User){
        userLogged.setUser(user)
    }

    suspend fun getUser():User?{
        return userLogged.getUser()
    }

    suspend fun delUser(){
        userLogged.delUser()
    }
}