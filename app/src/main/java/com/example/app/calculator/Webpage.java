package com.example.app.calculator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Webpage {
    private Context context;
    private Activity activity;
    private String url;
    private boolean isWebView;

    private static int numWebpages = 0;

    public Webpage(Context context, Activity activity, String url, boolean isWebView) {
        this.context = context;
        this.activity = activity;
        this.url = url;
        this.isWebView = isWebView;
        numWebpages++;
    }

    public Webpage(Activity activity, Context context, String url, boolean isWebView) {
        this(context, activity, url, isWebView);
    }

    public Context getContext() {
        return context;
    }

    public Activity getActivity() {
        return activity;
    }

    public String getUrl() {
        return url;
    }

    public boolean isWebView() {
        return isWebView;
    }

    public static int getNumWebpages() {
        return numWebpages;
    }

    public void setSite(String url) {
        this.url = url;
    }

    public void setWebView(boolean isWebView) {
        this.isWebView = isWebView;
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void build() {
        if (isWebView) {
            WebView webView = new WebView(context);
            webView.loadUrl(url);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient());
            activity.setContentView(webView);
        } else {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            activity.startActivity(intent);
        }
    }

    @Override
    public String toString() {
        return "URL: " + this.url + ", Is it a static web page? " + (this.isWebView ? "Yes" : "No")
                + ". Number of webpages created: " + numWebpages;
    }
}