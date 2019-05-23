package com.example.kotlinmessager

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        login_button.setOnClickListener {
            val email = email_editText.text.toString()
            val senha = senha_editText.text.toString()

            Log.d("Login", "Email: $email e Senha: $senha")
        } //login

        voltar_textView.setOnClickListener {
            finish()
        }


    } //onCreate

}//class