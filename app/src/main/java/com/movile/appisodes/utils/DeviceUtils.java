package com.movile.appisodes.utils;

import android.content.Context;
import android.content.res.Configuration;

/**
 * This Utils class has device common and standard methods such as the screen size
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public final class DeviceUtils {

    /** Private constructor to avoid insances **/
    private DeviceUtils(){}

    /**
     * Checks if the current device has a large screen or not
     *
     * @param context
     *         Application context
     *
     * @return True if the device has a large screen. False otherwise
     */
    public static boolean isLargeScreen(Context context) {
        return (getScreenSize(context)) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /**
     * This method returns the device screen size, e.g., {@link Configuration#SCREENLAYOUT_SIZE_LARGE},
     * {@link Configuration#SCREENLAYOUT_SIZE_NORMAL} or {@link Configuration#SCREENLAYOUT_SIZE_SMALL}
     *
     * @param context
     *         Current context
     *
     * @return This method returns the device screen size, e.g., {@link Configuration#SCREENLAYOUT_SIZE_LARGE},
     * {@link Configuration#SCREENLAYOUT_SIZE_NORMAL} or {@link Configuration#SCREENLAYOUT_SIZE_SMALL}
     */
    public static int getScreenSize(Context context) {
        return context.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;
    }

}
