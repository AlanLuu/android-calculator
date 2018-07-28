package com.example.app.calculator;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

public class SettingsSaver extends Application {
    private List<Settings> settings = new ArrayList<>();

    public void makeSettings() {
        settings.add(new Settings());
    }

    public void clearSettings() {
        settings.clear();
    }

    public int getSettingsLength() {
        return settings.size();
    }

    public Settings getSettings(int index) {
        if (settings.size() == 0) {
            settings.add(new Settings());
        }
        return settings.get(index);
    }

    public Settings getSettings() {
        if (settings.size() == 0) {
            settings.add(new Settings());
        }
        return settings.get(settings.size() - 1);
    }
}