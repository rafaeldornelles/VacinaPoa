package br.com.dbserver.lista.agendavacina.retrofit.services

import br.com.dbserver.lista.agendavacina.model.Comorbidade
import br.com.dbserver.lista.agendavacina.model.Vacina
import io.reactivex.Observable
import retrofit2.http.*

interface ComorbidadeService {
    @GET("comorbidades")
    fun listar(): Observable<List<Comorbidade>>

    @GET("comorbidades/{id}")
    fun buscarPorId(@Path("id") id: Int) : Observable<Comorbidade>

    @POST("comorbidades")
    fun inserir(@Body comorbidades: Comorbidade) : Observable<Comorbidade>

    @DELETE("comorbidades/{id}")
    fun deletar(@Path("id") id:Int) : Observable<Unit>

    @PUT("comorbidades/{id}")
    fun alterar(@Path("id") id:Int, @Body comorbidade: Comorbidade) : Observable<Comorbidade>
}