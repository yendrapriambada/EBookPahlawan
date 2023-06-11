package com.nurfadillahdwi.ebookpahlawan.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.nurfadillahdwi.ebookpahlawan.R
import com.nurfadillahdwi.ebookpahlawan.helper.indexPahlawan

class MKCActivity : AppCompatActivity() {
    private var x1: Float = 0.0F
    private var x2: Float = 0.0F
    private lateinit var viewModel: MKCViewModel
    private val token: String =
        "0110ad0929162b63b7fec2e9b08aca1d0003e99f2db7e4ec8880ea6f9c5a4d6882a7f78f3fc417725ed158eb95929b7c39ec8b5b5a68b0cc38468979f1b5ef2c6aae8e7c78ca53862b9a415a018e5a16646c9285500137649c9a9ce2b11629a92bf205fa6590f0186756c061e08775a26892cda1242a7e9351e0e3594d4b5a2b"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MKCViewModel::class.java]
        setContentView(R.layout.activity_mkcactivity)

        val sharedPref = applicationContext.getSharedPreferences("MyPref", MODE_PRIVATE)
        val namaSiswa = sharedPref.getString("nama", "default").toString()

        viewModel.getPahlawans(token, namaSiswa)
        viewModel.responsePahlawan.observe(this) {
            indexPahlawan = it.data?.size!! - 1
            Log.d("index saat ini", indexPahlawan.toString())
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
                    val i = Intent(this, GuideActivity::class.java)
                    startActivity(i)
                    Animatoo.animateSlideLeft(this)
                    finish()
                }
            }
        }
        return false
    }

    private fun backIntent() {
        val i = Intent(this, DaftarIsiActivity::class.java)
        startActivity(i)
        Animatoo.animateSlideRight(this)
        finish()
    }
}