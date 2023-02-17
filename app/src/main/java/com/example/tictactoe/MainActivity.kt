package com.example.tictactoe

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    // Game square vars
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

    // Game status vars
    var currPlayer = "X"
    var winner = ""

    private var textPlayer: TextView? = null

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

        textPlayer = findViewById(R.id.textPlayer)
        displayCurrPlayer()
    }

    private fun isVictory(squareA: TextView?, squareB: TextView?, squareC: TextView?): Boolean {
        if (squareA?.text?.isEmpty()!!) {
            return false
        }

        if (squareA?.text == squareB?.text && squareB?.text == squareC?.text) {
            return true
        }
        return false
    }

    private fun resetColour(square: TextView?) {
        if ((square?.background as ColorDrawable).color != teal200) {
            square.setBackgroundColor(teal200!!)
        }
    }

    private fun resetSquaresColour() {
        resetColour(square1)
        resetColour(square2)
        resetColour(square3)
        resetColour(square4)
        resetColour(square5)
        resetColour(square6)
        resetColour(square7)
        resetColour(square8)
        resetColour(square9)
    }

    private fun resetSquaresSelection() {
        square1?.text = ""
        square2?.text = ""
        square3?.text = ""
        square4?.text = ""
        square5?.text = ""
        square6?.text = ""
        square7?.text = ""
        square8?.text = ""
        square9?.text = ""
    }

    private fun highlightAnyVictory(): String {
        resetSquaresColour()

        // Check each possible  line for victory
        if (isVictory(square1, square2, square3)) {
            square1?.setBackgroundColor(purple200!!)
            square2?.setBackgroundColor(purple200!!)
            square3?.setBackgroundColor(purple200!!)
            return square1?.text.toString()
        }
        if (isVictory(square4, square5, square6)) {
            square4?.setBackgroundColor(purple200!!)
            square5?.setBackgroundColor(purple200!!)
            square6?.setBackgroundColor(purple200!!)
            return square4?.text.toString()
        }
        if (isVictory(square7, square8, square9)) {
            square7?.setBackgroundColor(purple200!!)
            square8?.setBackgroundColor(purple200!!)
            square9?.setBackgroundColor(purple200!!)
            return square7?.text.toString()
        }
        if (isVictory(square1, square4, square7)) {
            square1?.setBackgroundColor(purple200!!)
            square4?.setBackgroundColor(purple200!!)
            square7?.setBackgroundColor(purple200!!)
            return square1?.text.toString()
        }
        if (isVictory(square2, square5, square8)) {
            square2?.setBackgroundColor(purple200!!)
            square5?.setBackgroundColor(purple200!!)
            square8?.setBackgroundColor(purple200!!)
            return square2?.text.toString()
        }
        if (isVictory(square3, square6, square9)) {
            square3?.setBackgroundColor(purple200!!)
            square6?.setBackgroundColor(purple200!!)
            square9?.setBackgroundColor(purple200!!)
            return square3?.text.toString()
        }
        if (isVictory(square1, square5, square9)) {
            square1?.setBackgroundColor(purple200!!)
            square5?.setBackgroundColor(purple200!!)
            square9?.setBackgroundColor(purple200!!)
            return square1?.text.toString()
        }
        if (isVictory(square3, square5, square7)) {
            square3?.setBackgroundColor(purple200!!)
            square5?.setBackgroundColor(purple200!!)
            square7?.setBackgroundColor(purple200!!)
            return square3?.text.toString()
        }

        return ""
    }

    private fun reset() {
        resetSquaresSelection()
        resetSquaresColour()
        currPlayer = "X"
        winner = ""
        displayCurrPlayer()
    }

    fun onClickPlaySquare(view: View) {
        if (winner != "") { // No more moves after a win
            return
        }

        val square = (view as TextView)
        if (square.text != "") { // Can't change a played square
            return
        }

        square.text = currPlayer

        when (currPlayer) {
            "O" -> {
                currPlayer = "X"
            }
            "X" -> {
                currPlayer = "O"
            }
        }

        winner = highlightAnyVictory()
        if (winner == "") {
            displayCurrPlayer()
        } else {
            Toast.makeText(this, "WINNER!", Toast.LENGTH_SHORT).show()
            displayWinner(winner)
        }
    }

    private fun displayCurrPlayer() {
        (textPlayer as TextView).text = String.format(getString(R.string.curr_player_message), currPlayer)
    }

    private fun displayWinner(winner: String) {
        (textPlayer as TextView).text = String.format(getString(R.string.winner_message), winner)
    }

    fun onClickNewGame(view: View) {
        // Ask user to confirm
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.new_game_title_message))
        builder.setMessage(getString(R.string.new_game_confirm_message))
        builder.setPositiveButton(getString(R.string.new_button_message)) { _, _ ->
            reset()
        }
        builder.setNegativeButton(getString(R.string.go_back_message)) { _, _ -> }
        builder.show()
    }

    fun onClickExitApp(view: View) {
        // Ask user to confirm
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.exit_title_message))
        builder.setMessage(getString(R.string.exit_confirm_message))
        builder.setPositiveButton(getString(R.string.exit_message)) { _, _ ->
            finishAndRemoveTask()
        }
        builder.setNegativeButton(getString(R.string.go_back_message)) { _, _ -> }
        builder.show()
    }
}