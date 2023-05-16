package com.nurfadillahdwi.ebookpahlawan.view

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.nurfadillahdwi.ebookpahlawan.databinding.ActivityInstruksiContohBinding
import com.nurfadillahdwi.ebookpahlawan.helper.flagPahlawan
import com.nurfadillahdwi.ebookpahlawan.helper.indexPahlawan

class InstruksiContohActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInstruksiContohBinding
    private var x1: Float = 0.0F
    private var x2: Float = 0.0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInstruksiContohBinding.inflate(layoutInflater)
        setContentView(binding.root)

        indexPahlawan = 0

        if (flagPahlawan) {
            binding.btnSkip.visibility = View.VISIBLE

            val btnSkip =
                ObjectAnimator.ofFloat(binding.btnSkip, "translationX", 500f, binding.btnSkip.x)
                    .setDuration(1000)

            AnimatorSet().apply {
                playTogether(
                    btnSkip
                )
                start()
            }
        }

        binding.btnSkip.setOnClickListener {
            val i = Intent(this, MKCActivity::class.java)
            i.putExtra(EXTRA_FROM, "instruksicontoh")
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
                    val i = Intent(this, PahlawanTemplate1Activity::class.java)
                    startActivity(i)
                    Animatoo.animateSlideLeft(this)
                    finish()
                }
            }
        }
        return false
    }

    private fun backIntent() {
        val i = Intent(this, Materi3Activity::class.java)
        startActivity(i)
        Animatoo.animateSlideRight(this)
        finish()
    }

    companion object {
        const val EXTRA_FROM = "extra_from"
    }
}