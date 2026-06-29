package com.example.agendamento.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.agendamento.domain.model.Agendamento
import com.example.agendamento.ui.viewmodel.AgendamentoViewModel
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgendamentoFormScreen(
    onBack: () -> Unit,
    viewModel: AgendamentoViewModel = hiltViewModel()
) {
    var servico by remember { mutableStateOf("") }
    var data by remember { mutableStateOf("") }
    var horario by remember { mutableStateOf("") }

    val datePickerState = rememberDatePickerState()
    var showDatePicker by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Novo Agendamento") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = servico,
                onValueChange = { servico = it },
                label = { Text("Serviço") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedButton(
                onClick = { showDatePicker = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (data.isEmpty()) "Selecionar Data" else "Data: $data")
            }

            OutlinedTextField(
                value = horario,
                onValueChange = { horario = it },
                label = { Text("Horário (HH:mm)") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    val agendamento = Agendamento(servico = servico, data = data, horario = horario)
                    viewModel.salvarAgendamento(agendamento, onBack)
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = servico.isNotBlank() && data.isNotBlank() && horario.isNotBlank()
            ) {
                Text("Salvar")
            }
        }

        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton(onClick = {
                        datePickerState.selectedDateMillis?.let {
                            val cal = Calendar.getInstance()
                            cal.timeInMillis = it
                            data = "${cal.get(Calendar.DAY_OF_MONTH)}/${cal.get(Calendar.MONTH) + 1}/${cal.get(Calendar.YEAR)}"
                        }
                        showDatePicker = false
                    }) { Text("Confirmar") }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}
