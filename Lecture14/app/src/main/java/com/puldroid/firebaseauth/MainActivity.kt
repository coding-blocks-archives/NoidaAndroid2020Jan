package com.puldroid.firebaseauth

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val auth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun submit(view: View) {
        val email = email.text.toString()
        val pass = pass.text.toString()
        if (email.isEmpty() || pass.isEmpty()) {
            return
        } else {
//            auth.signInWithEmailAndPassword()
            auth.createUserWithEmailAndPassword(email, pass)
                    .addOnSuccessListener {
                        //Save User Name to FirebaseAuth
                        val userProfileChangeRequest = UserProfileChangeRequest.Builder()
                                .setDisplayName(username.text.toString())
                                .build()
                        it.user?.updateProfile(userProfileChangeRequest)


                        Toast.makeText(this, it.user?.displayName, Toast.LENGTH_LONG).show()
                    }.addOnFailureListener {
                        Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
                    }
        }
    }
}
