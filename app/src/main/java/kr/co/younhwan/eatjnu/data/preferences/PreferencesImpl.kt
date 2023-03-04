package kr.co.younhwan.eatjnu.data.preferences

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import javax.inject.Inject

private const val PREFERENCES_NAME = "preferences_name"

private val Context.dataStore by preferencesDataStore(name = PREFERENCES_NAME)

class PreferencesImpl @Inject constructor(
    private val context: Context
) : Preferences {

    override suspend fun getUserId(): String? {
        return try {
            val preferencesKey = stringPreferencesKey("user_id")
            val preferences = context.dataStore.data.first()
            preferences[preferencesKey]
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    override suspend fun createUserId(id: String) {
        val preferencesKey = stringPreferencesKey("user_id")
        context.dataStore.edit { preferences ->
            preferences[preferencesKey] = id
        }
    }
}