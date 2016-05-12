package com.movile.appisodes.controllers.common;

import android.app.Activity;
import android.support.annotation.NonNull;

import roboguice.activity.RoboActionBarActivity;

/**
 * This is the base activity where basic methods and behavior are implemented for each activity
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class BaseActivity extends RoboActionBarActivity {

    /**
     * This method returns the current activity
     *
     * @return The activity
     */
    @NonNull
    protected Activity getActivity() {
        return this;
    }

}
