package com.example.memorygame.anim

import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.Button
import com.example.memorygame.R


class ButtonBlink {
    fun blink(button: Button) {
        val anim: Animation = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 500 //You can manage the blinking time with this parameter

        anim.startOffset = 20
        anim.repeatMode = Animation.REVERSE
        anim.repeatCount = 0
        button.startAnimation(anim)
    }
}