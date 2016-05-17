package com.movile.appisodes.controllers.shows;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.github.johnpersano.supertoasts.SuperToast;
import com.github.johnpersano.supertoasts.util.Style;
import com.google.inject.Inject;
import com.movile.appisodes.R;
import com.movile.appisodes.controllers.common.BaseNoDrawerActivity;
import com.movile.appisodes.custom_views.progress_bars.ProgressWheel;
import com.movile.appisodes.utils.ImageUtils;
import com.movile.appisodes.utils.ViewUtils;
import com.movile.business.services.api.IShowsService;
import com.movile.common.constants.ImageType;
import com.movile.common.model.shows.Season;
import com.movile.common.model.shows.Show;

import java.util.List;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * This activity shows the Show detail with its episodes and other information
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
@ContentView(R.layout.activity_show_detail)
public class ShowDetailActivity extends BaseNoDrawerActivity
        implements View.OnClickListener, SeasonsAdapter.OnItemClickListener {

    /** Tag for logs **/
    public static final String TAG_LOG = ShowDetailActivity.class.getName();

    /** Show Id key **/
    public static final String SHOW_ID = "SHOW_ID";

    /** Progress Wheel **/
    @InjectView(R.id.progress_wheel)
    private ProgressWheel mProgressWheel;

    /** Seasons RecyclerView **/
    @InjectView(R.id.seasons_rv)
    private RecyclerView mSeasonsRv;

    /** ImageView parallax **/
    @InjectView(R.id.media_parallax)
    private ImageView mMediaParallaxIv;

    /** Share FAB **/
    @InjectView(R.id.share_fab)
    private FloatingActionButton mShareFab;

    /** Seasons Adapter **/
    private SeasonsAdapter mAdapter;

    /** Show Id **/
    @Inject
    private IShowsService mShowsService;

    /** Current Show **/
    private Show mShow;

    /** Seasons list **/
    private List<Season> mSeasons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int showId = getIntent().getIntExtra(SHOW_ID, -1);
        if (showId == -1) {
            Log.w(TAG_LOG,
                    "Show id has not been passed as a parameter. It is mandatory for this activity");
            finish();
            return;
        }

        mShow = mShowsService.getShow(showId);

        mSeasonsRv.setLayoutManager(new LinearLayoutManager(this));
        setTitle(mShow.getTitle());
        String bannerUrl = mShow.getImages().getImage(ImageType.FANART).getFull();
        ImageUtils.displayImage(mMediaParallaxIv, bannerUrl, null);

        mShareFab.setOnClickListener(this);

        new LoadShowAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, showId);
    }

    /**
     * This method enables the progress wheel and its related views
     *
     * @param enable
     *         Enables or disables the PW
     */
    private void enableProgressWheel(boolean enable) {
        ViewUtils.enableProgressWheel(mProgressWheel, enable);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v
     *         The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share_fab:
                ViewUtils.makeToast(this, R.string.share_medoa, SuperToast.Duration.EXTRA_LONG,
                        Style.BLUE).show();
                break;
        }
    }

    /**
     * This method is called when an item is clicked
     *
     * @param view
     *         Clicked view
     * @param position
     *         Item position
     */
    @Override
    public void onItemClick(View view, int position) {
        Season season = mAdapter.getItem(position);
    }

    /**
     * This class loads the show asynchronously
     */
    private class LoadShowAsyncTask extends AsyncTask<Integer, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            enableProgressWheel(true);
        }

        @Override
        protected Void doInBackground(Integer... params) {
            mSeasons = mShowsService.getSeasons(mShow.getLocalId());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            enableProgressWheel(false);

            if (mSeasons == null) {
                ViewUtils.makeToast(getActivity(), R.string.no_internet_connection,
                        SuperToast.Duration.EXTRA_LONG, Style.RED).show();
                return;
            }

            mAdapter = new SeasonsAdapter(getActivity(), mSeasons);
            mAdapter.setOnItemClickListener(ShowDetailActivity.this);
            mSeasonsRv.setAdapter(mAdapter);
        }

    }
}
