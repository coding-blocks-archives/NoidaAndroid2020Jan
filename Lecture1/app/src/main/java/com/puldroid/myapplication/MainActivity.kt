package com.puldroid.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val name: String = "Pulkit" //final always need to set the value
    val name2 = 5
    lateinit var name3: String
    var id: Int? = 100
    val arr: Array<String> = Array(100) {
        "1"
    }
    val arr2 = arrayOf("2", "3", "3", "4", "5")
    val arrlist = arrayListOf<String>("2", "3", "3", "4", "5")

    /*
    String name = null;
    if(name != null){

        String getName() {
        return name;
        int[] a = new int[2]
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //A a = new A()
        val a = A("Pulkit", 5)
        Log.i("Kotlin", a.toString())
        name3 = "Pulkt"
        if (id!! > 5) {
            getUser(isActive = true)
            getUser(isActive = false, id = 2, name = "Pulkit")
            getUser(2, isActive = false, name = "Pulkit")
//            getUser(name = "Pulkit",id= 3,false)
            arr2.forEach {
                Log.i("Kotlin", it.toString())
            }

        }

        val newName  = when (name) {
            "Pulkit" -> "Aggarwal"
            "Aggaral" -> "Pulkit"
            else -> "Pulkit Aggarwal"

        }
        btn.setOnClickListener {
            Toast.makeText(this, "Pulkit Aggaral ${btn.text}", Toast.LENGTH_SHORT).show()
        }
    }

    fun getName6(): String = "Pulkit"
    fun getName7() = "Pulkit"
    fun getName8(): String {
        return "Pulkit"
    }

    fun getUser(id: Int = 1, name: String = "", isActive: Boolean = false): String {
        return "(id = $id,name = $name,isActive = $isActive)"
    }


}
