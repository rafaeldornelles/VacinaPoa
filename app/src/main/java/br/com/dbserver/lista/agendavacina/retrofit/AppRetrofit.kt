
package br.com.dbserver.lista.retrofitcrudprodutos.retrofit


import br.com.dbserver.lista.agendavacina.model.Usuario
import br.com.dbserver.lista.agendavacina.retrofit.services.*
import br.com.dbserver.lista.agendavacina.utils.LocalDateAdapter
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate

class AppRetrofit {
    companion object{
        private const val BASE_URL = "http://10.0.2.2:3000"
        val gson = GsonBuilder().registerTypeAdapter(LocalDate::class.java, LocalDateAdapter()).create()
        val retrofit : Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }

        val agendamentoService by lazy {
            retrofit.create(AgendamentoService::class.java)
        }
        val comorbidadeService by lazy {
            retrofit.create(ComorbidadeService::class.java)
        }
        val grupoPrioritarioService by lazy {
            retrofit.create(GrupoPrioritarioService::class.java)
        }
        val grupoVacinacaoService by lazy {
            retrofit.create(GrupoVacinacaoService::class.java)
        }
        val unidadeSaudeService by lazy {
            retrofit.create(UnidadeSaudeService::class.java)
        }
        val usuarioComorbidadeService by lazy {
            retrofit.create(UsuarioComorbidadeService::class.java)
        }
        val usuarioService by lazy {
            retrofit.create(UsuarioService::class.java)
        }
        val vacinacaoService by lazy {
            retrofit.create(VacinacaoService::class.java)
        }
        val vacinaService by lazy {
            retrofit.create(VacinaService::class.java)
        }
    }
}