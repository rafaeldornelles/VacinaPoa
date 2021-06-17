package br.com.dbserver.lista.agendavacina


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

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    val loginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
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
        Log.d("AAAA", loginViewModel.email.value.toString())
        Log.d("AAAA", "signing in....")
        loginViewModel.email.value = "rafafd@hotmail.com"
        Log.d("AAAA", loginViewModel.email.value.toString())
    }

    fun register(){
        findNavController().navigate(R.id.action_loginFragment2_to_cadastroFragment)
    }
}
