package br.com.dbserver.lista.agendavacina.retrofit.services

import br.com.dbserver.lista.agendavacina.model.Agendamento
import br.com.dbserver.lista.agendavacina.model.Vacina
import io.reactivex.Observable
import retrofit2.http.*

interface AgendamentoService {

    @GET("agendamentos")
    fun listar(): Observable<List<Agendamento>>

    @GET("agendamentos/{id}")
    fun buscarPorId(@Path("id") id: Int) : Observable<Agendamento>

    @POST("agendamentos")
    fun inserir(@Body agendamento: Agendamento) : Observable<Agendamento>

    @DELETE("agendamentos/{id}")
    fun deletar(@Path("id") id:Int) : Observable<Unit>

    @PUT("agendamentos/{id}")
    fun alterar(@Path("id") id:Int, @Body agendamento: Agendamento) : Observable<Agendamento>
}
