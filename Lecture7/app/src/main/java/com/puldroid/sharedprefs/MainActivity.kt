package com.puldroid.sharedprefs

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
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


        updateBtn.setOnClickListener {

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
