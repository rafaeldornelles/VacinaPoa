package br.com.dbserver.lista.agendavacina.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import br.com.dbserver.lista.agendavacina.VacinaPoaApplication
import br.com.dbserver.lista.agendavacina.db.VacinaPoaDatabase
import br.com.dbserver.lista.agendavacina.model.Usuario
import br.com.dbserver.lista.agendavacina.model.populated.UsuarioPopulado
import br.com.dbserver.lista.retrofitcrudprodutos.retrofit.AppRetrofit
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UsuarioRepository() {
    private val db = VacinaPoaDatabase.getInstance(VacinaPoaApplication.instance.applicationContext)
    private val usuarioDao = db.usuarioDao()
    private val usuarioService = AppRetrofit.usuarioService
    val compositeDisposable = CompositeDisposable()

    fun inserir(u: Usuario): Completable{
        return usuarioService.inserir(u).concatMapCompletable {
            usuarioDao.insert(it)
        }
    }

    fun findUsuarioByIdBanco(id: String): LiveData<UsuarioPopulado>{
        val usuarioLivedata = MediatorLiveData<UsuarioPopulado>()
            .apply { addSource(usuarioDao.findByIdPopulated(id)) { this.value = it } }

        compositeDisposable.add(usuarioService.buscarPorId(id)
            .flatMapCompletable {
                usuarioLivedata.value?.usuario = it
                usuarioDao.insert(it)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ())
        return usuarioLivedata
    }
}