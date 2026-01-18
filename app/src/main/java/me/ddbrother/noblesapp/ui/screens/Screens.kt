package me.ddbrother.noblesapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ProfileScreen() {
    val context = androidx.compose.ui.platform.LocalContext.current
    val prefs = androidx.compose.runtime.remember { me.ddbrother.noblesapp.data.local.UserPreferences(context) }
    val userProfile = prefs.userProfile.collectAsState(initial = null).value

    androidx.compose.foundation.layout.Column(
        modifier = Modifier.fillMaxSize()
    ) {
        if (userProfile != null) {
            Text(text = "Name: ${userProfile.firstName} ${userProfile.lastName}", color = androidx.compose.ui.graphics.Color.Black)
            Text(text = "Type: ${userProfile.personType}", color = androidx.compose.ui.graphics.Color.Black)
            Text(text = "UNID: ${userProfile.unId}", color = androidx.compose.ui.graphics.Color.Black)
            Text(text = "PeopleID: ${userProfile.peopleId}", color = androidx.compose.ui.graphics.Color.Black)
            if (!userProfile.pin.isNullOrEmpty()) {
                Text(text = "PIN: ${userProfile.pin}", color = androidx.compose.ui.graphics.Color.Black)
            }
            Text(text = "Assembly Seat: ${userProfile.assemblySeat}", color = androidx.compose.ui.graphics.Color.Black)
            
            if (!userProfile.macLockerNumber.isNullOrEmpty()) {
                Text(text = "MAC Locker: ${userProfile.macLockerNumber} (Combo: ${userProfile.macLockerCombo})", color = androidx.compose.ui.graphics.Color.Black)
            }
            if (!userProfile.schoolHouseLockerNumber.isNullOrEmpty()) {
               Text(text = "SchoolHouse Locker: ${userProfile.schoolHouseLockerNumber} (Combo: ${userProfile.schoolHouseLockerCombo})", color = androidx.compose.ui.graphics.Color.Black)
            }
             if (!userProfile.msCubbyNo.isNullOrEmpty()) {
               Text(text = "MS Cubby: ${userProfile.msCubbyNo} (Combo: ${userProfile.msCubbyCombo})", color = androidx.compose.ui.graphics.Color.Black)
            }

        } else {
            Text(text = "No profile data found", color = androidx.compose.ui.graphics.Color.Black)
        }
    }
}

@Composable
fun ScheduleScreen() { ScreenContent("Schedule") }

@Composable
fun MenuScreen() { ScreenContent("Menu") }

@Composable
fun AthleticsScreen() { ScreenContent("Athletics") }

@Composable
fun DirectoryScreen() { ScreenContent("Directory") }

@Composable
fun WhoHasLunchScreen() { ScreenContent("Who has lunch?") }

@Composable
fun HelpfulLinksScreen() { ScreenContent("Helpful Links") }

@Composable
fun SettingsScreen() { ScreenContent("Settings") }

@Composable
fun ScreenContent(title: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = title)
    }
}
