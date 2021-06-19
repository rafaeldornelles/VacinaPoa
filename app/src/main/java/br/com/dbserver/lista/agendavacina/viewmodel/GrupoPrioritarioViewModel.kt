package br.com.dbserver.lista.agendavacina.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.dbserver.lista.agendavacina.Repository.GrupoPrioritarioRepository

class GrupoPrioritarioViewModel(): ViewModel() {
    val grupoPrioritarioRepository = GrupoPrioritarioRepository()

    val gruposPrioritarios = grupoPrioritarioRepository.getGrupoPrioritarios()
}
