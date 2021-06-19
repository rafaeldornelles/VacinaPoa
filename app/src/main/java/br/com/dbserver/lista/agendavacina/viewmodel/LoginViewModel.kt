package br.com.dbserver.lista.agendavacina.viewmodel

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import br.com.dbserver.lista.agendavacina.utils.Validators

class LoginViewModel: ViewModel() {
    val email: MutableLiveData<String> = MutableLiveData()
    val password: MutableLiveData<String> = MutableLiveData()

    val canSubmit: MediatorLiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        value = false
        addSource(email){value = formIsValid()}
        addSource(password){value = formIsValid()}

    }
    fun formIsValid(): Boolean{
        return listOf(Validators.emailValidator(email.value?:""), Validators.passwordValidator(password.value?:"")).all { it == null }
    }
}
