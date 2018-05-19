package in.trichashapps.knowledgeportal.presenters.interfaces;

import in.trichashapps.knowledgeportal.models.Post;
import java.util.List;

public interface ITopPostsView extends MvpView {
    void addPosts(List<Post> posts);

    void showProgress(boolean shouldShow);

    void showMessage(String message);
}
