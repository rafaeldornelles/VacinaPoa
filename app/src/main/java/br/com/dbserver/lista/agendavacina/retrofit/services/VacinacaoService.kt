package br.com.dbserver.lista.agendavacina.retrofit.services

import br.com.dbserver.lista.agendavacina.model.Vacinacao
import io.reactivex.Observable
import retrofit2.http.*

interface VacinacaoService {
    @GET("vacinacoes")
    fun listar(): Observable<List<Vacinacao>>

    @GET("vacinacoes/{id}")
    fun buscarPorId(@Path("id") id: Int) : Observable<Vacinacao>

    @POST("vacinacoes")
    fun inserir(@Body vacinacao: Vacinacao) : Observable<Vacinacao>

    @DELETE("vacinacoes/{id}")
    fun deletar(@Path("id") id:Int) : Observable<Unit>

    @PUT("vacinacoes/{id}")
    fun alterar(@Path("id") id:Int, @Body vacinacao: Vacinacao) : Observable<Vacinacao>
}