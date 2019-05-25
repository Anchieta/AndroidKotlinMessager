package com.example.kotlinmessager

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gravar_button.setOnClickListener {
            val nome = nome_editText.text.toString()
            val email = email_editText.text.toString()
            val senha = senha_editText.text.toString()

            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                Toast.makeText(baseContext, "Por favor, verifique se os campos: \'Nome\', \'Email\' e \'Senha\'", Toast.LENGTH_SHORT).show()

                return@setOnClickListener
            }

            Toast.makeText(baseContext, "Nome: $nome \n\nEmail: $email \n\nSenha: $senha", Toast.LENGTH_SHORT).show()

            //Autenticando no Firebase
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener {
                    if (!it.isSuccessful) {
                        Toast.makeText(baseContext, "Registro ${it.result?.user?.uid} gravado com sucesso!", Toast.LENGTH_SHORT).show()

                        return@addOnCompleteListener
                    } //if (!it.isSuccessful)
                } //.addOnCompleteListener
                .addOnFailureListener {
                    Toast.makeText(baseContext, "Falha ao tentar criar o usuário. Erro: ${it.message}", Toast.LENGTH_SHORT).show()
                } //.addOnFailureListener
            } //FirebaseAuth.getInstance()
        } //btn
    } //onCreate


} //class
