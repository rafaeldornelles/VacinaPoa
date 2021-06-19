package br.com.dbserver.lista.agendavacina.repository

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import br.com.dbserver.lista.agendavacina.VacinaPoaApplication
import br.com.dbserver.lista.agendavacina.db.VacinaPoaDatabase
import br.com.dbserver.lista.agendavacina.model.GrupoVacinacao
import br.com.dbserver.lista.retrofitcrudprodutos.retrofit.AppRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GrupoVacinacaoRepository(){
    private val compositeDisposable = CompositeDisposable()

    private val db = VacinaPoaDatabase.getInstance(VacinaPoaApplication.instance.applicationContext)
    private val grupoVacinacaoDao = db.grupoVacinacaoDao()

    private val grupoVacinacaoService = AppRetrofit.grupoVacinacaoService

    private var carregouDoServiço: Boolean = false


    private var unidadesSaude = MediatorLiveData<List<GrupoVacinacao>>().apply {
        value = emptyList()
        addSource(grupoVacinacaoDao.getGruposVacinacao()) { if (!carregouDoServiço) value = it }
    }

    fun getGrupoVacinacao(): MutableLiveData<List<GrupoVacinacao>> {
        compositeDisposable.add(grupoVacinacaoService.listar()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                unidadesSaude.value = it
                carregouDoServiço = true
                grupoVacinacaoDao.insert(*it.toTypedArray()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe()
            }, {
                Log.e("DEBUG", "Connection Error: ${it.message}")
            }))
        return unidadesSaude
    }


}