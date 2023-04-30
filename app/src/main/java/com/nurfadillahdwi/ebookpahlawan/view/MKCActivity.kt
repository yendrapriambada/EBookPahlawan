package com.nurfadillahdwi.ebookpahlawan.view

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.nurfadillahdwi.ebookpahlawan.R
import com.nurfadillahdwi.ebookpahlawan.databinding.ActivityInstruksiContohBinding
import com.nurfadillahdwi.ebookpahlawan.databinding.ActivityMkcactivityBinding
import com.nurfadillahdwi.ebookpahlawan.view.InstruksiContohActivity.Companion.EXTRA_FROM

class MKCActivity : AppCompatActivity() {
    private var from: String? = "default"
    private lateinit var binding: ActivityMkcactivityBinding
    private var x1: Float = 0.0F
    private var x2: Float = 0.0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMkcactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        from = intent.getStringExtra(EXTRA_FROM)

        val btnSkip =
            ObjectAnimator.ofFloat(binding.btnSkip, "translationX", -500f, binding.btnSkip.x).setDuration(1000)

        AnimatorSet().apply {
            playTogether(
                btnSkip
            )
            start()
        }

        binding.btnSkip.setOnClickListener {
            val i = Intent(this, Materi1Activity::class.java)
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
                } else if (x1 > x2) {
                    val i = Intent(this, PracticeActivity::class.java)
                    startActivity(i)
                    Animatoo.animateSlideLeft(this)
                    finish()
                }
            }
        }
        return false
    }

    private fun backIntent() {
        if (from == "instruksicontoh"){
            val i = Intent(this, InstruksiContohActivity::class.java)
            startActivity(i)
            Animatoo.animateSlideRight(this)
            finish()
        }
        else {
            val i = Intent(this, PahlawanTemplate2Activity::class.java)
            startActivity(i)
            Animatoo.animateSlideRight(this)
            finish()
        }
    }
}