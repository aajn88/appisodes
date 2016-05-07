package com.movile.appisodes.utils;

import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * This utils class provide standard animation methods
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public final class AnimationUtils {

    /** Private constructor to avoid instances **/
    private AnimationUtils() {}

    /**
     * This method creates an Object Animator based on the targeted view, the property to be
     * animated and the initial value and final value
     *
     * @param view
     *         Target view
     * @param property
     *         Property to be animated
     * @param init
     *         Initial value
     * @param end
     *         Final value
     * @param duration
     *         Animation duration
     *
     * @return ObjectAnimator with the given animated property
     */
    @NonNull
    public static ObjectAnimator createObjectAnimator(View view, String property, float init, float end,
                                                long duration) {
        ObjectAnimator scaleXAnimation = ObjectAnimator.ofFloat(view, property, init, end);
        scaleXAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleXAnimation.setDuration(duration);
        return scaleXAnimation;
    }

}
