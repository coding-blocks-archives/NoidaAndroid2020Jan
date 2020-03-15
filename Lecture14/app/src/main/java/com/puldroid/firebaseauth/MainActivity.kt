package com.puldroid.firebaseauth

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    val auth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    lateinit var storedVerificationId: String

    fun submit(view: View) {
        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Log.d("TAG", "onVerificationCompleted:$credential")
                signInWithPhoneAuthCredential(credential)
            }
            override fun onVerificationFailed(e: FirebaseException) {
                Log.w("TAG", "onVerificationFailed", e)
                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                } else if (e is FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                }
            }
            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken
            ) {
                Log.d("TAG", "onCodeSent:$verificationId")
                storedVerificationId = verificationId
                var resendToken = token
            }
        }
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91${number.text}", // Phone number to verify
                60, // Timeout duration
                TimeUnit.SECONDS, // Unit of timeout
                this, // Activity (for callback binding)
                callbacks)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d("TAG", "signInWithCredential:success")
                    } else {
                        Log.w("TAG", "signInWithCredential:failure", task.exception)
                        if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        }
                    }
                }
    }

    fun VerifyOtp(view: View) {
        val credential = PhoneAuthProvider.getCredential(storedVerificationId, pass.text.toString())
        signInWithPhoneAuthCredential(credential)

    }

//    fun submit(view: View) {
//        val email = email.text.toString()
//        val pass = pass.text.toString()
//        if (email.isEmpty() || pass.isEmpty()) {
//            return
//        } else {
//            auth.createUserWithEmailAndPassword(email, pass)
//                    .addOnSuccessListener {
//                        saveName(it)
//                        Toast.makeText(this, "Sign up With " + it.user?.displayName, Toast.LENGTH_LONG).show()
//                    }.addOnFailureListener {
//                        if (it.localizedMessage.contains("already", true)) {
//                            login(email, pass)
//                        } else {
//                            Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
//                        }
//                    }
//        }
//    }
//
//    private fun login(email: String, pass: String) {
//        auth.signInWithEmailAndPassword(email, pass)
//                .addOnSuccessListener {
//                    saveName(it)
//                    Toast.makeText(this, "Login With " + it.user?.displayName, Toast.LENGTH_LONG).show()
//                }.addOnFailureListener {
//                    Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
//                }
//    }
//
//    private fun saveName(it: AuthResult) {
//        //Save Name to Firebase Auth
//        val userProfileChangeRequest = UserProfileChangeRequest.Builder()
//                .setDisplayName(username.text.toString())
//                .build()
//        it.user?.updateProfile(userProfileChangeRequest)
//    }
}


