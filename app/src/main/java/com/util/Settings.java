package com.util;

@SuppressWarnings("all")
public class Settings {
    private static String className;
    private boolean switchOn;

    public Settings() {
        className = getClass().getName();
    }

    public boolean isSwitchOn() {
        return switchOn;
    }

    public void setSwitchState(boolean b) {
        switchOn = b;
    }
}