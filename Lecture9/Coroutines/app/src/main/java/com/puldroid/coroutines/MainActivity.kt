package com.puldroid.coroutines

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    var counter = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var result: Job? = null
        button.setOnClickListener {
            //            Thread.sleep(1000)
            result?.let {
                if (it.isActive) {
                    it.cancel()
                }else {
                    result = GlobalScope.launch(Dispatchers.Main) {
                        for (i in 0..1000) {
                            delay(1000)
                            text.text = "${counter++}"
                        }
                    }
                }
            } ?: run {
                result = GlobalScope.launch(Dispatchers.Main) {
                    for (i in 0..1000) {
                        delay(1000)
                        text.text = "${counter++}"
                    }

                }
            }

        }
        button2.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
//                val a = GlobalScope.async {  wait()}.await()
                val dialog = AlertDialog.Builder(this@MainActivity)
                        .setTitle("Wait")
                        .setMessage("Wait for operation to complete")
                        .setCancelable(false)
                        .show()
                val a = GlobalScope.async(Dispatchers.Default) { wait(3) }.await()

                val b = withContext(Dispatchers.Default) { wait(4) }

                if(a && b){
                    dialog.dismiss()
                    Toast.makeText(this@MainActivity,"Coroutine Completed",Toast.LENGTH_SHORT).show()
                }
            }
        }

        thread(true) {
            Log.i("Threading", "Thread 1")
            printNo("Thread1")
        }

        thread(true) {
            Log.i("Threading", "Thread 2")
            printNo("Thread2")
        }
    }
    suspend fun wait(i: Int):Boolean{
        for(i in 0..i){
            delay(1000)
        }
        return true
    }

    private fun printNo(s: String) {
        for (i in 0..10000) {
            if (i == 5000) {
                text.text = "5000"
            }
            Log.i("Threading $s", "$i")
        }

    }
}
