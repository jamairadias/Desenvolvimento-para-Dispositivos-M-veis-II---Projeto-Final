package com.example.agendamento.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agendamento.domain.model.Agendamento
import com.example.agendamento.domain.repository.AgendamentoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgendamentoViewModel @Inject constructor(
    private val repository: AgendamentoRepository
) : ViewModel() {

    private val _agendamentos = MutableStateFlow<List<Agendamento>>(emptyList())
    val agendamentos: StateFlow<List<Agendamento>> = _agendamentos.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        listarAgendamentos()
    }

    private fun listarAgendamentos() {
        viewModelScope.launch {
            _loading.value = true
            repository.listarAgendamentos().collect { lista ->
                _agendamentos.value = lista
                _loading.value = false
            }
        }
    }

    fun salvarAgendamento(agendamento: Agendamento, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _loading.value = true
            try {
                repository.salvar(agendamento)
                onSuccess()
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }

    fun excluirAgendamento(id: String) {
        viewModelScope.launch {
            try {
                repository.excluir(id)
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}
