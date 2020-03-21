package com.pulkit.mediaplayer

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.os.StrictMode
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File


const val WRITE_PER_CODE = 123
const val READ_PER_CODE = 456

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        StrictMode.setThreadPolicy(
//                StrictMode.ThreadPolicy.Builder()
//                        .detectAll()
//                        .penaltyDeath()
//                        .build()
//        )

        mediaBtn.setOnClickListener {
            startActivity(Intent(this,MediaPlayerActivity::class.java))
        }

        button.setOnClickListener {
            val perm = ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            if (perm == PackageManager.PERMISSION_GRANTED) {
                saveToFile()
            } else {
                //ask the user for permission
                ActivityCompat.requestPermissions(this,
                        arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        WRITE_PER_CODE)
            }
        }

        restBtn.setOnClickListener {
            val perm = ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
            if (perm == PackageManager.PERMISSION_GRANTED) {
                restoreFile()
            } else {
                //ask the user for permission
                ActivityCompat.requestPermissions(this,
                        arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                        READ_PER_CODE)
            }
        }
    }

    private fun restoreFile() {

        Toast.makeText(this, "Reading form a file", Toast.LENGTH_SHORT).show()
        GlobalScope.launch {
            val directory = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)?.absolutePath
            val file = File(directory, "data.txt")
            if (file.exists()) {
                val savedData = withContext(Dispatchers.IO) { file.readText() }
                GlobalScope.launch(Dispatchers.Main) {
                    editText.setText(savedData)
                }
            } else {
                GlobalScope.launch(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Nothing to restore", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun saveToFile() {
        Toast.makeText(this, "Writing to a file", Toast.LENGTH_SHORT).show()
        val data = editText.text.toString()
        GlobalScope.launch {

            val directory = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)?.absolutePath
            Log.i("Download file Path", directory)
            val file = File(directory, "data.txt")
            val fileWritten = withContext(Dispatchers.IO) {
                file.writeBytes(data.toByteArray())
                true
            }
            Log.i("Download file Path", fileWritten.toString())
        }


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == WRITE_PER_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                saveToFile()
            } else {
                Toast.makeText(this, "Cannot write to a file", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == READ_PER_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                restoreFile()
            } else {
                Toast.makeText(this, "Cannot read a file", Toast.LENGTH_SHORT).show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}
