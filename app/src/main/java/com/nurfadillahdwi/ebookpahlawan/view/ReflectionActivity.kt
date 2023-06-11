package com.nurfadillahdwi.ebookpahlawan.view

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.nurfadillahdwi.ebookpahlawan.databinding.ActivityReflectionBinding

class ReflectionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReflectionBinding
    private var x1: Float = 0.0F
    private var x2: Float = 0.0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReflectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onBackPressedDispatcher.addCallback(this) {
            goBack()
        }
        binding.btnHome.setOnClickListener {
            val i = Intent(this, DaftarIsiActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    private fun goBack() {
        val i = Intent(this, DaftarIsiActivity::class.java)
        startActivity(i)
        Animatoo.animateSlideRight(this)
        finish()
    }

    override fun onTouchEvent(touchEvent: MotionEvent): Boolean {
        when (touchEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                x1 = touchEvent.x
            }
            MotionEvent.ACTION_UP -> {
                x2 = touchEvent.x
                if (x1 < x2) {
                    goBack()
                }
//                else if (x1 > x2) {
//                    val i = Intent(this, ReferenceActivity::class.java)
//                    startActivity(i)
//                    Animatoo.animateSlideLeft( this)
//                    finish()
//                }
            }
        }
        return false
    }
}