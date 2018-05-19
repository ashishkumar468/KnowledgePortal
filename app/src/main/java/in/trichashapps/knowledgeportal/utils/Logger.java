package in.trichashapps.knowledgeportal.utils;

import android.util.Log;

public class Logger {
    public static void logDebug(String tag, String message) {
        Log.d(tag, message);
    }

    public static void logError(String tag, String message) {
        Log.e(tag, message);
    }
}
