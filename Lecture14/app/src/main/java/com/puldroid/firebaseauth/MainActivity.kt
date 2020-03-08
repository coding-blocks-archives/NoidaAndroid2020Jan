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
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d("TAG", "onVerificationCompleted:$credential")

                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w("TAG", "onVerificationFailed", e)

                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    // ...
                } else if (e is FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // ...
                }

                // Show a message and update the UI
                // ...
            }

            override fun onCodeSent(
                    verificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
            ) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d("TAG", "onCodeSent:$verificationId")

                // Save verification ID and resending token so we can use them later
                storedVerificationId = verificationId
                var resendToken = token

                // ...
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
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("TAG", "signInWithCredential:success")

                        val user = task.result?.user
                        // ...
                    } else {
                        // Sign in failed, display a message and update the UI
                        Log.w("TAG", "signInWithCredential:failure", task.exception)
                        if (task.exception is FirebaseAuthInvalidCredentialsException) {
                            // The verification code entered was invalid
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


