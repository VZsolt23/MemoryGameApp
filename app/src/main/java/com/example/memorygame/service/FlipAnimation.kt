package com.example.memorygame.service

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.ImageView
import com.example.memorygame.R

class FlipAnimation {
    fun Flip(btn: ImageView, id: Int, isTurned: Boolean) {
        val anime1 = ObjectAnimator.ofFloat(btn, "scaleX", 1f, 0f)
        val anime2 = ObjectAnimator.ofFloat(btn, "scaleX", 0f, 1f)

        anime1.interpolator = DecelerateInterpolator()
        anime2.interpolator = AccelerateInterpolator()

        anime1.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                if (isTurned) {
                    btn.setImageResource(id)
                }
                else {
                    btn.setImageResource(R.drawable.zsut)
                }
                anime2.start()
            }
        })
        anime1.start()
    }
}