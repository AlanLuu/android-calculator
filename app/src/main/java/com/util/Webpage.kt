package com.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient

@Suppress("MemberVisibilityCanBePrivate")
class Webpage(val context: Context, private val activity: Activity, var url: String, var isWebView: Boolean = true,
              var javaScriptEnabled: Boolean = false) {
    @SuppressLint("SetJavaScriptEnabled")
    fun build() {
        if (isWebView) {
            val webView = WebView(context)
            webView.loadUrl(url)
            webView.settings.javaScriptEnabled = javaScriptEnabled
            webView.webViewClient = WebViewClient()
            webView.webChromeClient = WebChromeClient()
            activity.setContentView(webView)
        } else {
            val uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            activity.startActivity(intent)
        }
    }
}