package com.util

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
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
            AlertDialog.Builder(context).setTitle("Notice")
                    .setMessage("This will open in a separate web browser.")
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes) { _, _ ->
                        val uri = Uri.parse(url)
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        activity.startActivity(intent)
                    }.create().show()
        }
    }
}