package com.example.agendamento

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.agendamento.data.local.PreferencesManager
import com.example.agendamento.ui.navigation.AppNavigation
import com.example.agendamento.ui.theme.AgendamentoTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferencesManager: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val darkModePref by preferencesManager.isDarkMode.collectAsState(initial = false)
            
            AgendamentoTheme(darkTheme = darkModePref) {
                AppNavigation(preferencesManager)
            }
        }
    }
}
