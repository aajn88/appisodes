package com.movile.appisodes.utils;

import android.view.View;

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
