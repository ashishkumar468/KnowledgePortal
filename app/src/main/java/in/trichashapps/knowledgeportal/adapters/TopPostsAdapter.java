package in.trichashapps.knowledgeportal.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import in.trichashapps.knowledgeportal.R;
import in.trichashapps.knowledgeportal.activities.PostActivity;
import in.trichashapps.knowledgeportal.models.Post;
import in.trichashapps.knowledgeportal.utils.DateUtils;
import java.util.ArrayList;
import java.util.List;

public class TopPostsAdapter extends RecyclerView.Adapter<TopPostsAdapter.ViewHolder> {
    List<Post> posts;
    private Context context;

    public TopPostsAdapter() {
        this.posts = new ArrayList<>();
    }

    @NonNull @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.row_item_top_posts, parent, false);
        context = parent.getContext();
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.init(position);
    }

    @Override public int getItemCount() {
        return posts.size();
    }

    public void addPosts(List<Post> posts) {
        this.posts.addAll(posts);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_container_post) LinearLayout llContainerPost;
        @BindView(R.id.iv_post_image) ImageView ivPostImage;
        @BindView(R.id.tv_title) TextView tvTitle;
        @BindView(R.id.tv_post_date) TextView tvPostDate;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void init(int position) {
            final Post post = posts.get(position);
            if (!TextUtils.isEmpty(post.getImageUrl())) {
                Glide.with(context).load(post.getImageUrl()).into(ivPostImage);
            }

            tvPostDate.setText(DateUtils.getHumaReadableDate(post.getTimestamp()));
            tvTitle.setText(post.getTitle());

            llContainerPost.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View view) {
                    PostActivity.startMe(context, post);
                }
            });
        }
    }
}
