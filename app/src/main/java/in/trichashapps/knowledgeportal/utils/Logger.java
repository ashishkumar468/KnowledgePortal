package in.trichashapps.knowledgeportal.utils;

import android.util.Log;
import in.trichashapps.knowledgeportal.BuildConfig;

public class Logger {
    public static void logDebug(String tag, String message) {
        if (BuildConfig.BUILD_TYPE != "release") {
            Log.d(tag, message);
        }
    }

    public static void logError(String tag, String message) {
        if (BuildConfig.BUILD_TYPE != "release") {
            Log.e(tag, message);
        }
    }
}
