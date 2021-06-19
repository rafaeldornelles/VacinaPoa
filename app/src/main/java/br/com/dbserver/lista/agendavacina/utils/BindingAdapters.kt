package br.com.dbserver.lista.agendavacina.utils

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object BindingAdapters {
    @BindingAdapter("emailValidator")
    @JvmStatic
    fun setEmail(input: TextInputLayout, email: String) {
        input.error = with(Validators.emailValidator(email)){
            if (input.isDirty && this != null) this else null
        }
    }

    @BindingAdapter("passwordValidator")
    @JvmStatic
    fun setSenha(input: TextInputLayout, password: String) {
        input.error = with(Validators.passwordValidator(password)){
            if (input.isDirty && this != null) this else null
        }
    }

    @BindingAdapter("nameValidator")
    @JvmStatic
    fun setName(input: TextInputLayout, name: String) {
        input.error = with(Validators.nameValidator(name)) {
            if (input.isDirty && this != null) this else null
        }
    }

    @BindingAdapter("cpfValidator")
    @JvmStatic
    fun setCpf(input: TextInputLayout, cpf: String) {
        input.error = with(Validators.cpfValidator(cpf)) {
            if (input.isDirty && this!= null) this else null
        }
    }


    @BindingAdapter("android:text")
    @JvmStatic
    fun setText(input: TextInputEditText, date: LocalDate?) {
        val format = date?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        if (input.text.toString() == format || format == null) return
            input.setText(format)

    }

    @InverseBindingAdapter(attribute = "android:text")
    @JvmStatic fun getText(view: TextInputEditText) : LocalDate? {
        var dateString = view.text.toString()
        return try {
            LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        }catch (e: DateTimeParseException){
            null
        }
    }

    @BindingAdapter("birthDateValidator")
    @JvmStatic
    fun setBirthDate(input: TextInputLayout, birthDate: LocalDate?) {
        input.error = with(Validators.birthDateValidator(birthDate)) {
            if (input.isDirty && this!= null) this else null
        }
    }
}