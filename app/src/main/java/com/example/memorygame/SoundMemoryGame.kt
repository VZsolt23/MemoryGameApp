package com.example.memorygame

import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.memorygame.service.ActivityHandler
import com.example.memorygame.service.LightAndSound
import com.google.android.material.snackbar.Snackbar


class SoundMemoryGame : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_sound_memory_game)

        val clRoot: ConstraintLayout = findViewById(R.id.constLay)
        val startBtn: Button = findViewById(R.id.startButton)
        val backBtn: Button = findViewById(R.id.backBtn)

        val loadMainMenu = ActivityHandler()
        val lightAndSound = LightAndSound()

        var isRunning = false

        startBtn.setOnClickListener {
            if (!isRunning) {
                lightAndSound.startGame(this)
                isRunning = true
                Snackbar.make(clRoot, getString(R.string.game_started), Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                Snackbar.make(clRoot, getString(R.string.already_running), Snackbar.LENGTH_SHORT)
                    .show()
            }
        }

        backBtn.setOnClickListener {
            loadMainMenu.switchActivity(this, this, MainMenu::class.java, 0)
        }
    }


}