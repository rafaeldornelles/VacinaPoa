package br.com.dbserver.lista.agendavacina.repository

import android.util.Log
import br.com.dbserver.lista.agendavacina.VacinaPoaApplication
import br.com.dbserver.lista.agendavacina.db.VacinaPoaDatabase
import br.com.dbserver.lista.agendavacina.model.UsuarioComorbidade
import br.com.dbserver.lista.retrofitcrudprodutos.retrofit.AppRetrofit
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

class UsuarioComorbidadeRepository {
    private val compositeDisposable = CompositeDisposable()

    private val db = VacinaPoaDatabase.getInstance(VacinaPoaApplication.instance.applicationContext);
    private val unidadeSaudeDao = db.usuarioComorbidadeDao()
    private val service = AppRetrofit.usuarioComorbidadeService

    fun inserirRelacao(vararg  usuarioComorbidade: UsuarioComorbidade): Completable{
        val mapRequests = usuarioComorbidade.toList().map { service.inserir(it) }
        val mergeRequests = Observable.fromIterable(mapRequests)
            .concatMap {
                it
            }.toList().toObservable()

        Log.i("AAAA", "inserindo usuário")
        return mergeRequests.flatMapCompletable {
            Log.i("AAAA", "inserindo usuário 2")
            unidadeSaudeDao.insert(*it.toTypedArray())
        }
    }

    fun removerRelacao(vararg  usuarioComorbidade: UsuarioComorbidade): Completable{
        val mapRequests = usuarioComorbidade.toList().map { service.deletar(it.id?:-1) }
        val mergeRequests = Observable.fromIterable(mapRequests)
            .flatMap { it }.toList().toObservable()
        return mergeRequests.flatMapCompletable {
            Log.i("AAAA", "inserindo usuário comorbidade 2")
            unidadeSaudeDao.delete(*usuarioComorbidade)
        }
    }
}