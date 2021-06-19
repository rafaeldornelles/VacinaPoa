package br.com.dbserver.lista.agendavacina


import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.dbserver.lista.agendavacina.databinding.FragmentLoginBinding
import br.com.dbserver.lista.agendavacina.viewmodel.LoginViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {
    val auth = Firebase.auth
    lateinit var binding: FragmentLoginBinding
    val loginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        checkUser()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.callback = this
        binding.viewModel = loginViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    fun signIn(){
        auth.signInWithEmailAndPassword(loginViewModel.email.value?:"", loginViewModel.password.value?:"")
            .addOnSuccessListener {
                checkUser()
            }.addOnFailureListener{
                AlertDialog.Builder(context)
                    .setTitle("Erro ao fazer login")
                    .setMessage("Usuário ou senha inválidos")
                    .setPositiveButton("Ok", null)
                    .create()
                    .show()
            }
    }

    fun register(){
        findNavController().navigate(R.id.action_loginFragment2_to_cadastroFragment)
    }

    fun checkUser(){
        if(auth.currentUser != null){
            Intent(context, VacinaPoaActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(this)
            }
        }
    }
}
