package br.com.dbserver.lista.agendavacina.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.dbserver.lista.agendavacina.model.GrupoPrioritario
import br.com.dbserver.lista.agendavacina.model.GrupoVacinacao
import br.com.dbserver.lista.agendavacina.model.Usuario
import br.com.dbserver.lista.agendavacina.model.populated.UsuarioPopulado
import br.com.dbserver.lista.agendavacina.repository.GrupoVacinacaoRepository

class GruposVacinacaoViewModel: ViewModel() {
    val gruposVacinacaoRepository = GrupoVacinacaoRepository()
    val gruposVacinacao = gruposVacinacaoRepository.getGrupoVacinacao()

    val usuario:MutableLiveData<UsuarioPopulado?> = MutableLiveData(null)
    val gruposprioritariosUsuario:MutableLiveData<List<GrupoPrioritario?>> = MutableLiveData(mutableListOf())

    val grupoVacinacaoUsuario: MutableLiveData<GrupoVacinacao> = MutableLiveData()
}