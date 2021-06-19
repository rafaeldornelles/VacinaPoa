package br.com.dbserver.lista.agendavacina.retrofit.services

import br.com.dbserver.lista.agendavacina.model.Vacina
import io.reactivex.Observable
import retrofit2.http.*

interface VacinaService {
    @GET("vacinas")
    fun listar(): Observable<List<Vacina>>

    @GET("vacinas/{id}")
    fun buscarPorId(@Path("id") id: Int) : Observable<Vacina>

    @POST("vacinas")
    fun inserir(@Body vacina: Vacina) : Observable<Vacina>

    @DELETE("vacinas/{id}")
    fun deletar(@Path("id") id:Int) : Observable<Unit>

    @PUT("vacinas/{id}")
    fun alterar(@Path("id") id:Int, @Body vacina: Vacina) : Observable<Vacina>
}