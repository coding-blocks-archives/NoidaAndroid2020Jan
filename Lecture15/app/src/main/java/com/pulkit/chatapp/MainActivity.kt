package com.pulkit.chatapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.AuthResult
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

        auth.addAuthStateListener {
            if(it.currentUser != null){
                startActivity(Intent(this,ChatActivity::class.java))
            }
        }
    }

    fun submit(view: View) {
        val email = email.text.toString()
        val pass = pass.text.toString()
        if (email.isEmpty() || pass.isEmpty()) {
            return
        } else {
            auth.createUserWithEmailAndPassword(email, pass)
                    .addOnSuccessListener {
                        saveName(it)
                        Toast.makeText(this, "Sign up With " + it.user?.displayName, Toast.LENGTH_LONG).show()
                        startActivity(Intent(this,ChatActivity::class.java))
                    }.addOnFailureListener {
                        if (it.localizedMessage.contains("already", true)) {
                            login(email, pass)
                        } else {
                            Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
                        }
                    }
        }
    }

    private fun login(email: String, pass: String) {
        auth.signInWithEmailAndPassword(email, pass)
                .addOnSuccessListener {
                    saveName(it)
                    Toast.makeText(this, "Login With " + it.user?.displayName, Toast.LENGTH_LONG).show()
                    startActivity(Intent(this,ChatActivity::class.java))
                }.addOnFailureListener {
                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
                }
    }

    private fun saveName(it: AuthResult) {
        //Save Name to Firebase Auth
        val userProfileChangeRequest = UserProfileChangeRequest.Builder()
                .setDisplayName(username.text.toString())
                .build()
        it.user?.updateProfile(userProfileChangeRequest)
    }
}
