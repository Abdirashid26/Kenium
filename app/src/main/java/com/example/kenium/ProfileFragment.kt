package com.example.kenium

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProfileFragment : Fragment() {

    lateinit var editButton : MaterialButton
    lateinit var logoutButton: MaterialButton
    private val firebaseAuth = FirebaseAuth.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_profile, container, false)

        editButton = v.findViewById(R.id.editUser);
        logoutButton = v.findViewById(R.id.logOut);

        logoutButton.setOnClickListener {
            firebaseAuth.signOut()
            Intent(requireContext(),LoginActivity::class.java).apply {
                startActivity(this)

            }

            activity?.finish()
        }


        return v
    }


}