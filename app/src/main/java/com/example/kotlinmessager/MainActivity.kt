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
            performRegister()
        } //btn

        ja_tem_conta_textView.setOnClickListener() {
            Log.d("MainAtivicty", "Tentando mostrar a activity de login")

            //Executa a activity de login
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    } //onCreate

    private fun performRegister() {

        val nome = nome_editText.text.toString()
        val email = email_editText.text.toString()
        val senha = senha_editText.text.toString()

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            Toast.makeText(baseContext, "Por favor, verifique se os campos: \'Nome\', \'Email\' e \'Senha\'", Toast.LENGTH_LONG).show()
            return
        }

        Toast.makeText(baseContext, "Nome: $nome \n\nEmail: $email \n\nSenha: $senha", Toast.LENGTH_LONG).show()

        //Autenticando no Firebase
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener {
                if (!it.isSuccessful) {
                    Toast.makeText(baseContext, "O registro não foi gravado!!!", Toast.LENGTH_LONG).show()
                    return@addOnCompleteListener
                }
                //Else
                Toast.makeText(baseContext, "Registro gravado com sucesso!!!", Toast.LENGTH_LONG).show()
            } //.addOnCompleteListener
            .addOnFailureListener {
                Toast.makeText(baseContext, "Falha ao tentar criar o usuário. Erro: ${it.message}", Toast.LENGTH_SHORT).show()
            } //.addOnFailureListener
    }//performRegister


} //class
