package com.example.app.calculator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * A Website object represents a website embed in either a WebView or a separate activity.
 * @author Alan Luu
 * @version 1.0
 */

@SuppressWarnings({"unused", "WeakerAccess"})
public class Website {
    /** Interface to global information about an application environment. It allows access to application-specific resources and classes, as well as up-calls for application-level operations such as launching activities, broadcasting and receiving intents, etc. */
    private Context context;

    /** An activity is a single, focused thing that the user can do. Almost all activities interact with the user, so the Activity class takes care of creating a window for you in which you can place your UI with setContentView(View). */
    private Activity activity;

    /** URL of the Website object */
    private String url;

    /** Tells if the website is part of a WebView or not */
    private boolean isWebView;

    /** An integer representing the amount of Website objects constructed in total. */
    private static int numWebsites = 0;

    /**
     * Constructs a website of given URL.
     * @param context Interface to global information about an application environment. It allows access to application-specific resources and classes, as well as up-calls for application-level operations such as launching activities, broadcasting and receiving intents, etc.
     * @param activity An activity is a single, focused thing that the user can do. Almost all activities interact with the user, so the Activity class takes care of creating a window for you in which you can place your UI with setContentView(View).
     * @param url URL of the website object
     * @param isWebView tells if the website is part of a WebView or not
     */
    public Website(Context context, Activity activity, String url, boolean isWebView) {
        this.context = context;
        this.activity = activity;
        this.url = url;
        this.isWebView = isWebView;
        numWebsites++;
    }

    /**
     * Constructs a website of given URL.
     */
    public Website(Activity activity, Context context, String url, boolean isWebView) {
        this(context, activity, url, isWebView);
    }

    /**
     * Returns the passed in Context object.
     * @return the passed in context object.
     */
    public Context getContext() {
        return context;
    }

    /**
     * Returns the passed in Activity object.
     * @return the passed in Activity object.
     */
    public Activity getActivity() {
        return activity;
    }

    /**
     * Returns the URL of this Website.
     * @return the string indicating the URL of this Website.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Returns whether the website is part of a WebView object or is a separate activity.
     * @return the boolean indicating whether the constructed Website is part of a WebView object or is a separate activity.
     */
    public boolean isWebView() {
        return isWebView;
    }

    /**
     * Returns the number of Website objects constructed in total.
     * @return an int representing how many Website objects have been constructed.
     */
    public static int getNumWebsites() {
        return numWebsites;
    }

    /**
     * Sets the URL of the website to the specified URL.
     * @param url The new URL of the website
     */
    public void setSite(String url) {
        this.url = url;
    }

    /**
     * Toggles between the website being part of a WebView object or being a separate activity.
     * @param isWebView a boolean indicating whether the constructed Website is part of a WebView object or is a separate activity.
     */
    public void setWebView(boolean isWebView) {
        this.isWebView = isWebView;
    }

    /**
     * Compares this website to the specified website.
     * @param other the website to compare this website against
     * @return <i>true</i> if the given website has the same url and is the same isWebView boolean as this website, <i>false</i> otherwise.
     */
    public boolean equals(Website other) {
        return this.url.equals(other.getUrl()) &&
                ((this.isWebView && other.isWebView() || (!this.isWebView && !other.isWebView())));
    }

    /**
     * Builds the Activity or the WebView containing the website.
     */
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

    /**
     * Returns a string representation containing this website's URL, whether it is a WebView, and the number of Website objects constructed in total. This method is intended to be used only for debugging purposes.
     * @return a string representation of this website
     */
    @Override
    public String toString() {
        return "URL: " + this.url + ", Is it a WebView? " + (this.isWebView ? "Yes" : "No")
                + ". Number of Website objects created: " + numWebsites;
    }
}