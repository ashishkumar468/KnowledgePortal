package in.trichashapps.knowledgeportal.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import in.trichashapps.knowledgeportal.R;
import in.trichashapps.knowledgeportal.fragments.TopPostsFragment;
import in.trichashapps.knowledgeportal.utils.MisUtils;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.fl_top_posts) FrameLayout flTopPosts;
    private TopPostsFragment topPostsFragment;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        init();
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            /*case android.R.id.home:
                onBackPressed();
                return false;*/
            case R.id.menu_share:
                MisUtils.shareApp(this);
                break;
            //no need for default
        }
        return super.onOptionsItemSelected(item);
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

    public static void startMe(Activity activity) {
        Intent intent = new Intent(activity, HomeActivity.class);
        activity.startActivity(intent);
    }
}
