package com.example.app.calculator;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.app.calculator.databinding.ActivityBrowserBinding;

public class BrowserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBrowserBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_browser);
        setSupportActionBar(binding.toolbar);

        binding.aboutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Website aboutTheDev = new Website(getContext(), getActivity(),
                        "file:///android_asset/aboutTheDeveloper.html", true);
                aboutTheDev.build();
            }
        });

        binding.circleCanvas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Website circleCanvas = new Website(getContext(), getActivity(),
                        "https://luualan9545.github.io/circle-canvas", false);
                circleCanvas.build();
            }
        });
    }

    private Context getContext() {
        return this;
    }

    private Activity getActivity() {
        return (Activity) this;
    }
}