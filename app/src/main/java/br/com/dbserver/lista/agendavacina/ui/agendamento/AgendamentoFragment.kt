package br.com.dbserver.lista.agendavacina.ui.agendamento

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import br.com.dbserver.lista.agendavacina.R
import br.com.dbserver.lista.agendavacina.databinding.FragmentAgendamentoBinding

val CHAVE_USUARIO_EXTRA = "usuarioextra"
class AgendamentoFragment : Fragment() {

    private val viewModel: AgendamentoViewModel by lazy {
        ViewModelProvider(this).get(AgendamentoViewModel::class.java)
    }

    lateinit var binding: FragmentAgendamentoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAgendamentoBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.handler = this
        binding.lifecycleOwner = this
        return binding.root
    }

    fun onAgendarClick(){
        Log.i("AAAA", "clicked")
        viewModel.usuario?.value?.let {
            Bundle().apply {
                putSerializable(CHAVE_USUARIO_EXTRA, it)
                findNavController().navigate(R.id.action_navigation_dashboard_to_comorbidadesFragment2, this)
            }
        }
    }
}