package br.com.dbserver.lista.agendavacina.Repository

import android.app.Application
import br.com.dbserver.lista.agendavacina.VacinaPoaApplication
import br.com.dbserver.lista.agendavacina.db.VacinaPoaDatabase
import br.com.dbserver.lista.agendavacina.model.Usuario
import br.com.dbserver.lista.retrofitcrudprodutos.retrofit.AppRetrofit
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UsuarioRepository() {
    private val db = VacinaPoaDatabase.getInstance(VacinaPoaApplication.instance.applicationContext)
    private val usuarioDao = db.usuarioDao()
    private val usuarioService = AppRetrofit.usuarioService

    fun inserir(u: Usuario): Completable{
        return usuarioService.inserir(u).concatMapCompletable {
            usuarioDao.insert(it)
        }
    }
}