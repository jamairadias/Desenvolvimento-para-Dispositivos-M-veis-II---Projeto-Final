package com.example.agendamento.di

import com.example.agendamento.data.repository.AgendamentoRepositoryImpl
import com.example.agendamento.domain.repository.AgendamentoRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideAgendamentoRepository(firestore: FirebaseFirestore): AgendamentoRepository {
        return AgendamentoRepositoryImpl(firestore)
    }
}
