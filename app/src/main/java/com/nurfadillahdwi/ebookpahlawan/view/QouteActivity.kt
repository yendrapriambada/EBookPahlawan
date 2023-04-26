package com.nurfadillahdwi.ebookpahlawan.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
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

        when (indexPahlawan?.minus(1)?.div(4)) {
            0 -> setContentView(R.layout.activity_qoute)
            1 -> setContentView(R.layout.activity_quote2)
            2 -> setContentView(R.layout.activity_quote3)
            3 -> setContentView(R.layout.activity_quote4)
            4 -> setContentView(R.layout.activity_quote5)
            5 -> setContentView(R.layout.activity_quote6)
            else -> setContentView(R.layout.activity_qoute)
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
                    if (from == "before") { // jika back lagi ke halaman barusan
                        if (totalPahlawan?.div(2)!! >= indexPahlawan) {
                            val i = Intent(this, PahlawanTemplate1Activity::class.java)
                            startActivity(i)
                            Animatoo.animateSlideRight(this)
                        } else {
                            val i = Intent(this, PahlawanTemplate2Activity::class.java)
                            startActivity(i)
                            Animatoo.animateSlideRight(this)
                        }
                    } else if (from == "after") {
                        indexPahlawan--
                        if (totalPahlawan?.div(2)!! >= indexPahlawan) {
                            val i = Intent(this, PahlawanTemplate1Activity::class.java)
                            startActivity(i)
                            Animatoo.animateSlideRight(this)
                        } else {
                            val i = Intent(this, PahlawanTemplate2Activity::class.java)
                            startActivity(i)
                            Animatoo.animateSlideRight(this)
                        }
                    }
                } else if (x1 > x2) {   //next
                    if (from == "after") { // jika next page ke halaman barusan
                        if (totalPahlawan?.div(2)!! >= indexPahlawan) {
                            val i = Intent(this, PahlawanTemplate1Activity::class.java)
                            startActivity(i)
                            Animatoo.animateSlideLeft(this)
                        } else {
                            val i = Intent(this, PahlawanTemplate2Activity::class.java)
                            startActivity(i)
                            Animatoo.animateSlideLeft(this)
                        }
                    } else if (from == "before") {
                        indexPahlawan++
                        if (totalPahlawan?.div(2)!! >= indexPahlawan) {
                            val i = Intent(this, PahlawanTemplate1Activity::class.java)
                            startActivity(i)
                            Animatoo.animateSlideLeft(this)
                        } else {
                            val i = Intent(this, PahlawanTemplate2Activity::class.java)
                            startActivity(i)
                            Animatoo.animateSlideLeft(this)
                        }
                    }
                }
            }
        }
        return false
    }
}