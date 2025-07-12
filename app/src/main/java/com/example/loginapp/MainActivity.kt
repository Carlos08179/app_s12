package com.example.loginapp

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var minLengthText: TextView
    private lateinit var upperCaseText: TextView
    private lateinit var numberText: TextView
    private lateinit var specialCharText: TextView

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setupTextWatchers()
        setupObservers()
        setupLoginButton()
    }

    private fun initViews() {
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)
        minLengthText = findViewById(R.id.minLengthText)
        upperCaseText = findViewById(R.id.upperCaseText)
        numberText = findViewById(R.id.numberText)
        specialCharText = findViewById(R.id.specialCharText)
    }

    private fun setupTextWatchers() {
        emailEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                loginViewModel.setEmail(s.toString())
            }
        })

        passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                loginViewModel.setPassword(s.toString())
            }
        })
    }

    private fun setupObservers() {
        loginViewModel.hasMinLength.observe(this, Observer { isValid ->
            updateValidationText(minLengthText, isValid)
        })

        loginViewModel.hasUpperCase.observe(this, Observer { isValid ->
            updateValidationText(upperCaseText, isValid)
        })

        loginViewModel.hasNumber.observe(this, Observer { isValid ->
            updateValidationText(numberText, isValid)
        })

        loginViewModel.hasSpecialChar.observe(this, Observer { isValid ->
            updateValidationText(specialCharText, isValid)
        })

        loginViewModel.formIsValid.observe(this, Observer { isValid ->
            loginButton.isEnabled = isValid
        })
    }

    private fun updateValidationText(textView: TextView, isValid: Boolean) {
        if (isValid) {
            textView.setTextColor(Color.GREEN)
        } else {
            textView.setTextColor(Color.RED)
        }
    }

    private fun setupLoginButton() {
        loginButton.setOnClickListener {
            if (loginViewModel.formIsValid.value == true) {
                loginViewModel.login()
                Toast.makeText(this, "¡Login exitoso!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}