package com.tcs.edureka.utility;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.format.DateUtils;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.model.LatLng;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

/**
 * @author Bhuvaneshvar
 */
public class Utility {
    private static final String TAG = "Utility";
    public static String sliceTitle = "";
    public static String sliceSubtitle = "";
    private static LatLng currentUserPrefLocation = null;
    public static String[] MONTH_LIST = new String[]{"January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December"};

    public static boolean isPermissionGranted(Context context, String permission) {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static String PREFF_CITY;

    public static String getPreffCity() {
        return PREFF_CITY;
    }

    public static void setPreffCity(String city) {
        PREFF_CITY = city;
    }

    public static String getTodayTommorowOrDate(Date date) {
        int hours = date.getHours();
        int minute = date.getMinutes();
        if (DateUtils.isToday(date.getTime()))
            return "Today at " + hours + " : " + minute;

        if (DateUtils.isToday(date.getTime() - DateUtils.DAY_IN_MILLIS))
            return "Tomorrow at " + hours + " : " + minute;

        return new StringBuilder().append(date.getDate()).append("/") //-> 7/
                .append(MONTH_LIST[date.getMonth() + 1]).append("/") //-> August/
                .append(date.getYear() + 1900)
                .append(" at ").append(hours).append(" : ").append(minute)
                .toString();

    }

    public static String getCurrentUserName() {
        return "bhuavan1";
    }

    public static LatLng getPreffLocation() {
        return currentUserPrefLocation;
    }

    public static void setPreffLocation(LatLng latLng) {
        currentUserPrefLocation = latLng;
    }

    public static void updateSliceTextAndSubtitle(@NotNull String locationName, String title, String subtitle, Context context) {
        sliceTitle = title;
        sliceSubtitle = locationName + " " + subtitle;
        Uri uri = getUri(context, "map");
        context.getContentResolver().notifyChange(uri, null);

    }

    public static void makeToast(String msg, Context context) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }


    public static Uri getUri(Context context, String path) {
        return new Uri.Builder()
                .scheme(ContentResolver.SCHEME_CONTENT)
                .authority(context.getPackageName() + ".providers")
                .appendPath(path)
                .build();
    }
}
