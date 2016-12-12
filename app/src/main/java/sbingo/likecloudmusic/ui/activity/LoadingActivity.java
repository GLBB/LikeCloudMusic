package sbingo.likecloudmusic.ui.activity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import butterknife.BindView;
import sbingo.likecloudmusic.R;

/**
 * Author: Sbingo
 * Date:   2016/12/11
 */

public class LoadingActivity extends BaseActivity {


    @BindView(R.id.loading)
    FrameLayout loading;

    @Override
    public int getLayoutId() {
        return R.layout.activity_loading;
    }

    @Override
    public void initInjector() {

    }

    @Override
    public void initViews() {
    }

    @Override
    public void customToolbar() {

    }

    @Override
    protected boolean hasToolbar() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loading.postDelayed(loadingOut, 1500);
    }

    Runnable loadingOut = new Runnable() {
        @Override
        public void run() {
            Animation animation = AnimationUtils.loadAnimation(LoadingActivity.this, R.anim.loading_fade_out);
            loading.startAnimation(animation);
            loading.postDelayed(toMainActivity, 2000);
        }
    };
    Runnable toMainActivity = new Runnable() {
        @Override
        public void run() {
            startActivityTo(MainActivity.class);
            finish();
        }
    };
}
