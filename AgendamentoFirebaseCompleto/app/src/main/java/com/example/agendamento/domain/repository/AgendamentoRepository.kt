
package com.example.agendamento.domain.repository

import com.example.agendamento.domain.model.Agendamento
import kotlinx.coroutines.flow.Flow

interface AgendamentoRepository {
 fun listarAgendamentos(): Flow<List<Agendamento>>
 suspend fun salvar(agendamento: Agendamento)
 suspend fun atualizar(agendamento: Agendamento)
 suspend fun excluir(id: String)
}
