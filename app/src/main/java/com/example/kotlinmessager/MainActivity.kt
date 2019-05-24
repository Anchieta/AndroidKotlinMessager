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

            if (email.isEmpty() || senha.isEmpty()) return@setOnClickListener

            Log.d( "MainActivity", "Nome: $nome")
            Log.d("MainActivity", "Email: " + email)
            Log.d( "MainActivity",  "Senha: $senha")

            Toast.makeText(baseContext, "Nome: $nome \n\nEmail: $email \n\nSenha: $senha",
                Toast.LENGTH_SHORT).show()

            //Autenticando no Firebase
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener {
                if (!it.isSuccessful) {
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                    return@addOnCompleteListener
                }
                //else - Se for sucesso
                Toast.makeText(baseContext, "Usuário criado com sucesso!\\n\\nuid: ${it.result?.user?.uid}",
                    Toast.LENGTH_SHORT).show()
                Log.d("Main", "Usuário criado com sucesso!\n\nuid: ${it.result?.user?.uid}")
            }
        } //btn

        ja_tem_conta_textView.setOnClickListener {
            Log.d("MainActivity", "Tente ver a tela de login")

            //launch the login activity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        } //texto click

    } //onCreate
} //class
