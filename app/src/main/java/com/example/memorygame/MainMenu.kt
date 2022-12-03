package com.example.memorygame

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.memorygame.model.BoardSize
import com.example.memorygame.service.ActivityHandler

class MainMenu : AppCompatActivity() {

    private val loadMainMenu: ActivityHandler = ActivityHandler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main_menu)

        val mgButton: ImageButton = findViewById(R.id.memory_game)
        val smgButton: ImageButton = findViewById(R.id.sound_memory_game)

        mgButton.setOnClickListener {
            Toast.makeText(this, "Default Memory Game", Toast.LENGTH_SHORT).show()
            showSizeSelection()
        }

        smgButton.setOnClickListener {
            Toast.makeText(this, "Sound Memory Game", Toast.LENGTH_SHORT).show()
            loadMainMenu.switchActivity(this, this, SoundMemoryGame::class.java, 500)
        }
    }

    private fun showSizeSelection() {
        val boardSizeView = LayoutInflater.from(this).inflate(R.layout.dialog_board_size, null)
        val radioGroup = boardSizeView.findViewById<RadioGroup>(R.id.radioGroup)
        showAlert(getString(R.string.message1), boardSizeView, View.OnClickListener {
            val boardSize: BoardSize = when (radioGroup.checkedRadioButtonId) {
                R.id.rbEasy -> BoardSize.EASY
                R.id.rbMedium -> BoardSize.MEDIUM
                R.id.rbHard -> BoardSize.HARD
                else -> BoardSize.EXPERT
            }

            val sendBoardSize = Intent(this, DefaultMemoryGame::class.java)
            sendBoardSize.putExtra("SizeOfBoard", boardSize)
            startActivity(sendBoardSize)
        })
    }

    private fun showAlert(title: String, view: View?, positiveClickListener: View.OnClickListener) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setView(view)
            .setNegativeButton(getString(R.string.cncl), null)
            .setPositiveButton(getString(R.string.okay)) { _, _ ->
                positiveClickListener.onClick(null)
            }.show()
    }
}