package com.movile.appisodes.controllers.login;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.movile.appisodes.R;
import com.movile.appisodes.utils.AnimationUtils;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * This is the Login Activity where Trakt authentication should be done-
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
@ContentView(R.layout.activity_login)
public class LoginActivity extends RoboActionBarActivity implements View.OnClickListener {

    /** Constant for animations duration **/
    private static final int ANIM_DURATION = 500;

    /** Constant for delays duration **/
    private static final int DELAY_DURATION = 1000;

    /** Sign in TextView **/
    @InjectView(R.id.signin_rtv)
    private TextView mSigninRtv;

    /** Sign in MaterialRippleLayout **/
    @InjectView(R.id.signin_trakt_mrl)
    private View mSigninMrl;

    /** Sign in Trakt LinearLayout **/
    @InjectView(R.id.signin_trakt_ll)
    private View mSigninTraktLl;

    /** Header LinearLayout **/
    @InjectView(R.id.header_ll)
    private View mHeaderLl;

    /** Footer LinearLayout **/
    @InjectView(R.id.footer_ll)
    private View mFooterLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fadeTransition();

        mSigninMrl.setOnClickListener(this);
    }

    /**
     * This method completes the transition with a fade animation
     */
    private void fadeTransition() {
        mSigninRtv.setAlpha(0);
        mSigninMrl.setAlpha(0);
        mHeaderLl.setAlpha(0);
        mFooterLl.setAlpha(0);

        ObjectAnimator tvAnim = AnimationUtils
                .createObjectAnimator(mSigninRtv, "alpha", 0.0F, 1.0F, ANIM_DURATION);
        ObjectAnimator rippleAnim = AnimationUtils
                .createObjectAnimator(mSigninMrl, "alpha", 0.0F, 1.0F, ANIM_DURATION);
        ObjectAnimator headerAnim = AnimationUtils
                .createObjectAnimator(mHeaderLl, "alpha", 0.0F, 1.0F, ANIM_DURATION);
        ObjectAnimator footerAnim = AnimationUtils
                .createObjectAnimator(mFooterLl, "alpha", 0.0F, 1.0F, ANIM_DURATION);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(tvAnim).with(rippleAnim).with(headerAnim).with(footerAnim);
        animatorSet.setStartDelay(DELAY_DURATION);
        animatorSet.start();
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v
     *         The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signin_trakt_ll:
                Intent generateCodeIntent = new Intent(this, GenerateCodeActivity.class);
                startActivity(generateCodeIntent);
                break;
        }
    }
}
