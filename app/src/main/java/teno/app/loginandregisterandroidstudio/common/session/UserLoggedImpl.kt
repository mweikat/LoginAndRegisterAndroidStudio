package teno.app.loginandregisterandroidstudio.common.session

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import teno.app.loginandregisterandroidstudio.loggedIn.data.User
import javax.inject.Inject

class UserLoggedImpl @Inject constructor(private val dataStore: DataStore<Preferences>): UserLogged {

    private val ACCESS_USER_NAME_KEY = stringPreferencesKey("user_name")
    private val ACCESS_USER_LAST_NAME_KEY = stringPreferencesKey("user_last_name")
    private val ACCESS_USER_EMAIL_KEY = stringPreferencesKey("user_email")

    override suspend fun getUser(): User? {
        val name:String =
            dataStore.data.map { preferences -> preferences[ACCESS_USER_NAME_KEY] }.first().toString()
        val lastName:String =
            dataStore.data.map { preferences -> preferences[ACCESS_USER_LAST_NAME_KEY] }.first().toString()
        val email:String =
            dataStore.data.map { preferences -> preferences[ACCESS_USER_EMAIL_KEY] }.first().toString()

        return User(name, lastName, email)
    }

    override suspend fun setUser(user: User) {
        dataStore.edit { preferences ->
            preferences[ACCESS_USER_NAME_KEY] = user.name
            preferences[ACCESS_USER_LAST_NAME_KEY] = user.lastNAme
            preferences[ACCESS_USER_EMAIL_KEY] = user.email
        }
    }

    override suspend fun delUser() {
        dataStore.edit { preferences ->preferences.remove(ACCESS_USER_NAME_KEY) }
        dataStore.edit { preferences ->preferences.remove(ACCESS_USER_LAST_NAME_KEY) }
        dataStore.edit { preferences ->preferences.remove(ACCESS_USER_EMAIL_KEY) }
    }
}