package com.nurfadillahdwi.ebookpahlawan

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.nurfadillahdwi.ebookpahlawan.databinding.ActivityMainBinding
import com.nurfadillahdwi.ebookpahlawan.databinding.ActivityPahlawanTemplate2Binding


class PahlawanTemplate2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityPahlawanTemplate2Binding
    private var x1: Float = 0.0F
    private var x2: Float = 0.0F
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPahlawanTemplate2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val constraintLayout = ConstraintLayout(this)

        binding.imageView3.setOnClickListener {
            constraintLayout.scrollTo(0, 0)
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
                    val i = Intent(this, PahlawanTemplate1Activity::class.java)
                    startActivity(i)
                    Animatoo.animateSlideRight( this)
                } else if (x1 > x2) {
//                    val i = Intent(this, KeteranganActivity::class.java)
//                    startActivity(i)
//                    Animatoo.animateSlideLeft( this)

                }
            }
        }
        return false
    }
}