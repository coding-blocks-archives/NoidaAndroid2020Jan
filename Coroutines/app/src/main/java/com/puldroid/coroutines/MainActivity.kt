package com.puldroid.coroutines

import android.os.Bundle
import android.util.Log
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

        thread(true) {
            Log.i("Threading", "Thread 1")
            printNo("Thread1")
        }

        thread(true) {
            Log.i("Threading", "Thread 2")
            printNo("Thread2")
        }
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
