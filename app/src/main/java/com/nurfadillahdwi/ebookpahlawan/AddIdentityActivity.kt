package com.nurfadillahdwi.ebookpahlawan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import com.blogspot.atifsoftwares.animatoolib.Animatoo

class AddIdentityActivity : AppCompatActivity() {
    private var x1: Float = 0.0F
    private var x2: Float = 0.0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_identity)
        supportActionBar?.hide()
    }

    override fun onTouchEvent(touchEvent: MotionEvent): Boolean {
        when (touchEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                x1 = touchEvent.x
            }
            MotionEvent.ACTION_UP -> {
                x2 = touchEvent.x
                if (x1 < x2) {
                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                    Animatoo.animateSlideRight( this)
                } else if (x1 > x2) {
                    val i = Intent(this, KeteranganActivity::class.java)
                    startActivity(i)
                    Animatoo.animateSlideLeft( this)

                }
            }
        }
        return false
    }
}