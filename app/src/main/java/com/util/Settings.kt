package com.util

class Settings {
    var isSwitchOn: Boolean = false
        private set

    init {
        className = javaClass.name
    }

    fun setSwitchState(b: Boolean) {
        isSwitchOn = b
    }

    companion object {
        private var className: String = ""
    }
}