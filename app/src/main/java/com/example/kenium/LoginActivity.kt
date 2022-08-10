package com.example.kenium

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.kenium.repo.FirebaseRepo
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {

//    FirebaseRepo Initialization
    private val firebaseRepo : FirebaseRepo  = FirebaseRepo()
    private lateinit var txtEmail : TextInputLayout
    private lateinit var txtPassword: TextInputLayout
    private lateinit var btnLogin : MaterialButton
    private lateinit var progressCircular : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        check if the user is logged in ?
//        if yes then take them to the Home activity
        if(firebaseRepo.firebaseAuth.currentUser != null){
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        txtEmail = findViewById(R.id.Email)
        txtPassword = findViewById(R.id.Password)
        btnLogin = findViewById(R.id.loginBtn)
        progressCircular = findViewById(R.id.progress_circular)

        btnLogin.setOnClickListener {
            var email = txtEmail.editText?.text
            var password = txtPassword.editText?.text

            if(email?.isBlank() == true || password?.isBlank() == true){
                Snackbar.make(it,"Please Enter Username/Password",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            progressCircular.visibility = View.VISIBLE

            firebaseRepo.firebaseAuth.signInWithEmailAndPassword(email.toString(),
                password.toString()
            ).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Snackbar.make(it,"Welcome",Snackbar.LENGTH_SHORT).show()
                    progressCircular.visibility = View.INVISIBLE
                    val intent = Intent(this,HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Snackbar.make(it,"Wrong Username/Password",Snackbar.LENGTH_SHORT).show()
                    progressCircular.visibility = View.INVISIBLE
                }
            }



        }



    }
}