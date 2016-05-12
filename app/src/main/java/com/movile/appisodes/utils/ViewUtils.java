package com.movile.appisodes.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.View;

import com.github.johnpersano.supertoasts.SuperToast;
import com.github.johnpersano.supertoasts.util.Style;
import com.movile.appisodes.custom_views.progress_bars.ProgressWheel;

/**
 * Utils class to support basic and common view operations
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public final class ViewUtils {

    /** Private constructor to avoid instances **/
    private ViewUtils() {}

    /**
     * This method makes a toast.
     *
     * @param context
     *         Current context
     * @param resId
     *         String to be shown
     * @param duration
     *         Toast duration
     * @param color
     *         SuperToast color. Selected from {@link Style} constants, such as, {@link Style#BLUE}
     *
     * @return SuperToast instance
     */
    @NonNull
    public static SuperToast makeToast(Context context, @StringRes int resId, int duration,
                                       int color) {
        return makeToast(context, context.getString(resId), duration, color);
    }

    /**
     * This method makes a toast.
     *
     * @param context
     *         Current context
     * @param text
     *         String to be shown
     * @param duration
     *         Toast duration
     * @param color
     *         SuperToast color. Selected from {@link Style} constants, such as, {@link Style#BLUE}
     *
     * @return SuperToast instance
     */
    @NonNull
    public static SuperToast makeToast(Context context, String text, int duration, int color) {
        return SuperToast.create(context, text, duration,
                Style.getStyle(color, SuperToast.Animations.FLYIN));
    }

    /**
     * This method enables/disables the progress wheel. All related views will be hide/visible
     * depending on the progress wheel state
     *
     * @param pw
     *         The progress wheel
     * @param enable
     *         Enable/Disable
     * @param views
     *         Related views that will be hide/visible depending on the progress wheel state
     */
    public static void enableProgressWheel(ProgressWheel pw, boolean enable, View... views) {
        if (pw == null) {
            return;
        }
        pw.setVisibility(enable ? View.VISIBLE : View.INVISIBLE);
        if (pw.isSpinning()) {
            pw.stopSpinning();
        }

        if (enable) {
            pw.spin();
        }

        for (View view : views) {
            if (view != null) {
                view.setVisibility(enable ? View.INVISIBLE : View.VISIBLE);
            }
        }
    }

}
