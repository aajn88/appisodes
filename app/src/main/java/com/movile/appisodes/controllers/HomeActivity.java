package com.movile.appisodes.controllers;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.inject.Inject;
import com.movile.appisodes.R;
import com.movile.appisodes.controllers.common.BaseActivity;
import com.movile.business.services.api.IShowsService;

import roboguice.inject.ContentView;

/**
 * This is the Home activity where the basic view will be displayed, the drawer and much more
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
@ContentView(R.layout.activity_home)
public class HomeActivity extends BaseActivity {

    /** Shows service **/
    @Inject
    private IShowsService mShowsService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MenuItem home = mNavigationView.getMenu().getItem(0);
        home.setChecked(true);
        onNavigationItemSelected(home);
    }

    @Override
    public void onBackPressed() {
        if(!closeDrawer()) {
            finishAffinity();
        }
    }
}
