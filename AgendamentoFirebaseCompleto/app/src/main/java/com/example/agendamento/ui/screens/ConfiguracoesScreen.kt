package com.example.agendamento.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.agendamento.data.local.PreferencesManager
import com.example.agendamento.ui.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfiguracoesScreen(
    onLogout: () -> Unit,
    preferencesManager: PreferencesManager,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val isDarkMode by preferencesManager.isDarkMode.collectAsState(initial = false)

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Configurações") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Modo Escuro")
                Switch(
                    checked = isDarkMode,
                    onCheckedChange = { 
                        scope.launch { preferencesManager.setDarkMode(it) }
                    }
                )
            }

            HorizontalDivider()

            Button(
                onClick = {
                    authViewModel.logout()
                    onLogout()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text("Sair da Conta")
            }
        }
    }
}
