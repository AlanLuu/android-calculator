package com.util

import android.app.Activity
import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Toast

import java.util.Arrays

/**
 * Provides various static methods to make my life easier.
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
object Utility {
    fun handleException(context: Context, activity: Activity, view: View, e: Throwable) {
        val errorSnackbar = Snackbar.make(view, "Oops, something went wrong.", Snackbar.LENGTH_INDEFINITE)
        errorSnackbar.setAction("Send error report") {
            val numArr = intArrayOf(97, 108, 97, 110, 108, 117, 117, 52, 64, 103, 109, 97, 105, 108, 46, 99, 111, 109)
            val builder = StringBuilder()
            for (value in numArr) {
                builder.append(value.toChar())
            }

            val email = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + builder.toString()))
            email.putExtra(Intent.EXTRA_TEXT, versionInfo(context) + "\n" + e.toString() + "\n\n" + Arrays.toString(e.stackTrace))

            try {
                activity.startActivity(Intent.createChooser(email, "Send email"))
            } catch (ee: ActivityNotFoundException) {
                Toast.makeText(context, "There is no email client installed on this device.", Toast.LENGTH_SHORT).show()
            }
        }
        errorSnackbar.setActionTextColor(Color.parseColor("#46bdbf"))
        errorSnackbar.show()
    }

    fun alert(context: Context, message: String) {
        val alert = AlertDialog.Builder(context)
        alert.setMessage(message)
        alert.setPositiveButton(android.R.string.yes, null)
        alert.show()
    }

    fun confirm(context: Context, title: String, message: String, listener: (dialog: DialogInterface, which: Int) -> Unit) {
        val alert = AlertDialog.Builder(context)
        alert.setTitle(title)
        alert.setMessage(message)
        alert.setNegativeButton(android.R.string.no, null)
        alert.setPositiveButton(android.R.string.yes, listener)
        alert.show()
    }

    fun sendEmail(to: String, subject: String, body: String, context: Context, activity: Activity, vararg cc: String) {
        val email = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$to"))
        email.putExtra(Intent.EXTRA_SUBJECT, subject)
        email.putExtra(Intent.EXTRA_CC, cc)
        email.putExtra(Intent.EXTRA_TEXT, body)

        try {
            activity.startActivity(Intent.createChooser(email, "Send email"))
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, "There is no email client installed on this device.", Toast.LENGTH_SHORT).show()
        }
    }

    fun sendDebugEmail(to: String, subject: String, context: Context, activity: Activity, vararg cc: String) {
        sendEmail(to, subject, versionInfo(context), context, activity, *cc)
    }

    fun share(activity: Activity, subject: String, text: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, text)
        activity.startActivity(Intent.createChooser(intent, "Share"))
    }

    fun getRandomInt(min: Int, max: Int): Int {
        if (min >= max) {
            throw IllegalArgumentException("Error: min cannot be greater than or equal to max.")
        }
        return (Math.random() * (max - min + 1)).toInt() + min
    }

    fun versionInfo(context: Context): String {
        val pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        return "App version: ${pInfo.versionName}\n" +
                "Android version: ${android.os.Build.VERSION.RELEASE}\n" +
                "-----------------------------------\n"
    }

    fun binarySearch(arr: IntArray, target: Int): Int {
        var min = 0
        var max = arr.size - 1

        for (i in min until max) {
            if (arr[i] > arr[i + 1]) {
                throw IllegalArgumentException("Array is not sorted.")
            }
        }

        while (min <= max) {
            val guess = (min + max) / 2
            when {
                arr[guess] == target -> return guess
                arr[guess] < target -> min = guess + 1
                else -> max = guess - 1
            }
        }
        return -1
    }
}