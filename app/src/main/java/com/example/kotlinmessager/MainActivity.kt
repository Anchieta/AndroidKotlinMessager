package com.example.kotlinmessager

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import android.widget.Toast.makeText
import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import java.util.*


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
        } //ja_tem_conta

        selectphoto_button.setOnClickListener {

           // Toast.makeText(this, "Tentando carregar photo", Toast.LENGTH_LONG).show()

            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)

        } //selectphoto

    } //onCreate

    val selectedPhotoUri: Uri? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            Toast.makeText(this, "Foto selecionada", Toast.LENGTH_LONG).show()

            val selectedPhotoUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)

            val bitmapDrawable = BitmapDrawable(bitmap)
            selectphoto_button.setBackgroundDrawable(bitmapDrawable)

        } //if
    }

    private fun performRegister() {

        val nome = nome_editText.text.toString()
        val email = email_editText.text.toString()
        val senha = senha_editText.text.toString()

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            Toast.makeText(baseContext, "Por favor, verifique se os campos: \'Nome\', \'Email\' e \'Senha\'", Toast.LENGTH_LONG).show()
            return
        }

        //Toast.makeText(baseContext, "Nome: $nome \n\nEmail: $email \n\nSenha: $senha", Toast.LENGTH_LONG).show()

        //Autenticando no Firebase
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener {
                if (!it.isSuccessful) {
                    makeText(baseContext, "O registro não foi gravado!!!", Toast.LENGTH_LONG).show()
                    return@addOnCompleteListener
                }
                //Else
                Toast.makeText(baseContext, "Registro gravado com sucesso!!!", Toast.LENGTH_LONG).show()
                uploadImageToFirebaseStorage()
            } //.addOnCompleteListener
            .addOnFailureListener {
                Toast.makeText(baseContext, "Falha ao tentar criar o usuário. Erro: ${it.message}", Toast.LENGTH_LONG).show()
            } //.addOnFailureListener
    }//performRegister

    private fun uploadImageToFirebaseStorage() {
        if (selectedPhotoUri == null) return

        val fileName = UUID.randomUUID().toString()
        val ref = FirebaseStorage.getInstance().getReference("kotlinmessager/images/$fileName")

        ref.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                Toast.makeText(baseContext, "Imagem: ${it.metadata?.path} uploaded com sucesso!", Toast.LENGTH_LONG).show()
                Log.d("MainActivity", "Imagem: ${it.metadata?.path} uploaded com sucesso!")
            }

    } // uploadImageToFirebaseStorage()


} //class

