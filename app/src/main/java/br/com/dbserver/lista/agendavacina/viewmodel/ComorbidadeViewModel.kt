package br.com.dbserver.lista.agendavacina.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.dbserver.lista.agendavacina.Repository.ComorbidadeRepository

class ComorbidadeViewModel(): ViewModel() {
    val comorbidadeRepository = ComorbidadeRepository()

    val comorbidades = comorbidadeRepository.getComorbidades()
}
