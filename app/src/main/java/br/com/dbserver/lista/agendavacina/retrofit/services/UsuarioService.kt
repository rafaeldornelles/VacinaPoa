package br.com.dbserver.lista.agendavacina.retrofit.services

import br.com.dbserver.lista.agendavacina.model.Usuario
import br.com.dbserver.lista.agendavacina.model.Vacina
import io.reactivex.Observable
import retrofit2.http.*

interface UsuarioService {
    @GET("usuarios")
    fun listar(): Observable<List<Vacina>>

    @GET("usuarios/{id}")
    fun buscarPorId(@Path("id") id: String) : Observable<Usuario>

    @POST("usuarios")
    fun inserir(@Body usuario: Usuario) : Observable<Usuario>

    @DELETE("usuarios/{id}")
    fun deletar(@Path("id") id:Int) : Observable<Unit>

    @PUT("usuarios/{id}")
    fun alterar(@Path("id") id:Int, @Body usuario: Usuario) : Observable<Usuario>
}