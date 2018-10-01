package com.example.app.calculator

import android.app.Activity
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.example.app.calculator.databinding.ActivityBrowserBinding
import com.util.Webpage

class BrowserActivity : AppCompatActivity() {

    private val context: Context
        get() = this

    private val activity: Activity
        get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityBrowserBinding>(this, R.layout.activity_browser)

        val actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(false)
            actionbar.setHomeAsUpIndicator(null)
        }

        binding.aboutMe.setOnClickListener {
            val aboutTheDev = Webpage(context, activity,
                    "https://alanluu.github.io", true)
            aboutTheDev.build()
        }

        binding.circleCanvas.setOnClickListener {
            val circleCanvas = Webpage(context, activity,
                    "https://alanluu.github.io/circle-canvas", false)
            circleCanvas.build()
        }

        binding.github.setOnClickListener {
            val githubRepo = Webpage(context, activity,
                    "https://github.com/AlanLuu/android-calculator", false)
            githubRepo.build()
        }

        binding.gravity.setOnClickListener {
            if (actionbar != null) actionbar.title = "Gravity simulator"
            val g = GravitySim(applicationContext)
            setContentView(g)
            Thread(g).start()
        }

        binding.circles.setOnClickListener {
            if (actionbar != null) actionbar.title = "Random circles"
            val r = RandomCircles(applicationContext)
            setContentView(r)
            Thread(r).start()
        }
    }
}