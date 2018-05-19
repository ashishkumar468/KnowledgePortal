package in.trichashapps.knowledgeportal.common;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import in.trichashapps.knowledgeportal.models.Post;
import in.trichashapps.knowledgeportal.utils.Logger;

public class App extends Application {

    private static Context context;
    private FirebaseAnalytics firebaseAnalytics;
    private FirebaseDatabase database;
    private static DatabaseReference databaseReference;
    private final static String TAG = "##App##";

    @Override public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        initFirebaseDataBase();
        initFirebaseAnalytics();
    }

    public static Context getContext() {
        return context;
    }

    private void initFirebaseAnalytics() {
        firebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }

    private void initFirebaseDataBase() {
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("KnowledgePortal");
    }

    public static DatabaseReference getDatabaseChildReference() {
        DatabaseReference posts = databaseReference.child("posts");
        /*posts.removeValue();

        for (int i = 0; i < 100; i++) {
            Post post = new Post();
            post.setId((0 - i));
            post.setImageUrl("as" + i);
            post.setTitle("Ashish" + i);
            post.setAuthorName("Ashish Sinha");
            post.setContentText("Heya a simple post ");
            post.setTimestamp(System.currentTimeMillis() + (i * 10));
            post.setVisible(false);
            posts.push().setValue(post).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override public void onSuccess(Void aVoid) {
                    Logger.logDebug(TAG, "  success push post");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override public void onFailure(@NonNull Exception e) {
                    Logger.logDebug(TAG, "failed to push post");
                }
            }).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override public void onComplete(@NonNull Task<Void> task) {
                    Logger.logDebug(TAG, "success push post");
                }
            });
        }*/
        return posts;
    }
}
