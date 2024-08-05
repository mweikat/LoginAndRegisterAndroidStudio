package teno.app.loginandregisterandroidstudio.common.jwt

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

import javax.inject.Inject

class JwtTokenManagerImpl@Inject constructor(private val dataStore: DataStore<Preferences>): JwtTokenManager {
    companion object {
        private val ACCESS_JWT_KEY = stringPreferencesKey("jwt_token")
        //val REFRESH_JWT_KEY = stringPreferencesKey("refresh_jwt")
    }

    override suspend fun saveAccessJwt(token: String) {
        dataStore.edit { preferences ->
            preferences[ACCESS_JWT_KEY] = token
        }
    }

    override suspend fun getAccessJwt(): String? {

        val jwt = dataStore.data.map { preferences ->
            preferences[ACCESS_JWT_KEY]
        }.first()

        return jwt
    }

    override suspend fun clearAllTokens() {
        dataStore.edit { preferences ->
            preferences.remove(ACCESS_JWT_KEY)
            //preferences.remove(REFRESH_JWT_KEY)
        }
    }
}