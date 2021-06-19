package br.com.dbserver.lista.agendavacina.retrofit.services

import br.com.dbserver.lista.agendavacina.model.UnidadeSaude
import io.reactivex.Observable
import retrofit2.http.*

interface UnidadeSaudeService{

    @GET("unidadesSaude")
    fun listar(): Observable<List<UnidadeSaude>>

    @GET("unidadesSaude/{id}")
    fun buscarPorId(@Path("id") id: Int) : Observable<UnidadeSaude>

    @POST("unidadesSaude")
    fun inserir(@Body us: UnidadeSaude) : Observable<UnidadeSaude>

    @DELETE("unidadesSaude/{id}")
    fun deletar(@Path("id") id:Int) : Observable<Unit>

    @PUT("unidadesSaude/{id}")
    fun alterar(@Path("id") id:Int, @Body us: UnidadeSaude) : Observable<UnidadeSaude>

}