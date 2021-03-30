package com.metmit.xp.utils;

import android.util.Log;

import java.util.UUID;

import de.robv.android.xposed.XposedBridge;

public class Helper {

    public static void log(String message) {
        log(message, Container.LOG_TAG);
    }

    public static void log(String message, String tag) {
        XposedBridge.log(tag + ": " + message);
        Log.i(tag, message);
    }

    public static long getSecondTime() {
        return Integer.parseInt(String.valueOf(Helper.getMilliTime() / 1000));
    }

    public static long getMilliTime() {
        return System.currentTimeMillis();
    }

    public static String getUuid() {
        return UUID.randomUUID().toString();
    }
}
