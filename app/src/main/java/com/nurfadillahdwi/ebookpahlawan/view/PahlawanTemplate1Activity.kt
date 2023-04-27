package com.nurfadillahdwi.ebookpahlawan.view

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.nurfadillahdwi.ebookpahlawan.R
import com.nurfadillahdwi.ebookpahlawan.databinding.ActivityPahlawanTemplate1Binding
import com.nurfadillahdwi.ebookpahlawan.helper.indexPahlawan
import com.nurfadillahdwi.ebookpahlawan.helper.totalPahlawan

class PahlawanTemplate1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityPahlawanTemplate1Binding
    private var x1: Float = 0.0F
    private var x2: Float = 0.0F
    private lateinit var viewModel: Pahlawan1TemplateViewModel
    private val token: String =
        "0110ad0929162b63b7fec2e9b08aca1d0003e99f2db7e4ec8880ea6f9c5a4d6882a7f78f3fc417725ed158eb95929b7c39ec8b5b5a68b0cc38468979f1b5ef2c6aae8e7c78ca53862b9a415a018e5a16646c9285500137649c9a9ce2b11629a92bf205fa6590f0186756c061e08775a26892cda1242a7e9351e0e3594d4b5a2b"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPahlawanTemplate1Binding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[Pahlawan1TemplateViewModel::class.java]
        setContentView(binding.root)

        val sharedPref = applicationContext.getSharedPreferences("MyPref", MODE_PRIVATE)
        val namaSiswa = sharedPref.getString("nama", "default").toString()

        viewModel.isLoading.observe(this) {
            showLoading(it)
        }
        viewModel.getPahlawans(token, namaSiswa)
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

        onBackPressedDispatcher.addCallback(this) {
            goBack()
        }
    }

    private fun goBack() {
        if (indexPahlawan == 0) {
            val i = Intent(this, InstruksiContohActivity::class.java)
            startActivity(i)
            Animatoo.animateSlideRight(this)
            finish()
        } else if (indexPahlawan % 4 == 0) { //quote
            val i = Intent(this, QouteActivity::class.java)
            i.putExtra(EXTRA_MESSAGE, "backward")
            startActivity(i)
            Animatoo.animateSlideRight(this)
            finish()
        } else { // back
            indexPahlawan--
            val i = Intent(this, PahlawanTemplate1Activity::class.java)
            startActivity(i)
            Animatoo.animateSlideRight(this)
            finish()
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
                    goBack()
                } else if (x1 > x2) { // next
                    if (indexPahlawan.plus(1) % 4 == 0 && indexPahlawan!=0) { // quote
                        val i = Intent(this, QouteActivity::class.java)
                        i.putExtra(EXTRA_MESSAGE, "forward")
                        startActivity(i)
                        Animatoo.animateSlideLeft(this)
                        finish()
                    } else if (indexPahlawan.plus(2) > totalPahlawan?.div(2)!!) { //next
                        indexPahlawan++
                        val i = Intent(this, PahlawanTemplate2Activity::class.java)
                        startActivity(i)
                        Animatoo.animateSlideLeft(this)
                        finish()
                    } else { //next
                        indexPahlawan++
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

    companion object {
        const val EXTRA_MESSAGE = "extra_message"
    }
}