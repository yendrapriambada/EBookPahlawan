package com.nurfadillahdwi.ebookpahlawan.view

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.nurfadillahdwi.ebookpahlawan.databinding.ActivityReferenceBinding

class ReferenceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReferenceBinding
    private var x1: Float = 0.0F
    private var x2: Float = 0.0F
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.exit.setOnClickListener {
            this.finishAffinity()
        }
        binding.home.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
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
                    val i = Intent(this, ReflectionActivity::class.java)
                    startActivity(i)
                    Animatoo.animateSlideRight(this)
                }
            }
        }
        return false
    }
}