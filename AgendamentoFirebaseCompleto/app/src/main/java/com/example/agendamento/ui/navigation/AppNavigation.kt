package com.example.agendamento.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.example.agendamento.ui.screens.*
import com.example.agendamento.data.local.PreferencesManager

@Composable
fun AppNavigation(preferencesManager: PreferencesManager) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "login") {
        composable("login") {
            LoginScreen(onLoginSuccess = {
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            })
        }
        composable("home") {
            HomeScreen(
                onAddClick = { navController.navigate("form") },
                onSettingsClick = { navController.navigate("settings") }
            )
        }
        composable("form") {
            AgendamentoFormScreen(onBack = { navController.popBackStack() })
        }
        composable("settings") {
            ConfiguracoesScreen(
                onLogout = {
                    navController.navigate("login") {
                        popUpTo(0)
                    }
                },
                preferencesManager = preferencesManager
            )
        }
    }
}
