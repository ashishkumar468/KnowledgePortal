package in.trichashapps.knowledgeportal.presenters;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import in.trichashapps.knowledgeportal.R;
import in.trichashapps.knowledgeportal.common.App;
import in.trichashapps.knowledgeportal.models.Post;
import in.trichashapps.knowledgeportal.presenters.interfaces.ITopPostsView;
import in.trichashapps.knowledgeportal.utils.Logger;
import in.trichashapps.knowledgeportal.utils.MisUtils;
import in.trichashapps.knowledgeportal.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;

public class TopPostsPresenter {
    private static final int NUMBER_OF_PAGE_ELEMENTS = 1000;
    private ITopPostsView view;
    private final DatabaseReference databaseChildReference;
    private static final String TAG = "##TopPostsPresenter##";
    private List<Post> posts;

    public TopPostsPresenter(ITopPostsView view) {
        this.view = view;
        databaseChildReference = App.getDatabaseChildReference();
        posts = new ArrayList<>();
    }

    public void fetchPosts(long nextIdValue) {
        if (null == databaseChildReference) {
            return;
        }

        if (!MisUtils.isConnectedToInternet()) {
            view.showMessage(StringUtils.getString(R.string.no_internet_connection));
        } else {
            view.showProgress(true);
            databaseChildReference.orderByChild("timestamp")
                .limitToFirst(NUMBER_OF_PAGE_ELEMENTS)
                .startAt(nextIdValue)
                .addValueEventListener(new ValueEventListener() {
                    @Override public void onDataChange(DataSnapshot dataSnapshot) {
                        posts.clear();
                        Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                        for (DataSnapshot child : children) {
                            String childGsonString = new Gson().toJson(child.getValue());
                            Post post = new Gson().fromJson(childGsonString, Post.class);
                            Logger.logDebug(TAG, childGsonString);
                            posts.add(post);
                        }
                        view.addPosts(posts);
                        view.showProgress(false);
                    }

                    @Override public void onCancelled(DatabaseError databaseError) {
                        Logger.logError(TAG, databaseError.getMessage());
                        view.showProgress(false);
                    }
                });
        }
    }
}

