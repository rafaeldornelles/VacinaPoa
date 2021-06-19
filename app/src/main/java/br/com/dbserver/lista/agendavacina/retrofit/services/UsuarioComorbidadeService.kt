package br.com.dbserver.lista.agendavacina.retrofit.services

import br.com.dbserver.lista.agendavacina.model.UsuarioComorbidade
import io.reactivex.Observable
import retrofit2.http.*

interface UsuarioComorbidadeService {

    @GET("usuarioscomorbidades")
    fun listar(): Observable<List<UsuarioComorbidade>>

    @GET("usuarioscomorbidades/{id}")
    fun buscarPorId(@Path("id") id: Int) : Observable<UsuarioComorbidade>

    @POST("usuarioscomorbidades")
    fun inserir(@Body usuarioComorb: UsuarioComorbidade) : Observable<UsuarioComorbidade>

    @DELETE("usuarioscomorbidades/{id}")
    fun deletar(@Path("id") id:Int) : Observable<Unit>

    @PUT("usuarioscomorbidades/{id}")
    fun alterar(@Path("id") id:Int, @Body usuarioComorb: UsuarioComorbidade) : Observable<UsuarioComorbidade>

}