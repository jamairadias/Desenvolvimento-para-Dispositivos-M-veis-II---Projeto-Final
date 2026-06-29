# Agendamento Firebase Completo

Este é um projeto Android moderno de agendamentos, desenvolvido com as melhores práticas de desenvolvimento Android, utilizando Jetpack Compose, Firebase e arquitetura MVVM.

## 🚀 Funcionalidades Mínimas Implementadas

- **Tela de Login e Cadastro**: Autenticação completa via Firebase Auth.
- **Tela Principal (Home)**: Listagem de agendamentos associados ao usuário.
- **Listagens com LazyColumn**: Performance otimizada para listas.
- **Formulários de Cadastro**: Adição de novos agendamentos com validações.
- **Navegação entre telas**: Utilização de Navigation Compose.
- **Tela de Configurações**: Opções de customização do app.
- **Persistência com DataStore**: Armazenamento de preferências (Modo Escuro).
- **Componentes Material 3**:
    - `AlertDialog` para confirmações e erros.
    - `Snackbar` para feedback de ações.
    - `DatePicker` para seleção de datas.
- **Loading States**: Feedback visual durante operações assíncronas.
- **Tratamento de Erros**: Gerenciamento de exceções e feedbacks ao usuário.

## 🛠 Tecnologias e Padrões Utilizados

- **Linguagem**: Kotlin
- **Interface**: Jetpack Compose (Material 3)
- **Arquitetura**: MVVM (Model-View-ViewModel)
- **Injeção de Dependência**: Hilt
- **Assincronismo**: Coroutines e Flow/StateFlow
- **Banco de Dados/Backend**: 
    - Firebase Authentication
    - Cloud Firestore
- **Persistência Local**: Jetpack DataStore
- **Padrão de Projeto**: Repository Pattern

## 📂 Organização do Projeto

```text
com.example.agendamento
├── data
│   ├── local (DataStore / Preferences)
│   └── repository (Implementações dos Repositórios)
├── domain
│   ├── model (Entidades de dados)
│   └── repository (Interfaces dos Repositórios)
├── di (Módulos do Hilt)
├── ui
│   ├── components (Componentes reutilizáveis)
│   ├── navigation (Configuração de rotas)
│   ├── screens (Telas do App)
│   ├── theme (Cores, Tipografia e Tema)
│   └── viewmodel (Lógica de apresentação)
└── App.kt (Configuração do Hilt Application)
```

## ⚙️ Como rodar o projeto

1. Clone o repositório.
2. No console do Firebase:
   - Crie um novo projeto Android.
   - Adicione o pacote `com.example.agendamento`.
   - Baixe o arquivo `google-services.json` e coloque-o na pasta `app/`.
   - Ative **Email/Password** no Authentication.
   - Ative o **Cloud Firestore**.
3. Sincronize o Gradle e execute o app.

---
Desenvolvido como projeto prático seguindo os requisitos de arquitetura e funcionalidades modernas do ecossistema Android.
