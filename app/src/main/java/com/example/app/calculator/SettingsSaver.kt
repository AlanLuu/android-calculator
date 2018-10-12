package com.example.app.calculator

import android.app.Application

import com.util.Settings

import java.util.ArrayList

class SettingsSaver : Application() {
    private val settings = ArrayList<Settings>()

    fun makeSettings() {
        settings.add(Settings())
    }

    fun getSettings(index: Int): Settings {
        if (settings.size == 0) {
            settings.add(Settings())
        }
        return settings[index]
    }

    fun getSettings(): Settings {
        if (settings.size == 0) {
            settings.add(Settings())
        }
        return settings[settings.size - 1]
    }
}