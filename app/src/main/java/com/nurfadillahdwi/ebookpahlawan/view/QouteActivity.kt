package com.nurfadillahdwi.ebookpahlawan.view

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.nurfadillahdwi.ebookpahlawan.R
import com.nurfadillahdwi.ebookpahlawan.helper.indexPahlawan
import com.nurfadillahdwi.ebookpahlawan.view.PahlawanTemplate1Activity.Companion.EXTRA_MESSAGE

class QouteActivity : AppCompatActivity() {
    private var x1: Float = 0.0F
    private var x2: Float = 0.0F
    private var from: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        from = intent.getStringExtra(EXTRA_MESSAGE)

        when (indexPahlawan) {
            3 -> setContentView(R.layout.activity_qoute)
            7 -> setContentView(R.layout.activity_quote2)
            11 -> setContentView(R.layout.activity_quote3)
            15 -> setContentView(R.layout.activity_quote4)
            19 -> setContentView(R.layout.activity_quote5)
            23 -> setContentView(R.layout.activity_quote6)
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
                        if (indexPahlawan == 3 || indexPahlawan == 7 || indexPahlawan == 11) {
                            val i = Intent(this, PahlawanTemplate1Activity::class.java)
                            startActivity(i)
                            Animatoo.animateSlideRight(this)
                        } else if (indexPahlawan == 15 || indexPahlawan == 19 || indexPahlawan == 23) {
                            val i = Intent(this, PahlawanTemplate2Activity::class.java)
                            startActivity(i)
                            Animatoo.animateSlideRight(this)
                        }
                    } else if (from == "after") {
                        if (indexPahlawan == 3 || indexPahlawan == 7 || indexPahlawan == 11) {
                            val i = Intent(this, PahlawanTemplate1Activity::class.java)
                            startActivity(i)
                            Animatoo.animateSlideRight(this)
                        } else if (indexPahlawan == 15 || indexPahlawan == 19 || indexPahlawan == 23) {
                            val i = Intent(this, PahlawanTemplate2Activity::class.java)
                            startActivity(i)
                            Animatoo.animateSlideRight(this)
                        }
                    }

                } else if (x1 > x2) {   //next
                    if (from == "after") { // jika next page ke halaman barusan
                        if (indexPahlawan == 3 || indexPahlawan == 7 || indexPahlawan == 11) {
                            val i = Intent(this, PahlawanTemplate1Activity::class.java)
                            startActivity(i)
                            Animatoo.animateSlideLeft(this)
                            indexPahlawan++
                        } else if (indexPahlawan == 15 || indexPahlawan == 19 || indexPahlawan == 23) {
                            val i = Intent(this, PahlawanTemplate2Activity::class.java)
                            startActivity(i)
                            Animatoo.animateSlideLeft(this)
                            indexPahlawan++
                        }
                    } else if (from == "before") {
                        if (indexPahlawan == 3 || indexPahlawan == 7 || indexPahlawan == 11) {
                            val i = Intent(this, PahlawanTemplate1Activity::class.java)
                            startActivity(i)
                            Animatoo.animateSlideLeft(this)
                            indexPahlawan++
                        } else if (indexPahlawan == 15 || indexPahlawan == 19 || indexPahlawan == 23) {
                            val i = Intent(this, PahlawanTemplate2Activity::class.java)
                            startActivity(i)
                            Animatoo.animateSlideLeft(this)
                            indexPahlawan++
                        }
                    }
                }
            }
        }
        return false
    }
}