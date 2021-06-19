package br.com.dbserver.lista.agendavacina

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigator
import androidx.navigation.fragment.findNavController
import br.com.dbserver.lista.agendavacina.adapter.ComorbidadeAdapter
import br.com.dbserver.lista.agendavacina.databinding.FragmentComorbidadesBinding
import br.com.dbserver.lista.agendavacina.model.populated.UsuarioPopulado
import br.com.dbserver.lista.agendavacina.ui.agendamento.CHAVE_USUARIO_EXTRA
import br.com.dbserver.lista.agendavacina.viewmodel.ComorbidadeViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ComorbidadesFragment : Fragment() {
    val viewModel by lazy {
        ViewModelProvider(this)
            .get(ComorbidadeViewModel::class.java)
    }

    val compositeDisposable = CompositeDisposable()


    val comorbidadeAdapter by lazy {
        ComorbidadeAdapter(viewModel.comorbidades.value ?: emptyList())
    }

    lateinit var binding : FragmentComorbidadesBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.usuario.value = arguments?.getSerializable(CHAVE_USUARIO_EXTRA) as? UsuarioPopulado
        binding = FragmentComorbidadesBinding.inflate(inflater, container, false)
        binding.fragmentComorbidadesRecyclerView.adapter = comorbidadeAdapter
        binding.handler = this
        binding.lifecycleOwner = this
        viewModel.comorbidades.observe(this, Observer {
            comorbidadeAdapter.comorbidades = it
            comorbidadeAdapter.notifyDataSetChanged()
        })
        return binding.root
    }

    fun handleButtonClick(){
        Log.i("AAA", "clickedd")
        var selectedItens = comorbidadeAdapter.getSelectedItens()
        compositeDisposable.add(viewModel.cadastrarComorbidades(viewModel.usuario.value!!, selectedItens)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.i("AAAA", "completed")
                //findNavController().navigate(R.id.action_comorbidadesFragment2_to_grupoPrioritarioFragment)
            }, {
                Log.i("AAAA", "Error")
                Log.e("DEBUG", "Erro ao cadastrar: ${it.message}")
            })

        )
        Bundle().apply {
            viewModel.usuario.value = viewModel.usuario.value?.apply {
                comorbidades = selectedItens
            }
            putSerializable(CHAVE_USUARIO_EXTRA, viewModel.usuario.value)
            findNavController().navigate(R.id.action_comorbidadesFragment2_to_grupoPrioritarioFragment, this)
        }

    }


}
