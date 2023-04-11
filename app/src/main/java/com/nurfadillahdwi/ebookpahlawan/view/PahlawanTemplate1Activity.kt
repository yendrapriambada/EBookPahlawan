package com.nurfadillahdwi.ebookpahlawan.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.nurfadillahdwi.ebookpahlawan.R
import com.nurfadillahdwi.ebookpahlawan.databinding.ActivityPahlawanTemplate1Binding

class PahlawanTemplate1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityPahlawanTemplate1Binding
    private var x1: Float = 0.0F
    private var x2: Float = 0.0F
    private lateinit var viewModel: Pahlawan1TemplateViewModel
    private val token: String =
        "0110ad0929162b63b7fec2e9b08aca1d0003e99f2db7e4ec8880ea6f9c5a4d6882a7f78f3fc417725ed158eb95929b7c39ec8b5b5a68b0cc38468979f1b5ef2c6aae8e7c78ca53862b9a415a018e5a16646c9285500137649c9a9ce2b11629a92bf205fa6590f0186756c061e08775a26892cda1242a7e9351e0e3594d4b5a2b"

    private val nama: MutableList<String> = ArrayList()
    private var lahir: MutableList<String>? = ArrayList()
    private var wafat: MutableList<String>? = ArrayList()
    private var keterangan: MutableList<String>? = ArrayList()
    private var peran: MutableList<String>? = ArrayList()
    private var gambar: MutableList<String>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPahlawanTemplate1Binding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[Pahlawan1TemplateViewModel::class.java]
        setContentView(binding.root)

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }

        viewModel.getPahlawans(token)
        viewModel.responsePahlawan.observe(this) {
            it.data?.get(0).let {
                binding.namaPahlawan.text = it?.attributes?.nama.toString()
                binding.lahirPahlawan.text = it?.attributes?.tglLahir.toString()
                binding.wafatPahlawan.text = it?.attributes?.tglWafat.toString()
                binding.keteranganPahlawan.text = it?.attributes?.keterangan.toString()
                binding.peranPahlawan.text = it?.attributes?.peran.toString()

                val circularProgressDrawable = CircularProgressDrawable(this)
                circularProgressDrawable.strokeWidth = 5f
                circularProgressDrawable.centerRadius = 30f
                circularProgressDrawable.start()

                Glide.with(this)
                    .load("http://165.232.161.137:1337" + it?.attributes?.avatar?.data?.attributes?.url.toString())
                    .placeholder(circularProgressDrawable)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.mipmap.ic_launcher_round)
                    .into(binding.fotoPahlawan)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onTouchEvent(touchEvent: MotionEvent): Boolean {
        when (touchEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                x1 = touchEvent.x
            }
            MotionEvent.ACTION_UP -> {
                x2 = touchEvent.x
                if (x1 < x2) {
                    val i = Intent(this, InstruksiContohActivity::class.java)
                    startActivity(i)
                    Animatoo.animateSlideRight(this)
                } else if (x1 > x2) {
                    val i = Intent(this, PahlawanTemplate2Activity::class.java)
                    startActivity(i)
                    Animatoo.animateSlideLeft(this)
                }
            }
        }
        return false
    }
}