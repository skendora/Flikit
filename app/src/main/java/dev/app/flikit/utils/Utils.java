package dev.app.flikit.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Utils {

    public boolean isNetworkAvailable(Context context) {
        boolean connected = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {

            NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
            connected = activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();

        }
        return connected;
    }
}
