package me.ddbrother.noblesapp.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {
    val state by viewModel.state.collectAsState()
    var pin by remember { mutableStateOf("") }

    LaunchedEffect(state) {
        if (state is LoginState.Success) onLoginSuccess()
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Welcome to Nobles App", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(32.dp))

        OutlinedTextField(
            value = pin,
            onValueChange = { if (it.length <= 8) pin = it },
            label = { Text("Enter 8-digit PIN") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        val currentState = state

        if (currentState is LoginState.Loading) {
            CircularProgressIndicator()
        } else {
            Button(
                onClick = { viewModel.login(pin) },
                enabled = pin.length == 8,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }
        }

        if (currentState is LoginState.Error) {
            Spacer(Modifier.height(8.dp))
            Text(
                text = currentState.message,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}