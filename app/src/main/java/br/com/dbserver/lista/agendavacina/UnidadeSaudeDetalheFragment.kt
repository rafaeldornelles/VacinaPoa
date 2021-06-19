package br.com.dbserver.lista.agendavacina


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.dbserver.lista.agendavacina.databinding.FragmentUnidadeSaudeDetalheBinding
import br.com.dbserver.lista.agendavacina.model.UnidadeSaude
import br.com.dbserver.lista.agendavacina.ui.unidades_saude.CHAVE_UNIDADE_SAUDE_EXTRA

/**
 * A simple [Fragment] subclass.
 */
class UnidadeSaudeDetalheFragment : Fragment() {

    val unidade: UnidadeSaude? by lazy {
        arguments?.getSerializable(CHAVE_UNIDADE_SAUDE_EXTRA) as? UnidadeSaude
    }

    lateinit var binding: FragmentUnidadeSaudeDetalheBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUnidadeSaudeDetalheBinding.inflate(inflater, container, false)
        binding.unidadeSaude = unidade
        binding.detalheUnidadeSaudeButtonDirecoes.setOnClickListener { openMap() }
        binding.lifecycleOwner = this
        return binding.root
    }

    fun openMap(){
        unidade?.endereco?.let {
            val intent = Intent(Intent.ACTION_VIEW,
            Uri.parse("google.navigation:q=${unidade?.endereco}"))
            startActivity(intent)
        } ?: Toast.makeText(context, "Unidade não tem endereço cadastrado", Toast.LENGTH_LONG).show()
    }


}
