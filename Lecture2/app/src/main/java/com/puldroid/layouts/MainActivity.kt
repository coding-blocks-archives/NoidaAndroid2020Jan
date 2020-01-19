package com.puldroid.layouts

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    var player = false
    var turnCount = 0
    var boardStatus = Array(3) { IntArray(3) }
    var buttonArray = arrayOf<Array<Button>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonArray = arrayOf(
            arrayOf(btn1, btn2, btn3),
            arrayOf(btn4, btn5, btn6),
            arrayOf(btn7, btn8, btn9)
        )
        for (i in buttonArray) {
            for (j in i) {
                j.setOnClickListener(this)
            }
        }
        //Java
//        val button1  = findViewById<Button>(R.id.btn1)
        btn1.text = "Pulkit"
//        btn1.setOnClickListener {
//        }
//        btn1.setOnClickListener(this)
//        btn2.setOnClickListener(this)
        intialize()
        resetBtn.setOnClickListener {
            intialize()
        }
        Log.i("Lifecycle", "On Create Called")
    }

    private fun intialize() {
        for (i in buttonArray) {
            for (j in i) {
                j.isEnabled = true
                j.text = ""
            }
        }
        for (i in 0..2) {
            for (j in 0..2) {
                boardStatus[i][j] = -1
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("Lifecycle", "On Start Called")

    }

    override fun onResume() {
        super.onResume()
        Log.i("Lifecycle", "On Resume Called")

    }

    override fun onPause() {
        super.onPause()
        Log.i("Lifecycle", "On Pause Called")

    }

    override fun onStop() {
        super.onStop()
        Log.i("Lifecycle", "On Stop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Lifecycle", "On Destroy Called")

    }
    override fun onClick(v: View) {
        player = !player
        when (v.id) {
            R.id.btn1 -> {
                updateValue(row = 0, col = 0)
            }
            R.id.btn2 -> {
                updateValue(row = 0, col = 1)
            }
            R.id.btn3 -> {
                updateValue(row = 0, col = 2)
            }
            R.id.btn4 -> {
                updateValue(row = 1, col = 0)
            }
            R.id.btn5 -> {
                updateValue(row = 1, col = 1)
            }
            R.id.btn6 -> {
                updateValue(row = 1, col = 2)
            }
            R.id.btn7 -> {
                updateValue(row = 2, col = 0)
            }
            R.id.btn8 -> {
                updateValue(row = 2, col = 1)
            }
            R.id.btn9 -> {
                updateValue(row = 2, col = 2)
            }


        }
        turnCount++

        if (turnCount > 5)
            checkWinner()

    }

    private fun checkWinner() {

        //First Diagonal
        if (boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]) {
            if (boardStatus[0][0] == 1) {
                updateText("1")
            } else if (boardStatus[0][0] == 0) {
                updateText("0")
            }
        }
        //Second Diagonal
        if (boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0]) {
            if (boardStatus[0][2] == 1) {
                updateText("1")
            } else if (boardStatus[0][2] == 0) {
                updateText("0")
            }
        }

        //Vertical Row
        for (i in 0..2) {
            if (boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]) {
                if (boardStatus[0][i] == 1) {
                    updateText("1")
                } else if (boardStatus[0][i] == 0) {
                    updateText("0")
                }
            }
        }

        for (i in 0..2) {
            if (boardStatus[i][2] == boardStatus[i][1] && boardStatus[i][2] == boardStatus[i][0]) {
                if (boardStatus[i][2] == 1) {
                    updateText("1")
                } else if (boardStatus[i][2] == 0) {
                    updateText("0")
                }
            }
        }

    }

    private fun updateText(text: String) {
        displayText.text = "$text is Winner"
        disableButton()
    }

    private fun disableButton() {
        for (i in buttonArray) {
            for (j in i) {
                j.isEnabled = false
            }
        }
    }

    private fun updateValue(row: Int, col: Int) {
        val text = if (player) "X" else "0"
        val value = if (player) 1 else 0


        buttonArray[row][col].text = text
        buttonArray[row][col].isEnabled = false

        boardStatus[row][col] = value

    }



}
