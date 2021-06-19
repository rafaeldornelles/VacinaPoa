package br.com.dbserver.lista.agendavacina.ui.unidades_saude

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import br.com.dbserver.lista.agendavacina.R
import br.com.dbserver.lista.agendavacina.adapter.UnidadeSaudeAdapter
import br.com.dbserver.lista.agendavacina.databinding.FragmentUnidadesSaudeBinding
import br.com.dbserver.lista.agendavacina.model.UnidadeSaude
import br.com.dbserver.lista.agendavacina.viewmodel.UnidadeSaudeViewModel

val CHAVE_UNIDADE_SAUDE_EXTRA = "unidadeSaude"
class UnidadeSaudeFragment : Fragment(), UnidadeSaudeAdapter.CardClickHandler {


    override fun onCardClick(us:UnidadeSaude) {
        val bundle = Bundle().apply {
            putSerializable(CHAVE_UNIDADE_SAUDE_EXTRA, us)
        }
        findNavController().navigate(R.id.action_unidade_saude_to_unidadeSaudeDetalheFragment, bundle)
    }

    private val viewModel by lazy {
        ViewModelProvider(this).get(UnidadeSaudeViewModel::class.java)
    }

    private lateinit var binding: FragmentUnidadesSaudeBinding
    private val adapter = UnidadeSaudeAdapter(mutableListOf(), this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUnidadesSaudeBinding.inflate(layoutInflater, container, false)
        binding.fragmentUnidadesSaudeRecyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUnidadesSaude().observe(this){
            Log.i("AAAA", it.toString())
            adapter.unidadesSaude = it
            adapter.notifyDataSetChanged()
        }
    }
}