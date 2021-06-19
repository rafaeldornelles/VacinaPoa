package br.com.dbserver.lista.agendavacina.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.dbserver.lista.agendavacina.repository.GrupoPrioritarioRepository
import br.com.dbserver.lista.agendavacina.model.GrupoPrioritario
import br.com.dbserver.lista.agendavacina.model.populated.UsuarioPopulado

class GrupoPrioritarioViewModel(): ViewModel() {
    val grupoPrioritarioRepository = GrupoPrioritarioRepository()

    val gruposPrioritarios = grupoPrioritarioRepository.getGrupoPrioritarios()

    val usuario: MutableLiveData<UsuarioPopulado?> = MutableLiveData(null)

    val gruposPrioritariosUsuario = mutableListOf<GrupoPrioritario>()
}
