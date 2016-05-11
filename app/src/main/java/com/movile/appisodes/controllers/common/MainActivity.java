package com.movile.appisodes.controllers.common;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.ImageView;

import com.movile.appisodes.R;
import com.movile.appisodes.controllers.login.LoginActivity;
import com.movile.appisodes.utils.AnimationUtils;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * This is the main activity where the slpash screen is displayed and where the decision to go to
 * login or to home screen is taken
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActionBarActivity {

    /** Constant for animations duration **/
    private static final int ANIM_DURATION = 1000;

    /** Delay for the splash view **/
    private static final int SPLASH_DELAY = 2500;

    /** Appisodes logo ImageView **/
    @InjectView(R.id.appisodes_iv)
    private ImageView mAppisodesIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fadeAnimation(mAppisodesIv);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                goLogin();
            }
        }, SPLASH_DELAY);

    }

    /**
     * This method redirects to HomeActivity
     */
    private void goLogin() {
        Intent loginIntent = new Intent(this, LoginActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(this, mAppisodesIv,
                        getString(R.string.transition_body));
        startActivity(loginIntent, options.toBundle());
    }

    /**
     * This method creates a fade animation that suddenly appears "in front" of the screen to the
     * back of the screen
     */
    private void fadeAnimation(View view) {
        ObjectAnimator scaleXAnimation = AnimationUtils
                .createObjectAnimator(view, "scaleX", 5.0F, 1.0F, ANIM_DURATION);
        ObjectAnimator scaleYAnimation = AnimationUtils
                .createObjectAnimator(view, "scaleY", 5.0F, 1.0F, ANIM_DURATION);
        ObjectAnimator alphaAnimation = AnimationUtils
                .createObjectAnimator(view, "alpha", 0.0F, 1.0F, ANIM_DURATION);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleXAnimation).with(scaleYAnimation).with(alphaAnimation);
        animatorSet.start();
    }

}
