package in.trichashapps.knowledgeportal.fragments;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;

public class BaseFragment extends Fragment {
    public void snack(View view, String message, int notificationType, int duration,
        Context context) {
        Snackbar snackbar = Snackbar.make(view, message, duration).setAction("Action", null);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(context, notificationType));
        snackbar.show();
    }
}
