package com.example.kotlinmessager

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        //updateUI(currentUser)

        gravar_button.setOnClickListener {
            val nome = nome_editText.text.toString()
            val email = email_editText.text.toString()
            val senha = senha_editText.text.toString()

            Log.d( "MainActivity", "Nome: $nome")
            Log.d("MainActivity", "Email: " + email)
            Log.d( "MainActivity",  "Senha: $senha")

            //Autenticando no Firebase
            /*
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener {
                if (!it.isSuccessful) {
                    Log.d("Main", "Erro!!!!")

                    return@addOnCompleteListener
                }


                //else - Se for sucesso
                Log.d("Main", "Usuário criado com sucesso!\n\nuid: ${it.result?.user?.uid}")
            }
            */

            auth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("Main", "createUserWithEmail:success")
                        val user = auth.currentUser
                        //updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("Main", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        //updateUI(null)
                    }

                    // ...
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
