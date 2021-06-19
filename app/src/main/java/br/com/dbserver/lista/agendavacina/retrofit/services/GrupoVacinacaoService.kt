package br.com.dbserver.lista.agendavacina.retrofit.services

import br.com.dbserver.lista.agendavacina.model.GrupoVacinacao
import io.reactivex.Observable
import retrofit2.http.*

interface GrupoVacinacaoService {
    @GET("gruposvacinacao")
    fun listar(): Observable<List<GrupoVacinacao>>

    @GET("gruposvacinacao/{id}")
    fun buscarPorId(@Path("id") id: Int) : Observable<GrupoVacinacao>

    @POST("gruposvacinacao")
    fun inserir(@Body grupoVacinacao: GrupoVacinacao) : Observable<GrupoVacinacao>

    @DELETE("gruposvacinacao/{id}")
    fun deletar(@Path("id") id:Int) : Observable<Unit>

    @PUT("gruposvacinacao/{id}")
    fun alterar(@Path("id") id:Int, @Body grupoVacinacao: GrupoVacinacao) : Observable<GrupoVacinacao>
}