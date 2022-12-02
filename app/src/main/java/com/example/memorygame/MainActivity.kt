package com.example.memorygame

import android.os.Bundle
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.memorygame.service.ActivityHandler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)

        val logoAnim: Animation = AnimationUtils.loadAnimation(this, R.anim.logo_anim)
        val logoImg: ImageView = findViewById(R.id.logo)

        logoImg.animation = logoAnim

        val loadMainMenu: ActivityHandler = ActivityHandler()
        loadMainMenu.switchActivity(this, this, MainMenu::class.java, 5000)

    }
}