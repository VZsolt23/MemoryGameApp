package com.example.memorygame.service

import android.os.CountDownTimer
import android.widget.ImageView

class Timer {
    fun timerMethod(btn: ImageView) {
        btn.isEnabled = false
        object : CountDownTimer(1000, 50) {
            override fun onTick(arg0: Long) {

            }

            override fun onFinish() {
                btn.isEnabled = true
            }
        }.start()
    }
}