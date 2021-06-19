package br.com.dbserver.lista.agendavacina

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.dbserver.lista.agendavacina.databinding.FragmentGrupoVacinacaoBinding
import br.com.dbserver.lista.agendavacina.model.GrupoPrioritario
import br.com.dbserver.lista.agendavacina.model.Usuario
import br.com.dbserver.lista.agendavacina.model.populated.UsuarioPopulado
import br.com.dbserver.lista.agendavacina.ui.agendamento.CHAVE_USUARIO_EXTRA
import br.com.dbserver.lista.agendavacina.viewmodel.GruposVacinacaoViewModel


class GrupoVacinacaoFragment : Fragment() {

    lateinit var binding: FragmentGrupoVacinacaoBinding

    val viewmodel by lazy {
        ViewModelProvider(this).get(GruposVacinacaoViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGrupoVacinacaoBinding.inflate(inflater, container, false)
        binding.viewmodel = viewmodel
        binding.lifecycleOwner = this

        arguments?.let {
            viewmodel.usuario.value = it.getSerializable(CHAVE_USUARIO_EXTRA) as UsuarioPopulado
            viewmodel.gruposprioritariosUsuario.value = it.getSerializable(CHAVE_GRUPOS_PRIORITARIOS) as List<GrupoPrioritario>

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel.gruposVacinacao.observe(this, Observer{
            viewmodel.grupoVacinacaoUsuario.value = it.sortedBy { it.dataInicioVacinaCao }.firstOrNull {gv ->
                gv.idadeMin <= viewmodel.usuario.value!!.usuario!!.getIdade() &&
                        (gv.grupoPrioritarioId == null || viewmodel.gruposprioritariosUsuario.value!!.any { it!!.id == gv.grupoPrioritarioId })
            }
            val value = viewmodel.grupoVacinacaoUsuario.value
            val gruposusuario = viewmodel.gruposprioritariosUsuario.value
            val gruposusuario2 = viewmodel.gruposVacinacao.value
            Log.i("SSSS", "")
        })

    }


}
