package in.trichashapps.knowledgeportal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import in.trichashapps.knowledgeportal.BuildConfig;
import in.trichashapps.knowledgeportal.R;

public class BrandScreenActivity extends BaseActivity {

    @BindView(R.id.tv_version_name) TextView tvVersionName;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeFullScreen();
        setContentView(R.layout.activity_brand_screen);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        tvVersionName.setText(
            getString(R.string.publisher_name) + "\n" + "v: " + BuildConfig.VERSION_NAME);

        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                HomeActivity.startMe(BrandScreenActivity.this);
                finish();
            }
        }, 2000);
    }

    private void makeFullScreen() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }
}
