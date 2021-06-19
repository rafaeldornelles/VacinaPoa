package br.com.dbserver.lista.agendavacina.Repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import br.com.dbserver.lista.agendavacina.VacinaPoaApplication
import br.com.dbserver.lista.agendavacina.db.VacinaPoaDatabase
import br.com.dbserver.lista.agendavacina.model.GrupoPrioritario
import br.com.dbserver.lista.retrofitcrudprodutos.retrofit.AppRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GrupoPrioritarioRepository(){
    private val compositeDisposable = CompositeDisposable()

    private val db = VacinaPoaDatabase.getInstance(VacinaPoaApplication.instance.applicationContext);
    private val grupoPrioritarioDao = db.grupoPrioritarioDao()

    private val grupoPrioritarioService = AppRetrofit.grupoPrioritarioService


    private var gruposPrioritarios = MediatorLiveData<List<GrupoPrioritario>>().apply {
        value = emptyList()
        addSource(grupoPrioritarioDao.getGruposPrioritarios()) { value = it }
    }

    fun getGrupoPrioritarios(): MutableLiveData<List<GrupoPrioritario>> {
        compositeDisposable.add(grupoPrioritarioService.listar()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                gruposPrioritarios.value = it
                grupoPrioritarioDao.insert(*it.toTypedArray())
            }, {
                Log.e("DEBUG", "Connection Error: ${it.message}")
            }))
        return gruposPrioritarios
    }


}