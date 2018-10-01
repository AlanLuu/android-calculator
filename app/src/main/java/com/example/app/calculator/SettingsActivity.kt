package com.example.app.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch

class SettingsActivity : AppCompatActivity() {
    private var switches: Array<Switch?>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        switches = kotlin.arrayOfNulls<Switch?>(2)

        val actionbar = supportActionBar
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(false)
            actionbar.setHomeAsUpIndicator(null)
        }

        switches!![0] = findViewById(R.id.undoSwitch)
        switches!![1] = findViewById(R.id.disableTrigSwitch)

        for (i in switches!!.indices) {
            val settings = (application as SettingsSaver).getSettings(i)
            switches!![i]!!.isChecked = settings.isSwitchOn
        }
    }

    public override fun onStop() {
        super.onStop()
        for (i in switches!!.indices) {
            val settings = (application as SettingsSaver).getSettings(i)
            settings.setSwitchState(switches!![i]!!.isChecked)
        }
    }
}