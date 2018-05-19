package in.trichashapps.knowledgeportal.activities;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import in.trichashapps.knowledgeportal.R;
import in.trichashapps.knowledgeportal.fragments.TopPostsFragment;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.fl_top_posts) FrameLayout flTopPosts;
    private TopPostsFragment topPostsFragment;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initFragments();
        showTopPostsFragment();
    }

    private void showTopPostsFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fl_top_posts, topPostsFragment);
        transaction.commit();
    }

    private void initFragments() {
        topPostsFragment = new TopPostsFragment();
    }
}
