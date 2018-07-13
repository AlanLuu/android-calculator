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
                        "https://alanluu.github.io", true);
                aboutTheDev.build();
            }
        });

        binding.circleCanvas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Website circleCanvas = new Website(getContext(), getActivity(),
                        "https://alanluu.github.io/circle-canvas", false);
                circleCanvas.build();
            }
        });

        binding.github.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Website githubRepo = new Website(getContext(), getActivity(),
                        "https://github.com/AlanLuu/android-calculator", false);
                githubRepo.build();
            }
        });

        binding.canvas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(new SquarePattern(getContext()));
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