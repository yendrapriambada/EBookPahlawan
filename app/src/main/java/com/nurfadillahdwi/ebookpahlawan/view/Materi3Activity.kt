package com.nurfadillahdwi.ebookpahlawan.view

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.nurfadillahdwi.ebookpahlawan.databinding.ActivityMateri3Binding


class Materi3Activity : AppCompatActivity() {
    private var x1: Float = 0.0F
    private var x2: Float = 0.0F
    private lateinit var binding: ActivityMateri3Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMateri3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHome.setOnClickListener {
            val i = Intent(this, DaftarIsiActivity::class.java)
            startActivity(i)
            finish()
        }
        onBackPressedDispatcher.addCallback(this) {
            backIntent()
        }
    }

    override fun onTouchEvent(touchEvent: MotionEvent): Boolean {
        when (touchEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                x1 = touchEvent.x
            }
            MotionEvent.ACTION_UP -> {
                x2 = touchEvent.x
                if (x1 < x2) {
                    backIntent()
                }
            }
        }
        return false
    }

    private fun backIntent() {
        val i = Intent(this, Materi2Activity::class.java)
        startActivity(i)
        Animatoo.animateSlideRight(this)
        finish()
    }
}