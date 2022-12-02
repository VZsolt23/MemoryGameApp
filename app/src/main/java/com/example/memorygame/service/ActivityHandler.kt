package com.example.memorygame.service

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper

class ActivityHandler {
    fun switchActivity(toCls: Context, act: Activity, toPn: Class<*>, duration: Long) {
        Handler(Looper.getMainLooper()).postDelayed({
            val mainIntent = Intent(toCls, toPn)
            toCls.startActivity(mainIntent)
            act.finish()
        }, duration)
    }
}