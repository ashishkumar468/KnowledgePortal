package in.trichashapps.knowledgeportal.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import in.trichashapps.knowledgeportal.R;
import in.trichashapps.knowledgeportal.models.Post;
import in.trichashapps.knowledgeportal.utils.Constants;
import in.trichashapps.knowledgeportal.utils.DateUtils;

public class PostActivity extends BaseActivity {

    @BindView(R.id.tv_title) TextView tvTitle;
    @BindView(R.id.tv_author_name) TextView tvAuthorName;
    @BindView(R.id.tv_post_date) TextView tvPostDate;
    @BindView(R.id.iv_post_image) ImageView ivPostImage;
    @BindView(R.id.tv_post_content) TextView tvPostContent;

    private Post post;

    public static void startMe(Context context, Post post) {
        Intent intent = new Intent(context, PostActivity.class);
        intent.putExtra(Constants.BUNDLE_KEYS.POST, new Gson().toJson(post));
        context.startActivity(intent);
    }

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initPost();
        showPost();
    }

    private void showPost() {
        tvTitle.setText(post.getTitle());
        tvAuthorName.setText(post.getAuthorName());
        tvPostDate.setText(DateUtils.getHumaReadableDate(post.getTimestamp()));
        tvPostContent.setText(post.getContentText());

        if (!TextUtils.isEmpty(post.getImageUrl())) {
            Glide.with(this).load(post.getImageUrl()).into(ivPostImage);
        }
    }

    private void initPost() {
        String postString = getIntent().getStringExtra(Constants.BUNDLE_KEYS.POST);
        post = new Gson().fromJson(postString, Post.class);
    }
}
