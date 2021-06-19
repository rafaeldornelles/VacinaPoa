package br.com.dbserver.lista.agendavacina


import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.dbserver.lista.agendavacina.databinding.FragmentCadastroBinding
import br.com.dbserver.lista.agendavacina.viewmodel.CadastroViewModel
import br.com.dbserver.lista.agendavacina.viewmodel.LoginViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * A simple [Fragment] subclass.
 */
class CadastroFragment : Fragment() {
    lateinit var binding: FragmentCadastroBinding
    val viewModel: CadastroViewModel by lazy{
        ViewModelProvider(this).get(CadastroViewModel::class.java)
    }

    val compositeDisposable = CompositeDisposable()

    val auth = Firebase.auth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCadastroBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.listener = this
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentCadastroDateofbithTextinput.editText?.apply {
            setOnFocusChangeListener { view, b ->
                if (b) viewModel.showDatePicker(childFragmentManager)
            }
            setOnClickListener {
                viewModel.showDatePicker(childFragmentManager)
            }
            setRawInputType(InputType.TYPE_NULL)
        }
    }

    fun cadastrarUsuario() {
        val usuario = viewModel.getUsuario()
        auth.createUserWithEmailAndPassword(usuario.email, usuario.senha)
            .addOnSuccessListener {
                val user = auth.currentUser
                Log.i("AAAA", user?.email.toString())
                usuario.id = user?.uid ?: ""
                usuario.senha = ""

                compositeDisposable.add(viewModel.insertUsuario(usuario)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.i("AAAA", "hey")
                        AlertDialog.Builder(context)
                            .setTitle("Sucesso")
                            .setMessage("Usuário ${usuario.nome} cadastrado com sucesso.")
                            .setPositiveButton("Ok",null)
                            .create()
                            .show()
                        findNavController().navigateUp()
                    }, {
                        Log.i("AAAA", "hey: ${it.message}")
                        AlertDialog.Builder(context)
                            .setTitle("Erro")
                            .setMessage("Erro ao cadastrar Usuário: ${it.message}")
                            .setPositiveButton("Ok",null)
                            .create()
                            .show()
                    })
                )
            }
            .addOnFailureListener {
                Toast.makeText(context!!, "Erro ao cadastrar: ${it.message}", Toast.LENGTH_LONG)
                    .show()
            }
    }


    override fun onPause() {
        super.onPause()
        compositeDisposable.clear()
    }



}
