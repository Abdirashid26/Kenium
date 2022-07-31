package com.example.kenium.repo

import com.google.firebase.auth.FirebaseAuth

class FirebaseRepo {

    lateinit var firebaseAuth: FirebaseAuth

    init{
        firebaseAuth = FirebaseAuth.getInstance()
    }

}