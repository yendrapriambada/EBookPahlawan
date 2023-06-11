package com.nurfadillahdwi.ebookpahlawan.view

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.nurfadillahdwi.ebookpahlawan.databinding.ActivityDaftarIsiBinding
import com.nurfadillahdwi.ebookpahlawan.helper.indexPahlawan

class DaftarIsiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDaftarIsiBinding
    private var x1: Float = 0.0F
    private var x2: Float = 0.0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaftarIsiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        indexPahlawan = 0

        binding.apply {
            btnCover.setOnClickListener {
                Intent(this@DaftarIsiActivity, MainActivity::class.java).also {
                    startActivity(it)
                }
                finish()
            }

            btnInformasi.setOnClickListener {
                Intent(this@DaftarIsiActivity, KeteranganActivity::class.java).also {
                    startActivity(it)
                }
                finish()
            }

            btnTentang.setOnClickListener {
                Intent(this@DaftarIsiActivity, TentangActivity::class.java).also {
                    startActivity(it)
                }
                finish()
            }

            btnPengantar.setOnClickListener {
                Intent(this@DaftarIsiActivity, KataPengantarActivity::class.java).also {
                    startActivity(it)
                }
                finish()
            }

            btnMateri.setOnClickListener {
                Intent(this@DaftarIsiActivity, Materi1Activity::class.java).also {
                    startActivity(it)
                }
                finish()
            }

            btnContoh.setOnClickListener {
                Intent(this@DaftarIsiActivity, InstruksiContohActivity::class.java).also {
                    startActivity(it)
                }
                finish()
            }

            btnLatihan.setOnClickListener {
                Intent(this@DaftarIsiActivity, MKCActivity::class.java).also {
                    startActivity(it)
                }
                finish()
            }

            btnRefleksi.setOnClickListener {
                Intent(this@DaftarIsiActivity, ReflectionActivity::class.java).also {
                    startActivity(it)
                }
                finish()
            }

            btnReferensi.setOnClickListener {
                Intent(this@DaftarIsiActivity, ReferenceActivity::class.java).also {
                    startActivity(it)
                }
                finish()
            }
        }
        onBackPressedDispatcher.addCallback(this) {
            goBack()
        }
    }

    private fun goBack() {
        val i = Intent(this, KataPengantarActivity::class.java)
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
            }
        }
        return false
    }
}