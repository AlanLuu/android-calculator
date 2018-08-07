package com.utility;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import java.util.Arrays;

/**
 * This class provides various static methods to make my life easier.
 */

//Utility cannot be subclassed.

@SuppressWarnings({"unused", "WeakerAccess"})
public final class Utility {
    //Utility cannot be instantiated, and will throw an exception if attempted to do so.
    private Utility() {
        throw new UnsupportedOperationException("Error: Utility class cannot be instantiated.");
    }

    public static void handleException(final Context context, final Activity activity, View view, final Throwable e) {
        final Snackbar errorSnackbar = Snackbar.make(view, "Oops, something went wrong.", Snackbar.LENGTH_INDEFINITE);
        errorSnackbar.setAction("Send error report", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] numArr = {97, 108, 97, 110, 108, 117, 117, 52, 64, 103, 109, 97, 105, 108, 46, 99, 111, 109};
                StringBuilder builder = new StringBuilder();
                for (int value : numArr) {
                    builder.append((char) (value));
                }

                String[] TO = {builder.toString()};

                Intent email = new Intent(Intent.ACTION_SEND);
                email.setData(Uri.parse("mailto:"));
                email.setType("message/rfc822");
                email.putExtra(Intent.EXTRA_EMAIL, TO);
                email.putExtra(Intent.EXTRA_SUBJECT, "Error report");
                email.putExtra(Intent.EXTRA_TEXT, e.toString() + "\n\n" + Arrays.toString(e.getStackTrace()));

                try {
                    activity.startActivity(Intent.createChooser(email, "Send email"));
                } catch (ActivityNotFoundException ee) {
                    Toast.makeText(context, "There is no email client installed on this device.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        errorSnackbar.show();
    }

    public static void sendEmail(Context context, Activity activity, String to, String... cc) {
        String[] TO = {to};

        Intent email = new Intent(Intent.ACTION_SEND);
        email.setData(Uri.parse("mailto:"));
        email.setType("message/rfc822");
        email.putExtra(Intent.EXTRA_EMAIL, TO);
        if (noNullInArray(cc)) {
            email.putExtra(Intent.EXTRA_CC, cc);
        }

        try {
            activity.startActivity(Intent.createChooser(email, "Send email"));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "There is no email client installed on this device.", Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean noNullInArray(String[] arr) {
        for (String s : arr) {
            if (s == null) {
                return false;
            }
        }
        return true;
    }

    public static int getRandomInt(int min, int max) {
        if (min >= max || max <= min) {
            throw new IllegalArgumentException(min >= max ? "Error: min cannot be greater than or equal to max."
                    : "Error: max cannot be less than or equal to min.");
        }
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public static int findInt(int[] arr, int target) {
        Arrays.sort(arr);
        int min = 0;
        int max = arr.length - 1;

        while (min <= max) {
            int guess = (min + max) / 2;
            if (arr[guess] == target) {
                return guess;
            } else if (arr[guess] < target) {
                min = guess + 1;
            } else {
                max = guess - 1;
            }
        }
        return -1;
    }
}