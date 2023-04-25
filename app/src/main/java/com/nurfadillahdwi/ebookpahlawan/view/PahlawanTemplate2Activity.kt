package com.nurfadillahdwi.ebookpahlawan.view

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.nurfadillahdwi.ebookpahlawan.R
import com.nurfadillahdwi.ebookpahlawan.databinding.ActivityPahlawanTemplate2Binding
import com.nurfadillahdwi.ebookpahlawan.helper.indexPahlawan
import com.nurfadillahdwi.ebookpahlawan.helper.totalPahlawan
import com.nurfadillahdwi.ebookpahlawan.view.PahlawanTemplate1Activity.Companion.EXTRA_MESSAGE


class PahlawanTemplate2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityPahlawanTemplate2Binding
    private lateinit var viewModel: Pahlawan2TemplateViewModel
    private var x1: Float = 0.0F
    private var x2: Float = 0.0F
    private val token: String =
        "0110ad0929162b63b7fec2e9b08aca1d0003e99f2db7e4ec8880ea6f9c5a4d6882a7f78f3fc417725ed158eb95929b7c39ec8b5b5a68b0cc38468979f1b5ef2c6aae8e7c78ca53862b9a415a018e5a16646c9285500137649c9a9ce2b11629a92bf205fa6590f0186756c061e08775a26892cda1242a7e9351e0e3594d4b5a2b"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPahlawanTemplate2Binding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[Pahlawan2TemplateViewModel::class.java]
        setContentView(binding.root)

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }

        viewModel.getPahlawans(token)
        viewModel.responsePahlawan.observe(this) {
            totalPahlawan = it.data?.size
            it.data?.get(indexPahlawan).let {
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
                    if (indexPahlawan == (totalPahlawan?.div(2))) {
                        val i = Intent(this, PahlawanTemplate1Activity::class.java)
                        startActivity(i)
                        Animatoo.animateSlideRight(this)
                        indexPahlawan--
                    } else if (indexPahlawan == 16 || indexPahlawan == 20 || indexPahlawan == 24) {
                        val i = Intent(this, QouteActivity::class.java)
                        i.putExtra(EXTRA_MESSAGE, "after")
                        startActivity(i)
                        Animatoo.animateSlideRight(this)
                        indexPahlawan--
                    } else {
                        val i = Intent(this, PahlawanTemplate2Activity::class.java)
                        startActivity(i)
                        Animatoo.animateSlideRight(this)
                        indexPahlawan--
                    }
                } else if (x1 > x2) {
                    if (indexPahlawan == totalPahlawan!! - 1) {
                        val i = Intent(this, MKCActivity::class.java)
                        startActivity(i)
                        Animatoo.animateSlideLeft(this)
                    } else if (indexPahlawan == 15 || indexPahlawan == 19 || indexPahlawan == 23) {
                        val i = Intent(this, QouteActivity::class.java)
                        i.putExtra(EXTRA_MESSAGE, "before")
                        startActivity(i)
                        Animatoo.animateSlideLeft(this)
                    } else {
                        val i = Intent(this, PahlawanTemplate2Activity::class.java)
                        startActivity(i)
                        Animatoo.animateSlideLeft(this)
                        indexPahlawan++
                    }

                }
            }
        }
        return false
    }
}