package com.movile.appisodes;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.movile.appisodes.utils.AnimationUtils;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

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
                goHome();
            }
        }, SPLASH_DELAY);

    }

    /**
     * This method redirects to HomeActivity
     */
    private void goHome() {
        // TODO: Go home
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
