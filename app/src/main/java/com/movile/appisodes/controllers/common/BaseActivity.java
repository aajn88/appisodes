package com.movile.appisodes.controllers.common;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.inject.Inject;
import com.movile.appisodes.R;
import com.movile.appisodes.controllers.common.menu.TrendingFragment;
import com.movile.appisodes.utils.ImageUtils;
import com.movile.common.model.authentication.User;
import com.movile.common.services.ISessionService;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import javax.annotation.Nullable;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.InjectView;

/**
 * This is the base activity where basic methods and behavior are implemented for each activity
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class BaseActivity extends RoboActionBarActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /** Fst action btn **/
    @InjectView(R.id.fst_action_mditv)
    @Nullable
    protected TextView mFstActionBtn;

    /** Snd action btn **/
    @InjectView(R.id.snd_action_mditv)
    @Nullable
    protected TextView mSndActionBtn;

    /** Drawer Layout **/
    @InjectView(R.id.drawer_layout)
    @Nullable
    protected DrawerLayout mDrawerLayout;

    /** Navigation View **/
    @InjectView(R.id.nav_view)
    @Nullable
    protected NavigationView mNavigationView;

    /** Toolbar **/
    @InjectView(R.id.toolbar)
    @Nullable
    protected Toolbar mToolbar;

    /** Toolbar **/
    @InjectView(R.id.toolbar_title_rtv)
    @Nullable
    protected TextView mToolbarTitleRtv;

    /** Drawer List **/
    protected ActionBarDrawerToggle mDrawerToggle;

    /** Session service **/
    @Inject
    private ISessionService mSessionService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageLoader imageLoader = ImageLoader.getInstance();
        if (!imageLoader.isInited()) {
            imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        }

        initToolbar();
        initDrawer();

    }

    /**
     * This method inits the toolbar
     */
    protected void initToolbar() {
        if (mToolbar == null) {
            return;
        }
        setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ViewCompat.setElevation(mToolbar, getResources().getDimension(R.dimen.toolbar_elevation));

        if (mFstActionBtn == null || mSndActionBtn == null) {
            return;
        }
        mFstActionBtn.setVisibility(View.GONE);
        mSndActionBtn.setVisibility(View.GONE);
    }

    /**
     * This method initializes the Drawer
     */
    protected void initDrawer() {
        if (mNavigationView == null || mDrawerLayout == null) {
            return;
        }

        mNavigationView.inflateMenu(R.menu.menu_drawer);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);

        loadUserProfile();
    }

    /**
     * This method loads the user profile (if applies)
     */
    private void loadUserProfile() {
        if(mNavigationView == null) {
            return;
        }

        View headerView = mNavigationView.getHeaderView(0);
        ImageView userPicIv = (ImageView) headerView.findViewById(R.id.user_pic_siv);
        TextView nameTv = (TextView) headerView.findViewById(R.id.user_name_tv);
        TextView usernameTv = (TextView) headerView.findViewById(R.id.username_tv);

        User user = mSessionService.getCurrentSession();
        if(user == null) {
            nameTv.setText(R.string.guest);
            usernameTv.setText(R.string.guest);
            return;
        }

        nameTv.setText(user.getName());
        usernameTv.setText(user.getUsername());
        ImageUtils.displayImage(userPicIv, user.getAvatarUrl(), null);

    }

    /**
     * This method opens the drawer
     */
    protected boolean openDrawer() {
        if (!isDrawerOpen() && mDrawerLayout != null) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return false;
    }

    /**
     * This method closes the drawer
     *
     * @return Returns True if the drawer has been closed. False if the drawer was already closed
     */
    protected boolean closeDrawer() {
        if (isDrawerOpen()) {
            mDrawerLayout.closeDrawers();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (!closeDrawer()) {
            super.onBackPressed();
        }
    }

    /**
     * This method checks if the drawer is open
     *
     * @return True if it is open. Otherwise returns false
     */
    protected boolean isDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(GravityCompat.START);
    }

    /**
     * This method returns the current activity
     *
     * @return The activity
     */
    @NonNull
    protected Activity getActivity() {
        return this;
    }

    /**
     * Called when an item in the navigation menu is selected.
     *
     * @param item
     *         The selected item
     *
     * @return true to display the item as the selected item
     */
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.home_item:
                fragment = TrendingFragment.newInstance();
                setTitle(R.string.trending);
                break;
        }

        if(fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_content_rl, fragment)
                    .addToBackStack(null).commit();
        }

        closeDrawer();
        return true;
    }
}
