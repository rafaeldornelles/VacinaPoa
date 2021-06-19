package br.com.dbserver.lista.agendavacina.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import io.reactivex.Completable


interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg model: T): Completable

    @Delete
    fun delete(vararg model: T): Completable
}