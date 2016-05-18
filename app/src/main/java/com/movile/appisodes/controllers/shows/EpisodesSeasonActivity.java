package com.movile.appisodes.controllers.shows;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.johnpersano.supertoasts.SuperToast;
import com.github.johnpersano.supertoasts.util.Style;
import com.google.inject.Inject;
import com.movile.appisodes.R;
import com.movile.appisodes.controllers.common.BaseNoDrawerActivity;
import com.movile.appisodes.custom_views.progress_bars.ProgressWheel;
import com.movile.appisodes.custom_views.shaped_images.ShapeImageView;
import com.movile.appisodes.utils.ImageUtils;
import com.movile.appisodes.utils.ViewUtils;
import com.movile.business.services.api.IShowsService;
import com.movile.common.constants.ImageType;
import com.movile.common.model.shows.Episode;
import com.movile.common.model.shows.Season;
import com.movile.common.model.shows.Show;

import java.text.DecimalFormat;
import java.util.List;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * This is the activity where the episodes of a season is shown
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
@ContentView(R.layout.activity_episodes_season)
public class EpisodesSeasonActivity extends BaseNoDrawerActivity
        implements AppBarLayout.OnOffsetChangedListener {

    /** Selected season **/
    public static final String SELECTED_SEASON = "SELECTED_SEASON";

    /** Percentage to animate avatar **/
    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION = 200;

    private boolean mIsTheTitleVisible = false;
    private boolean mIsTheTitleContainerVisible = true;

    /** AppBar **/
    @InjectView(R.id.app_bar)
    private AppBarLayout mAppBar;

    /** Shape Image View **/
    @InjectView(R.id.show_pic_siv)
    private ShapeImageView mShowPicSiv;

    /** Season ImageView **/
    @InjectView(R.id.season_iv)
    private ImageView mSeasonIv;

    /** Progress Wheel **/
    @InjectView(R.id.progress_wheel)
    private ProgressWheel mProgressWheel;

    /** Small episode title **/
    @InjectView(R.id.small_episode_title_tv)
    private TextView mSmallEpisodeTitleTv;

    /** Big episode title **/
    @InjectView(R.id.big_season_title_rtv)
    private TextView mBigEpisodeTitleTv;

    /** Show title **/
    @InjectView(R.id.show_title_rtv)
    private TextView mShowTitleTv;

    /** Season rating RobotoTextView **/
    @InjectView(R.id.season_rating_rtv)
    private TextView mSeasonRatingRtv;

    /** Title container LinearLayout **/
    @InjectView(R.id.title_container_ll)
    private ViewGroup mTitleContainerLl;

    /** Episodes Recycler View **/
    @InjectView(R.id.episodes_rv)
    private RecyclerView mEpisodesRv;

    /** Episodes adapter **/
    private EpisodesAdapter mAdapter;

    /** Shows Services **/
    @Inject
    private IShowsService mShowsService;

    /** Selected show **/
    private Show mShow;

    /** Selected season **/
    private Season mSeason;

    /**
     * Alpha animation for a view
     *
     * @param v
     *         view to apply alpha
     * @param duration
     *         Animation duration
     * @param visibility
     *         Final visibility
     */
    public static void startAlphaAnimation(View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE) ? new AlphaAnimation(0f, 1f) :
                new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSeason = (Season) getIntent().getSerializableExtra(SELECTED_SEASON);
        mShow = mShowsService.getShow(mSeason.getShowId());

        ImageUtils.displayImage(mSeasonIv, mSeason.getImages().getImage(ImageType.POSTER).getFull(),
                null);
        ImageUtils.displayImage(mShowPicSiv, mShow.getImages().getImage(ImageType.THUMB).getFull(),
                null);

        mAppBar.addOnOffsetChangedListener(this);

        String title = String.format(getString(R.string.season_number), mSeason.getNumber());
        mSmallEpisodeTitleTv.setText(title);
        mBigEpisodeTitleTv.setText(title);
        mShowTitleTv.setText(mShow.getTitle());
        mSeasonRatingRtv.setText(new DecimalFormat("#.#").format(mSeason.getRating()));

        startAlphaAnimation(mSmallEpisodeTitleTv, 0, View.INVISIBLE);

        mEpisodesRv.setLayoutManager(new LinearLayoutManager(this));

        new LoadEpisodesAsyncTask().execute();
    }

    @Override
    protected void initToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (mFstActionBtn == null || mSndActionBtn == null) {
            return;
        }
        mFstActionBtn.setVisibility(View.GONE);
        mSndActionBtn.setVisibility(View.GONE);
    }

    /**
     * Called when the {@link AppBarLayout}'s layout offset has been changed. This allows child
     * views to implement custom behavior based on the offset (for instance pinning a view at a
     * certain y value).
     *
     * @param appBarLayout
     *         the {@link AppBarLayout} which offset has changed
     * @param offset
     *         the vertical offset for the parent {@link AppBarLayout}, in px
     */
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;

        handleAlphaOnTitle(percentage);
        handleToolbarTitleVisibility(percentage);
    }

    private void handleToolbarTitleVisibility(float percentage) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {

            if (!mIsTheTitleVisible) {
                startAlphaAnimation(mSmallEpisodeTitleTv, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }

        } else {

            if (mIsTheTitleVisible) {
                startAlphaAnimation(mSmallEpisodeTitleTv, ALPHA_ANIMATIONS_DURATION,
                        View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }

    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if (mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainerLl, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleContainerVisible = false;
            }

        } else {

            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainerLl, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleContainerVisible = true;
            }
        }
    }

    /**
     * This async task loads the season episodes asynchronously
     */
    private class LoadEpisodesAsyncTask extends AsyncTask<Void, Void, List<Episode>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ViewUtils.enableProgressWheel(mProgressWheel, true, mEpisodesRv);
        }

        @Override
        protected List<Episode> doInBackground(Void... params) {
            return mShowsService.getEpisodes(mSeason.getShowId(), mSeason.getNumber());
        }

        @Override
        protected void onPostExecute(List<Episode> episodes) {
            super.onPostExecute(episodes);
            ViewUtils.enableProgressWheel(mProgressWheel, false, mEpisodesRv);

            if (episodes == null) {
                ViewUtils.makeToast(getActivity(), R.string.no_internet_connection,
                        SuperToast.Duration.EXTRA_LONG, Style.RED).show();
                return;
            }

            mAdapter = new EpisodesAdapter(getActivity(), episodes);
            mEpisodesRv.setAdapter(mAdapter);
        }
    }
}
