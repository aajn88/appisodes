package com.movile.appisodes.utils;

import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.BaseAdapter;

import com.nhaarman.listviewanimations.appearance.AnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.ScaleInAnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.SwingBottomInAnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.SwingLeftInAnimationAdapter;
import com.nhaarman.listviewanimations.appearance.simple.SwingRightInAnimationAdapter;

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

    /**
     * This method returns an animation for an adapter given the base adapter and a key
     *
     * @param key
     *         Key from 0 to 4 to select an animation
     * @param adapter
     *         The base adapter that will take the animation adapter
     *
     * @return Animated Adapter
     */
    public static AnimationAdapter animateAdapter(int key, BaseAdapter adapter) {
        AnimationAdapter animAdapter;
        switch (key) {
            default:
            case 0:
                animAdapter = new AlphaInAnimationAdapter(adapter);
                break;
            case 1:
                animAdapter = new ScaleInAnimationAdapter(adapter);
                break;
            case 2:
                animAdapter = new SwingBottomInAnimationAdapter(adapter);
                break;
            case 3:
                animAdapter = new SwingLeftInAnimationAdapter(adapter);
                break;
            case 4:
                animAdapter = new SwingRightInAnimationAdapter(adapter);
                break;
        }
        return animAdapter;
    }

}
