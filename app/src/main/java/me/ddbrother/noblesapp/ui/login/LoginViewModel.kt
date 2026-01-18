package me.ddbrother.noblesapp.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.ddbrother.noblesapp.data.local.UserPreferences
import me.ddbrother.noblesapp.data.network.NetworkModule

class LoginViewModel(app: Application) : AndroidViewModel(app) {
    private val prefs = UserPreferences(app)

    private val _state = MutableStateFlow<LoginState>(LoginState.Idle)
    val state = _state.asStateFlow()

    fun login(pin: String) {
        if (pin.length != 8) {
            _state.value = LoginState.Error("PIN must be 8 digits")
            return
        }

        viewModelScope.launch {
            _state.value = LoginState.Loading
            try {
                val profile = NetworkModule.apiService.getUserProfile(pin)

                if (profile.firstName.isNullOrEmpty()) {
                    _state.value = LoginState.Error("Invalid PIN or Data not found")
                } else {
                    prefs.saveUser(pin, profile)
                    _state.value = LoginState.Success
                }
            } catch (e: Exception) {
                _state.value = LoginState.Error(e.message ?: "An error occurred")
            }
        }
    }
}

sealed interface LoginState {
    data object Idle : LoginState
    data object Loading : LoginState
    data object Success : LoginState
    data class Error(val message: String) : LoginState
}