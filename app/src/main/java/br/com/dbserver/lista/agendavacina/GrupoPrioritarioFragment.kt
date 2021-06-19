package br.com.dbserver.lista.agendavacina


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.dbserver.lista.agendavacina.adapter.GrupoPrioritarioAdapter
import br.com.dbserver.lista.agendavacina.databinding.FragmentGrupoPrioritarioBinding
import br.com.dbserver.lista.agendavacina.viewmodel.GrupoPrioritarioViewModel

/**
 * A simple [Fragment] subclass.
 */
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
        binding = FragmentGrupoPrioritarioBinding.inflate(inflater, container, false)
        binding.fragmentGruposPrioritariosRecyclerView.adapter = comorbidadeAdapter
        binding.lifecycleOwner = this
        viewModel.gruposPrioritarios.observe(this, Observer {
            comorbidadeAdapter.gruposPrioritarios = it.filterNot { it -> it.id == 1 }
            comorbidadeAdapter.notifyDataSetChanged()
        })
        return binding.root
    }


}
