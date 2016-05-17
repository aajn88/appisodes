package com.movile.appisodes.controllers.common;

import android.view.View;

/**
 * This is the base activity for other activities that does not need drawer view
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class BaseNoDrawerActivity extends BaseActivity {

    @Override
    protected void initToolbar() {
        super.initToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initDrawer() {
        // Avoid drawer initialization
    }

}
