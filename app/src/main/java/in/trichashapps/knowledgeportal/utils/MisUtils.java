package in.trichashapps.knowledgeportal.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import in.trichashapps.knowledgeportal.common.App;

public class MisUtils {
    public static boolean isConnectedToInternet() {
        Context context = App.getContext();
        ConnectivityManager cm =
            (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
