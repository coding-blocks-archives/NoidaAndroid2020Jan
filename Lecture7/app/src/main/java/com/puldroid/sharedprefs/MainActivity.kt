package com.puldroid.sharedprefs

import android.content.Context
import android.content.DialogInterface
import android.icu.text.CaseMap
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        var prefs = getSharedPreferences("com.prefs", Context.MODE_PRIVATE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        count = prefs.getInt("Count", 0)
        edtv.setText(prefs.getString("Name", ""))


        textView.append("$count")

//        val dialog = AlertDialog.Builder(this)
//            .setTitle("Simple Dialog")
//            .setMessage("This is some sample description")
//            .setPositiveButton("Ok") { dialogInterface: DialogInterface, i: Int ->
//                Toast.makeText(this,"Snackbar Action",Toast.LENGTH_SHORT).show()
//            }
//            .setNegativeButton("Cancel") { dialogInterface: DialogInterface, i: Int ->
//
//            }
//            .setCancelable(false)





        updateBtn.setOnClickListener {
            dialog()
//            Snackbar.make(root, "Update Preferences", Snackbar.LENGTH_LONG).show()
            root.snackbar("Update Preferences",Snackbar.LENGTH_LONG,{
                Toast.makeText(this,"Snackbar Action",Toast.LENGTH_SHORT).show()
            })

            prefs.edit().apply {
                putString("Name", edtv.text.toString()).apply()
                putInt("Count", ++count).apply()
            }

            textView.text = "Count ${count}"

        }


    }
}

fun View.snackbar(message: String, duration: Int, function: () -> Unit) {
    Snackbar.make(this, message, duration)
        .setAction("Undo") {
            function()
        }
        .show()
}

fun Context.dialog(message: String = "This is some sample description", title: String = "Simple Dialog",cancelable:Boolean = false,function: (status:Boolean) -> Unit = {}) {
    AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton("Ok") { dialogInterface: DialogInterface, i: Int ->
            function(true)
        }
        .setNegativeButton("Cancel") { dialogInterface: DialogInterface, i: Int ->
            function(false)
        }
        .setCancelable(cancelable)
        .show()
}
