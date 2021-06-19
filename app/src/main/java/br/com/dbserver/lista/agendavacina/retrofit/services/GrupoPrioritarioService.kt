package br.com.dbserver.lista.agendavacina.retrofit.services

import br.com.dbserver.lista.agendavacina.model.GrupoPrioritario
import io.reactivex.Observable
import retrofit2.http.*

interface GrupoPrioritarioService {
    @GET("gruposPrioritarios")
    fun listar(): Observable<List<GrupoPrioritario>>

    @GET("gruposPrioritarios/{id}")
    fun buscarPorId(@Path("id") id: Int) : Observable<GrupoPrioritario>

    @POST("gruposPrioritarios")
    fun inserir(@Body grupoPrioritario: GrupoPrioritario) : Observable<GrupoPrioritario>

    @DELETE("gruposPrioritarios/{id}")
    fun deletar(@Path("id") id:Int) : Observable<Unit>

    @PUT("gruposPrioritarios/{id}")
    fun alterar(@Path("id") id:Int, @Body grupoPrioritario: GrupoPrioritario) : Observable<GrupoPrioritario>
}