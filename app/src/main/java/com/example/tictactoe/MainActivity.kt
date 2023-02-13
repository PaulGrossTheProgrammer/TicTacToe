package com.example.tictactoe

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    // Game squares
    private var square1: TextView? = null
    private var square2: TextView? = null
    private var square3: TextView? = null
    private var square4: TextView? = null
    private var square5: TextView? = null
    private var square6: TextView? = null
    private var square7: TextView? = null
    private var square8: TextView? = null
    private var square9: TextView? = null

    // Colours
    private var teal200: Int? = null
    private var purple200: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("DEBUG", "Layout created")

        square1 = findViewById(R.id.textSquare1)
        square2 = findViewById(R.id.textSquare2)
        square3 = findViewById(R.id.textSquare3)
        square4 = findViewById(R.id.textSquare4)
        square5 = findViewById(R.id.textSquare5)
        square6 = findViewById(R.id.textSquare6)
        square7 = findViewById(R.id.textSquare7)
        square8 = findViewById(R.id.textSquare8)
        square9 = findViewById(R.id.textSquare9)

        teal200 = getColor(R.color.teal_200)
        purple200 = getColor(R.color.purple_200)
    }

    private fun isVictory(squareA: TextView?, squareB: TextView?, squareC: TextView?): Boolean {
        if (squareA?.text?.isEmpty()!!) { return false }

        if (squareA?.text == squareB?.text && squareB?.text == squareC?.text) {
            return true
        }
        return false
    }

    private fun resetColour(square: TextView?) {
        if ((square?.background as ColorDrawable).color != teal200) {
            square?.setBackgroundColor(teal200!!)
        }
    }

    private fun highlightAnyVictory() {
        // Reset all highlights
        resetColour(square1)
        resetColour(square2)
        resetColour(square3)
        resetColour(square4)
        resetColour(square5)
        resetColour(square6)
        resetColour(square7)
        resetColour(square8)
        resetColour(square9)

        // Check each possible  line for victory
        if (isVictory(square1, square2, square3)) {
            square1?.setBackgroundColor(purple200!!)
            square2?.setBackgroundColor(purple200!!)
            square3?.setBackgroundColor(purple200!!)
        }
        if (isVictory(square4, square5, square6)) {
            square4?.setBackgroundColor(purple200!!)
            square5?.setBackgroundColor(purple200!!)
            square6?.setBackgroundColor(purple200!!)
        }
        if (isVictory(square7, square8, square9)) {
            square7?.setBackgroundColor(purple200!!)
            square8?.setBackgroundColor(purple200!!)
            square9?.setBackgroundColor(purple200!!)
        }
        if (isVictory(square1, square4, square7)) {
            square1?.setBackgroundColor(purple200!!)
            square4?.setBackgroundColor(purple200!!)
            square7?.setBackgroundColor(purple200!!)
        }
        if (isVictory(square2, square5, square8)) {
            square2?.setBackgroundColor(purple200!!)
            square5?.setBackgroundColor(purple200!!)
            square8?.setBackgroundColor(purple200!!)
        }
        if (isVictory(square3, square6, square9)) {
            square3?.setBackgroundColor(purple200!!)
            square6?.setBackgroundColor(purple200!!)
            square9?.setBackgroundColor(purple200!!)
        }
        if (isVictory(square1, square5, square9)) {
            square1?.setBackgroundColor(purple200!!)
            square5?.setBackgroundColor(purple200!!)
            square9?.setBackgroundColor(purple200!!)
        }
        if (isVictory(square3, square5, square7)) {
            square3?.setBackgroundColor(purple200!!)
            square5?.setBackgroundColor(purple200!!)
            square7?.setBackgroundColor(purple200!!)
        }
    }

    fun onClickSelectSquare(view: View) {
        val square = (view as TextView)

        if (square.text == "") {
            square.text = "O"
        } else if (square.text == "O") {
            square.text = "X"
        }  else if (square.text == "X") {
            square.text = ""
        }

        highlightAnyVictory()
    }
    fun onClickSelectSquare2(view: View) {
        val button = (view as Button)

        if (button.text == "") {
            button.text = "O"
        } else if (button.text == "O") {
            button.text = "X"
        }  else if (button.text == "X") {
            button.text = ""
        }

        highlightAnyVictory()
    }

    fun onClickDoSomething(view: View) {
        Toast.makeText(this, "Do something...", Toast.LENGTH_SHORT).show()
    }

    fun onClickExitApp(view: View) {
        // Ask user to confirm before exiting
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Exit Application")
        builder.setMessage("Are you sure you want to to exit?")
        builder.setPositiveButton("Yes") { dialog, which ->
            finishAndRemoveTask()
        }
        builder.setNegativeButton("No") { dialog, which ->}
        builder.show()
    }
}