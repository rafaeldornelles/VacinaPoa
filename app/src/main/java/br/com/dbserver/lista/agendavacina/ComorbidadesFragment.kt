package br.com.dbserver.lista.agendavacina

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.dbserver.lista.agendavacina.adapter.ComorbidadeAdapter
import br.com.dbserver.lista.agendavacina.databinding.FragmentComorbidadesBinding
import br.com.dbserver.lista.agendavacina.viewmodel.ComorbidadeViewModel

class ComorbidadesFragment : Fragment() {
    val viewModel by lazy {
        ViewModelProvider(this)
            .get(ComorbidadeViewModel::class.java)
    }


    val comorbidadeAdapter by lazy {
        ComorbidadeAdapter(viewModel.comorbidades.value ?: emptyList())
    }

    lateinit var binding : FragmentComorbidadesBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentComorbidadesBinding.inflate(inflater, container, false)
        binding.fragmentComorbidadesRecyclerView.adapter = comorbidadeAdapter
        binding.lifecycleOwner = this
        viewModel.comorbidades.observe(this, Observer {
            comorbidadeAdapter.comorbidades = it
            comorbidadeAdapter.notifyDataSetChanged()
        })
        return binding.root
    }



}
