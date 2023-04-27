package com.nurfadillahdwi.ebookpahlawan.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.nurfadillahdwi.ebookpahlawan.R
import com.nurfadillahdwi.ebookpahlawan.helper.indexPahlawan
import com.nurfadillahdwi.ebookpahlawan.helper.totalPahlawan
import com.nurfadillahdwi.ebookpahlawan.view.PahlawanTemplate1Activity.Companion.EXTRA_MESSAGE

class QouteActivity : AppCompatActivity() {
    private var x1: Float = 0.0F
    private var x2: Float = 0.0F
    private var from: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        from = intent.getStringExtra(EXTRA_MESSAGE)

        when (indexPahlawan.minus(1)?.div(4)) {
            0 -> setContentView(R.layout.activity_qoute)
            1 -> setContentView(R.layout.activity_quote2)
            2 -> setContentView(R.layout.activity_quote3)
            3 -> setContentView(R.layout.activity_quote4)
            4 -> setContentView(R.layout.activity_quote5)
            5 -> setContentView(R.layout.activity_quote6)
            else -> setContentView(R.layout.activity_qoute)
        }
        onBackPressedDispatcher.addCallback(this) {
            goBack()
        }
    }

    private fun goBack() {
        if (from == "backward")
            indexPahlawan--
        if (indexPahlawan < totalPahlawan?.div(2)!!) {
            val i = Intent(this, PahlawanTemplate1Activity::class.java)
            startActivity(i)
            Animatoo.animateSlideRight(this)
            finish()
        } else {
            val i = Intent(this, PahlawanTemplate2Activity::class.java)
            startActivity(i)
            Animatoo.animateSlideRight(this)
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
                if (x1 < x2) {  // back
                    goBack()
                } else if (x1 > x2) {   //next
                    if (from == "forward")
                        indexPahlawan++
                    if (indexPahlawan >= (totalPahlawan?.div(2))!!) {
                        val i = Intent(this, PahlawanTemplate2Activity::class.java)
                        startActivity(i)
                        Animatoo.animateSlideLeft(this)
                        finish()
                    } else {
                        val i = Intent(this, PahlawanTemplate1Activity::class.java)
                        startActivity(i)
                        Animatoo.animateSlideLeft(this)
                        finish()
                    }
                }
            }
        }
        return false
    }
}