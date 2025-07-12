package com.example.loginapp

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _email = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()

    val email: LiveData<String> = _email
    val password: LiveData<String> = _password

    // Validación del email
    val isEmailValid: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(_email) { email ->
            value = email?.let { Patterns.EMAIL_ADDRESS.matcher(it).matches() } ?: false
        }
    }

    // Validaciones de contraseña
    val hasMinLength: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(_password) { password ->
            value = password?.length ?: 0 >= 8
        }
    }

    val hasUpperCase: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(_password) { password ->
            value = password?.any { it.isUpperCase() } ?: false
        }
    }

    val hasNumber: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(_password) { password ->
            value = password?.any { it.isDigit() } ?: false
        }
    }

    val hasSpecialChar: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(_password) { password ->
            value = password?.any { "@#$%&*!".contains(it) } ?: false
        }
    }

    // Validación completa del formulario
    val formIsValid: LiveData<Boolean> = MediatorLiveData<Boolean>().apply {
        addSource(isEmailValid) { value = checkFormValidity() }
        addSource(hasMinLength) { value = checkFormValidity() }
        addSource(hasUpperCase) { value = checkFormValidity() }
        addSource(hasNumber) { value = checkFormValidity() }
        addSource(hasSpecialChar) { value = checkFormValidity() }
    }

    private fun checkFormValidity(): Boolean {
        return isEmailValid.value == true &&
                hasMinLength.value == true &&
                hasUpperCase.value == true &&
                hasNumber.value == true &&
                hasSpecialChar.value == true
    }

    fun setEmail(email: String) {
        _email.value = email
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun login() {
        // Aquí iría la lógica de autenticación real
        // Por ahora solo mostramos un mensaje
    }
}