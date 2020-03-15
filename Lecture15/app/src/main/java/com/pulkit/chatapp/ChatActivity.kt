package com.pulkit.chatapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : AppCompatActivity() {

    val auth by lazy {
        FirebaseAuth.getInstance()
    }

    val db by lazy {
        FirebaseDatabase.getInstance().reference
    }
    val list = arrayListOf<Message>()
    val adapter = MessageAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        messageRv.apply {
            layoutManager = LinearLayoutManager(this@ChatActivity)
            adapter = this@ChatActivity.adapter
        }
        db.child("messages").child("1").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
            }

            override fun onDataChange(data: DataSnapshot) {
                if (data.exists()) {
                    Log.i(
                        "Messages", """
                        ${data.child("msg").value}
                        ${data.child("time").value}
                        ${data.child("name").value}
                    """.trimIndent()
                    )
                }
            }
        })

        db.child("messages").addChildEventListener(object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
            }

            override fun onChildAdded(data: DataSnapshot, p1: String?) {
                val msg = data.getValue(Message::class.java)
                msg?.let { list.add(it) }
                adapter.swapData(list)

            }

            override fun onChildRemoved(p0: DataSnapshot) {
            }

        })
    }

    fun sendMsg(v: View) {
        val msg = Message(auth.currentUser?.displayName.toString(),msgInp.editText?.text.toString(),System.currentTimeMillis())
        db.child("messages").push().setValue(msg)
    }
}

