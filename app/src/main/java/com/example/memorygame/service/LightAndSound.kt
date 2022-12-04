package com.example.memorygame.service

import android.widget.Button
import android.widget.TextView
import com.example.memorygame.SoundMemoryGame
import com.example.memorygame.anim.ButtonBlink
import kotlinx.android.synthetic.main.activity_sound_memory_game.*
import kotlinx.coroutines.delay
import kotlin.random.Random


class LightAndSound {
    fun startGame(view: SoundMemoryGame) {
        val blink = ButtonBlink()

        val scoreText: TextView = view.scoreTxt
        val greenBtn: Button = view.btn_green
        val blueBtn: Button = view.btn_blue
        val redBtn: Button = view.btn_red
        val yellowBtn: Button = view.btn_yellow

        val memoryList = List(10) { Random.nextInt(1, 5) }



    }
}