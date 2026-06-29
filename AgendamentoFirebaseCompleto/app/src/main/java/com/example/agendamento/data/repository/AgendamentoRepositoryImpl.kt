package com.example.agendamento.data.repository

import com.example.agendamento.domain.model.Agendamento
import com.example.agendamento.domain.repository.AgendamentoRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AgendamentoRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : AgendamentoRepository {

    private val collection = firestore.collection("agendamentos")

    override fun listarAgendamentos(): Flow<List<Agendamento>> = callbackFlow {
        val subscription = collection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }
            if (snapshot != null) {
                val agendamentos = snapshot.toObjects<Agendamento>()
                trySend(agendamentos)
            }
        }
        awaitClose { subscription.remove() }
    }

    override suspend fun salvar(agendamento: Agendamento) {
        try {
            val doc = if (agendamento.id.isEmpty()) collection.document() else collection.document(agendamento.id)
            val finalAgendamento = agendamento.copy(id = doc.id)
            doc.set(finalAgendamento).await()
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun atualizar(agendamento: Agendamento) {
        collection.document(agendamento.id).set(agendamento).await()
    }

    override suspend fun excluir(id: String) {
        collection.document(id).delete().await()
    }
}
