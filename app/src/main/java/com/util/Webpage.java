package com.util;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

@SuppressWarnings({"unused", "WeakerAccess"})
public final class Webpage {
    private Context context;
    private Activity activity;
    private String url;
    private boolean isWebView;

    public Webpage(Context context, Activity activity, String url, boolean isWebView) {
        this.context = context;
        this.activity = activity;
        this.url = url;
        this.isWebView = isWebView;
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

    public void setSite(String url) {
        this.url = url;
    }

    public void setWebView(boolean isWebView) {
        this.isWebView = isWebView;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Webpage)) return false;
        Webpage other = (Webpage) o;
        return this.url.equals(other.getUrl()) &&
                ((this.isWebView && other.isWebView() || (!this.isWebView && !other.isWebView())));
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
            new AlertDialog.Builder(context).setTitle("Notice")
                    .setMessage("This will open in a separate web browser.")
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Uri uri = Uri.parse(url);
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            activity.startActivity(intent);
                        }
                    }).create().show();
        }
    }

    @Override
    public String toString() {
        return "URL: " + this.url + ", Is it a WebView? " + (this.isWebView ? "Yes" : "No");
    }
}