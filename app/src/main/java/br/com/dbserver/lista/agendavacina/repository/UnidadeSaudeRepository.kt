package br.com.dbserver.lista.agendavacina.repository

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import br.com.dbserver.lista.agendavacina.VacinaPoaApplication
import br.com.dbserver.lista.agendavacina.db.VacinaPoaDatabase
import br.com.dbserver.lista.agendavacina.model.UnidadeSaude
import br.com.dbserver.lista.retrofitcrudprodutos.retrofit.AppRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UnidadeSaudeRepository(){
    private val compositeDisposable = CompositeDisposable()

    private val db = VacinaPoaDatabase.getInstance(VacinaPoaApplication.instance.applicationContext);
    private val unidadeSaudeDao = db.unidadeSaudeDao()

    private val unidadeSaudeService = AppRetrofit.unidadeSaudeService

    private var carregouDoServiço: Boolean = false


    private var unidadesSaude = MediatorLiveData<List<UnidadeSaude>>().apply {
        value = emptyList()
        addSource(unidadeSaudeDao.getUnidadesSaude()) { if (!carregouDoServiço) value = it }
    }

    fun getUnidadeSaude(): MutableLiveData<List<UnidadeSaude>> {
        compositeDisposable.add(unidadeSaudeService.listar()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                unidadesSaude.value = it
                carregouDoServiço = true
                unidadeSaudeDao.insert(*it.toTypedArray()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe()
            }, {
                Log.e("DEBUG", "Connection Error: ${it.message}")
            }))
        return unidadesSaude
    }


}