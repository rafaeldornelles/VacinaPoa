package br.com.dbserver.lista.agendavacina.Repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import br.com.dbserver.lista.agendavacina.VacinaPoaApplication
import br.com.dbserver.lista.agendavacina.db.VacinaPoaDatabase
import br.com.dbserver.lista.agendavacina.model.Comorbidade
import br.com.dbserver.lista.retrofitcrudprodutos.retrofit.AppRetrofit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ComorbidadeRepository(){
    private val compositeDisposable = CompositeDisposable()

    private val db = VacinaPoaDatabase.getInstance(VacinaPoaApplication.instance.applicationContext);
    private val comorbidadeDao = db.comorbidadeDao()

    private val comorbidadeService = AppRetrofit.comorbidadeService


    private var comorbidades = MediatorLiveData<List<Comorbidade>>().apply {
        value = emptyList()
        addSource(comorbidadeDao.getComorbidades()) { value = it }
    }

    fun getComorbidades(): MutableLiveData<List<Comorbidade>>{
        compositeDisposable.add(comorbidadeService.listar()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                comorbidades.value = it
                comorbidadeDao.insert(*it.toTypedArray())
            }, {
                Log.e("DEBUG", "Connection Error: ${it.message}")
            }))
        return comorbidades
    }


}