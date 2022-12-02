package com.example.memorygame

import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.transition.Transition
import android.transition.TransitionManager
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.memorygame.service.ActivityHandler


class SoundMemoryGame : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_sound_memory_game)

        val greenBtn: Button = findViewById(R.id.btn_green)
        val blueBtn: Button = findViewById(R.id.btn_blue)
        val redBtn: Button = findViewById(R.id.btn_red)
        val yellowBtn: Button = findViewById(R.id.btn_yellow)
        val backBtn: Button = findViewById(R.id.backBtn)

        val loadMainMenu: ActivityHandler = ActivityHandler()

        greenBtn.setOnClickListener {
            greenBtn.setBackgroundColor(resources.getColor(R.color.grass_green))
        }

        blueBtn.setOnClickListener {
            blueBtn.setBackgroundColor(resources.getColor(R.color.teal_200))
        }

        redBtn.setOnClickListener {
            redBtn.setBackgroundColor(resources.getColor(R.color.red))
            val transition: TransitionManager

        }

        yellowBtn.setOnClickListener {
            yellowBtn.setBackgroundColor(resources.getColor(R.color.bright_yellow))

        }

        backBtn.setOnClickListener {
            loadMainMenu.switchActivity(this, this, MainMenu::class.java, 0)
        }
    }


}