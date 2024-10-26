package bob.colbaskin.greenrostov.data.local.dataStore

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import bob.colbaskin.greenrostov.domain.local.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import java.io.IOException

/**
 * @author bybuss
 */
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "on_boarding_pref")

class DataStoreRepositoryImpl(private val context: Context): DataStoreRepository {

    private object PreferencesKey {
        val onBoardingKey = booleanPreferencesKey(name = "on_boarding_completed")
        val token = stringPreferencesKey("token")
    }

    private val dataStore = context.dataStore

    override suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.onBoardingKey] = completed
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[PreferencesKey.onBoardingKey] ?: false
                onBoardingState
            }
    }

    override fun getToken(): Flow<String> {
        return context.dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else { throw exception }
        }
            .map { it[PreferencesKey.token] ?: "" }
            .onEach { Log.d("AuthViewModel", "getAccessToken: $it") }
    }

    override suspend fun saveToken(token: String) {
        context.dataStore.edit { prefs ->
            prefs[PreferencesKey.token] = token
        }
        Log.d("AuthViewModel", "saveToken: $token")
    }

//    override suspend fun clearToken() {
//        context.dataStore.edit { prefs ->
//            prefs.remove(PreferencesKey.token)
//        }
//        Log.d("AuthViewModel", "Token cleared")
//    }
}