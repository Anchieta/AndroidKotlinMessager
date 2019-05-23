package com.example.kotlinmessager

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gravar_button.setOnClickListener {
            val nome = nome_editText.text.toString()
            val email = email_editText.text.toString()
            val senha = senha_editText.text.toString()

            Log.d( "MainActivity", "Nome: $nome")
            Log.d("MainActivity", "Email: " + email)
            Log.d( "MainActivity",  "Senha: $senha")
        } //btn

        ja_tem_conta_textView.setOnClickListener {
            Log.d("MainActivity", "Tente ver a tela de login")
        } //texto click

    } //onCreate
} //class
