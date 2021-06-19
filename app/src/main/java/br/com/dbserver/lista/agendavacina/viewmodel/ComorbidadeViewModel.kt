package br.com.dbserver.lista.agendavacina.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.dbserver.lista.agendavacina.repository.ComorbidadeRepository
import br.com.dbserver.lista.agendavacina.repository.UsuarioComorbidadeRepository
import br.com.dbserver.lista.agendavacina.model.Comorbidade
import br.com.dbserver.lista.agendavacina.model.UsuarioComorbidade
import br.com.dbserver.lista.agendavacina.model.populated.UsuarioPopulado
import io.reactivex.Completable

class ComorbidadeViewModel(): ViewModel() {
    val comorbidadeRepository = ComorbidadeRepository()
    val usuarioComorbidadeRepository = UsuarioComorbidadeRepository()
    val usuario: MutableLiveData<UsuarioPopulado?> = MutableLiveData()

    val comorbidades = comorbidadeRepository.getComorbidades()

    fun cadastrarComorbidades(usuario: UsuarioPopulado, comorbidades: List<Comorbidade>): Completable{
        Log.i("AAAA", "entrou no vm")
        return usuarioComorbidadeRepository.removerRelacao(*(usuario.usaruarioComorbidades).toTypedArray())
            .andThen {
                Log.i("AAAA", "entrou no vm 2")
                val novosUsuariosComorbidade = comorbidades.map { UsuarioComorbidade(0, usuario.usuario?.id?:"", it.id) }
                usuarioComorbidadeRepository.inserirRelacao(*novosUsuariosComorbidade.toTypedArray())
            }
    }
}
