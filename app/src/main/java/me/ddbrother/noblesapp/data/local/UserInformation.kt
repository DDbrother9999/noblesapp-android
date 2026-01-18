package me.ddbrother.noblesapp.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import kotlinx.coroutines.flow.map
import me.ddbrother.noblesapp.data.model.UserProfile

private val Context.dataStore by preferencesDataStore("user_information")

class UserPreferences(private val context: Context) {

    private val keyPin = stringPreferencesKey("p_pin")
    private val keyProfile = stringPreferencesKey("user_profile_json")

    val userPin = context.dataStore.data.map { it[keyPin] }

    val userProfile = context.dataStore.data.map { prefs ->
        try {
            Gson().fromJson(prefs[keyProfile], UserProfile::class.java)
        } catch (_: Exception) {
            null
        }
    }

    suspend fun saveUser(pin: String, profile: UserProfile) {
        context.dataStore.edit { prefs ->
            prefs[keyPin] = pin
            val profileWithPin = profile.copy(pin = pin)
            prefs[keyProfile] = Gson().toJson(profileWithPin)
        }
    }
}