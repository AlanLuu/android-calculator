package com.example.app.calculator;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class BrowserActivity extends AppCompatActivity {
    private final Context context = this;
    private final Activity activity = (Activity) this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button aboutMe = findViewById(R.id.aboutMe);
        aboutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Webpage aboutTheDev = new Webpage(context, activity, "file:///android_asset/aboutTheDeveloper.html", true);
                aboutTheDev.build();
            }
        });

        Button circleCanvas = findViewById(R.id.circleCanvas);
        circleCanvas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Webpage circleCanvas = new Webpage(context, activity, "https://luualan9545.github.io/circle-canvas", false);
                circleCanvas.build();
            }
        });
    }
}