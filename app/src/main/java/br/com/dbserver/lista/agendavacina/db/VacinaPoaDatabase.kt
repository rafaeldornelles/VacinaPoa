package br.com.dbserver.lista.agendavacina.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.dbserver.lista.agendavacina.db.dao.*
import br.com.dbserver.lista.agendavacina.model.*

@Database(entities = [Agendamento::class, Vacina::class, Comorbidade::class, GrupoPrioritario::class, GrupoVacinacao::class,
                    UnidadeSaude::class, Usuario::class, Vacinacao::class, UsuarioComorbidade::class],
                    version = 5, exportSchema = false)
@TypeConverters(DateConverters::class)
abstract class VacinaPoaDatabase: RoomDatabase() {
    companion object {
        private lateinit var instance: VacinaPoaDatabase

        fun getInstance(context: Context): VacinaPoaDatabase {
            if (!::instance.isInitialized) {
                instance = Room.databaseBuilder(context, VacinaPoaDatabase::class.java, "dbLunch")
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return instance
        }

    }
    abstract fun comorbidadeDao(): ComorbidadeDao
    abstract fun grupoPrioritarioDao(): GrupoPrioritarioDao
    abstract fun grupoVacinacaoDao(): GrupoVacinacaoDao
    abstract fun unidadeSaudeDao(): UnidadeSaudeDao
    abstract fun usuarioDao(): UsuarioDao
    abstract fun usuarioComorbidadeDao(): UsuarioComorbidadeDao
    abstract fun vacinacaoDao(): VacinacaoDao
    abstract fun vacinaDao(): VacinaDao
}