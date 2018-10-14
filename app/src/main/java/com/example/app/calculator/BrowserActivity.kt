package com.example.app.calculator

import android.app.Activity
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast

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
        actionbar!!.setDisplayHomeAsUpEnabled(false)
        actionbar.setHomeAsUpIndicator(null)

        val myItems: Array<String> = arrayOf(
                "About the creator",
                "Github repo",
                "Following circle",
                "Go to YouTube",
                "Random Wikipedia article",
                "Gravity simulator",
                "Random circles"
        )
        val adapter = ArrayAdapter<String>(this, R.layout.listview_item, myItems)
        binding.listView.adapter = adapter

        binding.listView.setOnItemClickListener {_, view, _, _ ->
            val text = (view as TextView).text
            when (text) {
                "About the creator" -> {
                    actionbar.hide()
                    Webpage(context, activity, "file:///android_asset/about.html").build()
                }
                "Github repo" -> {
                    Webpage(context, activity, "https://github.com/AlanLuu/android-calculator",
                            false).build()
                }
                "Following circle" -> {
                    actionbar.hide()
                    Webpage(context, activity, "https://alanluu.github.io/circle-canvas/",
                            javaScriptEnabled = true).build()
                }
                "Go to YouTube" -> {
                    actionbar.hide()
                    Webpage(context, activity, "https://www.youtube.com", javaScriptEnabled = true).build()
                }
                "Random Wikipedia article" -> {
                    actionbar.hide()
                    Webpage(context, activity, "https://en.wikipedia.org/wiki/Special:Random").build()
                }
                "Gravity simulator" -> {
                    actionbar.title = "Gravity simulator"
                    val g = GravitySim(applicationContext)
                    setContentView(g)
                    Thread(g).start()
                }
                "Random circles" -> {
                    actionbar.title = "Random circles"
                    val r = RandomCircles(applicationContext)
                    setContentView(r)
                    Thread(r).start()
                }
                else -> Toast.makeText(context, "Invalid option", Toast.LENGTH_SHORT).show() //Should never happen
            }
        }
    }
}