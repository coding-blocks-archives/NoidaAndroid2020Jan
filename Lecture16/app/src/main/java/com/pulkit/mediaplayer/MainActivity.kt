package com.pulkit.mediaplayer

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*


const val WRITE_PER_CODE = 123

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val perm = ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            )


            if (perm == PackageManager.PERMISSION_GRANTED) {
                saveToFile()
            } else {
                //ask the user for permission
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        WRITE_PER_CODE)
            }
        }
    }

    private fun saveToFile() {
        Toast.makeText(this, "Writing to a file", Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode == WRITE_PER_CODE){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                saveToFile()
            }else{
                Toast.makeText(this, "Cannot write to a file", Toast.LENGTH_SHORT).show()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}
