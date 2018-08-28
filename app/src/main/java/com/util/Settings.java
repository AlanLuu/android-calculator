package com.util;

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public final class Settings {
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