package com.example.app.calculator;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Switch;

import com.utility.Settings;

public class SettingsActivity extends AppCompatActivity {
    private Switch[] switches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        switches = new Switch[2];

        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(false);
            actionbar.setHomeAsUpIndicator(null);
        }

        switches[0] = findViewById(R.id.undoSwitch);
        switches[1] = findViewById(R.id.disableTrigSwitch);

        for (int i = 0; i < switches.length; i++) {
            Settings settings = ((SettingsSaver)getApplication()).getSettings(i);
            switches[i].setChecked(settings.isSwitchOn());
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        for (int i = 0; i < switches.length; i++) {
            Settings settings = ((SettingsSaver)getApplication()).getSettings(i);
            settings.setSwitchState(switches[i].isChecked());
        }
    }
}