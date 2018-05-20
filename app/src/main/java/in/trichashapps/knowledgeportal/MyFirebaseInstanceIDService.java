package in.trichashapps.knowledgeportal;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import in.trichashapps.knowledgeportal.utils.Logger;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private String TAG = "#MyFirebaseInstanceIDService#";

    @Override public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Logger.logDebug(TAG, "Refreshed token: " + refreshedToken);
    }
}
