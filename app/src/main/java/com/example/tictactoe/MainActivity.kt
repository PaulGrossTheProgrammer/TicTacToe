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

    private var square1: TextView? = null

    private fun highlightAnyVictory() {
        // FIXME: Maybe cast square1?.background to Color and get integer value???
        Log.d("DEBUG", "Square1 background ${(square1?.background as ColorDrawable).color}")
        Log.d("DEBUG", "Purple             ${getColor(R.color.purple_200)}")
        //Toast.makeText(this, "Square1 background ${square1?.background}", Toast.LENGTH_SHORT).show()
        //Toast.makeText(this, "Purple: ${getColor(R.color.purple_200)}", Toast.LENGTH_SHORT).show()

        // Reset all highlights
        if ((square1?.background as ColorDrawable).color != getColor(R.color.teal_200)) {
            square1?.setBackgroundColor(getColor(R.color.teal_200))
        }

        if (square1?.text == "X") {
            square1?.setBackgroundColor(getColor(R.color.purple_200))
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        square1 = findViewById(R.id.textSquare1)
    }
}