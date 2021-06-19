package br.com.dbserver.lista.agendavacina.viewmodel

import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.dbserver.lista.agendavacina.repository.UsuarioRepository
import br.com.dbserver.lista.agendavacina.model.Usuario
import br.com.dbserver.lista.agendavacina.utils.Validators
import com.google.android.material.datepicker.MaterialDatePicker
import io.reactivex.Completable
import java.time.*

class CadastroViewModel: ViewModel() {
    val email: MutableLiveData<String> = MutableLiveData("")
    val password: MutableLiveData<String> = MutableLiveData("")
    val name: MutableLiveData<String> = MutableLiveData("")
    val cpf: MutableLiveData<String> = MutableLiveData("")
    val birthdate: MutableLiveData<LocalDate?> = MutableLiveData(LocalDate.now().minusYears(18))
    val usuarioRepository= UsuarioRepository()
    val datePicker: MaterialDatePicker<Long> by lazy {
        MaterialDatePicker.Builder.datePicker()
            .setSelection(LocalDate.now().minusYears(18).atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli())
            .build()
    }

    val canSubmit = MediatorLiveData<Boolean>().apply {
        value = false
        addSource(email){value = formIsValid()}
        addSource(password){value = formIsValid()}
        addSource(name){value = formIsValid()}
        addSource(cpf){value = formIsValid()}
        addSource(birthdate){value = formIsValid()}
    }

    fun formIsValid(): Boolean{
        Log.i("AAAA", "changed")

        return listOf(
            Validators.emailValidator(email.value),
            Validators.passwordValidator(password.value),
            Validators.nameValidator(name.value),
            Validators.cpfValidator(cpf.value),
            Validators.birthDateValidator(birthdate.value))
            .all { it == null }
    }

    fun showDatePicker(fm: FragmentManager){
        Log.i("AAAA", "clicked")
        datePicker.addOnPositiveButtonClickListener {
            birthdate.value = LocalDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneId.of("UTC")).toLocalDate()
        }
        datePicker.show(fm, "datepicker")
    }

    fun getUsuario(): Usuario{
        return Usuario("", name.value!!, password.value!!, email.value!!, cpf.value!!, birthdate.value!!, null, null, null)
    }

    fun insertUsuario(u: Usuario) : Completable{
        return usuarioRepository.inserir(u)
    }

}