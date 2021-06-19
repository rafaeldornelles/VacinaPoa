package br.com.dbserver.lista.agendavacina


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.dbserver.lista.agendavacina.adapter.GrupoPrioritarioAdapter
import br.com.dbserver.lista.agendavacina.databinding.FragmentGrupoPrioritarioBinding
import br.com.dbserver.lista.agendavacina.model.populated.UsuarioPopulado
import br.com.dbserver.lista.agendavacina.ui.agendamento.CHAVE_USUARIO_EXTRA
import br.com.dbserver.lista.agendavacina.viewmodel.GrupoPrioritarioViewModel
import java.io.Serializable

/**
 * A simple [Fragment] subclass.
 */
val CHAVE_GRUPOS_PRIORITARIOS = "gruposprioritarios"

class GrupoPrioritarioFragment : Fragment() {

    val viewModel by lazy {
        ViewModelProvider(this)
            .get(GrupoPrioritarioViewModel::class.java)
    }


    val comorbidadeAdapter by lazy {
        GrupoPrioritarioAdapter(viewModel.gruposPrioritarios.value ?: emptyList())
    }

    lateinit var binding : FragmentGrupoPrioritarioBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val usuario = arguments?.getSerializable(CHAVE_USUARIO_EXTRA) as UsuarioPopulado
        viewModel.usuario.value = usuario
        binding = FragmentGrupoPrioritarioBinding.inflate(inflater, container, false)
        binding.fragmentGruposPrioritariosRecyclerView.adapter = comorbidadeAdapter
        binding.lifecycleOwner = this
        viewModel.gruposPrioritarios.observe(this, Observer {
            comorbidadeAdapter.gruposPrioritarios = it.filterNot { it -> it.id == 1 }
            comorbidadeAdapter.notifyDataSetChanged()
        })

        binding.fragmentGruposPrioritariosButtonContinuar.setOnClickListener {
            onNextClick()
        }

        return binding.root
    }

    fun onNextClick(){
        val usuario = viewModel.usuario.value
        if (!usuario?.comorbidades.isNullOrEmpty() && usuario?.comorbidades!!.isNotEmpty()){
            viewModel.gruposPrioritariosUsuario.add(viewModel.gruposPrioritarios.value!!.first())
        }
        viewModel.gruposPrioritariosUsuario.addAll(comorbidadeAdapter.getSelectedItens())
        Bundle().apply {
            putSerializable(CHAVE_GRUPOS_PRIORITARIOS, viewModel.gruposPrioritariosUsuario as Serializable)
            putSerializable(CHAVE_USUARIO_EXTRA, viewModel.usuario.value)
            findNavController().navigate(R.id.action_grupoPrioritarioFragment_to_grupoVacinacaoFragment, this)
        }
    }


}
