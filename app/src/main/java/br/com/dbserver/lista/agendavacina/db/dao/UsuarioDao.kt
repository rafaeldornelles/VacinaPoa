package br.com.dbserver.lista.agendavacina.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import br.com.dbserver.lista.agendavacina.model.Usuario
import br.com.dbserver.lista.agendavacina.model.populated.UsuarioPopulado

@Dao
interface UsuarioDao: BaseDao<Usuario> {
    @Query("SELECT * FROM usuario WHERE id = :id")
    fun findById(id: String): LiveData<Usuario>

    @Query("SELECT * FROM usuario WHERE id = :id")
    fun findByIdPopulated(id: String): LiveData<UsuarioPopulado>

    @Transaction
    @Query("SELECT * FROM usuario")
    fun getUsuarios(): LiveData<List<Usuario>>

    @Query("SELECT * FROM usuario WHERE idUnidadeSaude = :idUnidade")
    fun getProfissionaisByUnidadeSaude(idUnidade: Int): LiveData<List<Usuario>>
}