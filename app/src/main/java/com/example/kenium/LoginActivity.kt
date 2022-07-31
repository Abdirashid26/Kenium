package com.example.kenium

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kenium.repo.FirebaseRepo

class LoginActivity : AppCompatActivity() {

//    FirebaseRepo Initialization
    private val firebaseRepo : FirebaseRepo  = FirebaseRepo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        check if the user is logged in ?
//        if yes then take them to the Home activity
        if(firebaseRepo.firebaseAuth.currentUser != null){
            val intent = Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }


    }
}