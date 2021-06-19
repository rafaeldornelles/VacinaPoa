package br.com.dbserver.lista.agendavacina

import android.app.Application
import android.content.Context

class VacinaPoaApplication: Application() {
    companion object{
        lateinit var instance: Application private set
    }

    override fun onCreate() {
        instance = this
        super.onCreate()
    }
}