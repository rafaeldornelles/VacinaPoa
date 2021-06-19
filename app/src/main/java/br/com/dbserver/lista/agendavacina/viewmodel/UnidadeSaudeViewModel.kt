package br.com.dbserver.lista.agendavacina.viewmodel

import androidx.lifecycle.ViewModel
import br.com.dbserver.lista.agendavacina.repository.UnidadeSaudeRepository

class UnidadeSaudeViewModel : ViewModel() {

    private val unidadeSaudeRepository = UnidadeSaudeRepository()

    fun getUnidadesSaude() = unidadeSaudeRepository.getUnidadeSaude()
}