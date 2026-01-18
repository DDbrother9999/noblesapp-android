package me.ddbrother.noblesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import me.ddbrother.noblesapp.data.local.UserPreferences
import me.ddbrother.noblesapp.ui.MainScreen
import me.ddbrother.noblesapp.ui.login.LoginScreen
import me.ddbrother.noblesapp.ui.theme.NoblesappAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val userPreferences = remember { UserPreferences(context) }
            val userPin by userPreferences.userPin.collectAsState(initial = null)

            NoblesappAndroidTheme {
                if (userPin != null) {
                    MainScreen()
                } else {
                    LoginScreen(onLoginSuccess = {
                    })
                }
            }
        }
    }
}
