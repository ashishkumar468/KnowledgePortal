package in.trichashapps.knowledgeportal.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import in.trichashapps.knowledgeportal.R;
import in.trichashapps.knowledgeportal.adapters.TopPostsAdapter;
import in.trichashapps.knowledgeportal.models.Post;
import in.trichashapps.knowledgeportal.presenters.TopPostsPresenter;
import in.trichashapps.knowledgeportal.presenters.interfaces.ITopPostsView;
import in.trichashapps.knowledgeportal.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;

public class TopPostsFragment extends BaseFragment implements ITopPostsView {

    @BindView(R.id.pb_posts) ProgressBar pbPosts;
    @BindView(R.id.rv_posts) RecyclerView rvPosts;

    private TopPostsPresenter presenter;
    private TopPostsAdapter adapter;
    long nextIdValue = Long.MIN_VALUE;
    private boolean shouldFetchMore = true;
    private int PAGE_SIZE = 10;
    List<Post> posts;
    private LinearLayoutManager layoutManager;
    private boolean isLoading = false;

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext())
            .inflate(R.layout.fragment_top_posts, container, false);
        return view;
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        init();
    }

    private void init() {
        posts = new ArrayList<>();
        initPresenter();
        initRecyclerView();
        fetchPosts();
    }

    private void fetchPosts() {
        if (shouldFetchMore) {
            isLoading = true;
            presenter.fetchPosts(nextIdValue);
        } else {
            snack(rvPosts, StringUtils.getString(R.string.no_more_posts), R.color.colorWarning,
                Snackbar.LENGTH_SHORT, getContext());
        }
    }

    private void initRecyclerView() {
        adapter = new TopPostsAdapter();
        rvPosts.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getContext());
        rvPosts.setLayoutManager(layoutManager);
        rvPosts.addOnScrollListener(recyclerViewOnScrollListener);
    }

    private void initPresenter() {
        presenter = new TopPostsPresenter(this);
    }

    @Override public void addPosts(List<Post> posts) {
        for (int i = 0; i < posts.size(); i++) {
            Post post = posts.get(i);
            if (isNotAlreadyAdded(post) /*&& post.isVisible()*/) {
                this.posts.add(post);
                shouldFetchMore = true;
            } else {
                shouldFetchMore = false;
            }
        }
        adapter.addPosts(this.posts);
        isLoading = false;
    }

    private boolean isNotAlreadyAdded(Post post) {
        for (int i = 0; i < posts.size(); i++) {
            Post existingPost = posts.get(i);
            if (existingPost.getId() == post.getId()) {
                return false;
            }
        }
        return true;
    }

    @Override public void showProgress(boolean shouldShow) {
        if (shouldShow) {
            pbPosts.setVisibility(View.VISIBLE);
        } else {
            pbPosts.setVisibility(View.GONE);
        }
    }

    @Override public void showMessage(String message) {
        snack(rvPosts, message, R.color.colorWarning, Snackbar.LENGTH_SHORT, getContext());
    }

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener =
        new RecyclerView.OnScrollListener() {
            @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (!isLoading && shouldFetchMore) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= PAGE_SIZE) {
                        if (!posts.isEmpty()) {
                            //Mandate this auto increment of
                            nextIdValue = posts.get(posts.size() - 1).getId() - 1;
                        }
                        fetchPosts();
                    }
                }
            }
        };
}
