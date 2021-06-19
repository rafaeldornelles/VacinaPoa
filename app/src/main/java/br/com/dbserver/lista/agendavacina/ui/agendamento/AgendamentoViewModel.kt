package br.com.dbserver.lista.agendavacina.ui.agendamento

import androidx.lifecycle.ViewModel
import br.com.dbserver.lista.agendavacina.repository.UsuarioRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AgendamentoViewModel : ViewModel() {

    private val usuarioRepository = UsuarioRepository()
    val usuario = Firebase.auth.currentUser?.uid?.let {
        usuarioRepository.findUsuarioByIdBanco(it)
    }
}