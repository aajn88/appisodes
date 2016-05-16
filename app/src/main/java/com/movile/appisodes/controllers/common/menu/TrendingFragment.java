package com.movile.appisodes.controllers.common.menu;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.github.johnpersano.supertoasts.SuperToast;
import com.github.johnpersano.supertoasts.util.Style;
import com.google.inject.Inject;
import com.movile.appisodes.R;
import com.movile.appisodes.controllers.common.adapters.EndlessScrollListener;
import com.movile.appisodes.controllers.common.adapters.MediaAdapter;
import com.movile.appisodes.custom_views.progress_bars.ProgressWheel;
import com.movile.appisodes.utils.AnimationUtils;
import com.movile.appisodes.utils.ViewUtils;
import com.movile.business.services.api.IShowsService;
import com.movile.common.model.common.Pagination;
import com.movile.common.model.common.StandardMedia;
import com.movile.common.model.shows.Show;
import com.movile.common.model.shows.Trending;
import com.nhaarman.listviewanimations.appearance.AnimationAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;

/**
 * This is where trending medias are shown
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class TrendingFragment extends RoboFragment {

    /** Default items per page **/
    private static final int DEFAULT_LIMIT = 20;

    /** Large Screen tag **/
    @InjectResource(R.string.large_screen)
    private String mLargeScreenMsg;

    /** ProgressWheel **/
    @InjectView(R.id.progress_wheel)
    private ProgressWheel mProgressWheel;

    /** Media AbsListView **/
    @InjectView(R.id.medias_container)
    private AbsListView mMediasContainer;

    /** Media Adapter **/
    private MediaAdapter mAdapter;

    /** Indicates if data is being loading **/
    private boolean mIsLoading = false;

    /** Trending shows **/
    @Inject
    private IShowsService mShowsService;

    public TrendingFragment() {
        // Required empty public constructor
    }

    public static TrendingFragment newInstance() {
        TrendingFragment fragment = new TrendingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trending, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMediasContainer.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                if (!mIsLoading) {
                    new LoadTrendingAsyncTask().execute(page);
                }
                return mIsLoading;
            }
        });

        new LoadTrendingAsyncTask().execute();
    }

    /**
     * This method enables the progress wheel and its related views
     *
     * @param enable
     *         Enables or disables the PW
     */
    private void enableProgressWheel(boolean enable) {
        ViewUtils.enableProgressWheel(mProgressWheel, enable, mMediasContainer);
    }

    /**
     * This class loads the trending media asynchronously
     */
    private class LoadTrendingAsyncTask
            extends AsyncTask<Integer, Void, Pagination<Trending<Show>>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (mAdapter == null) {
                enableProgressWheel(true);
            }
            mIsLoading = true;
        }

        @Override
        protected Pagination<Trending<Show>> doInBackground(Integer... params) {
            int page = params.length > 0 ? params[0] : 1;
            int limit = params.length > 1 ? params[1] : DEFAULT_LIMIT;
            return mShowsService.getTrending(page, limit);
        }

        @Override
        protected void onPostExecute(Pagination<Trending<Show>> trendingPagination) {
            super.onPostExecute(trendingPagination);
            if (mAdapter == null) {
                enableProgressWheel(false);
            }
            mIsLoading = false;

            if (trendingPagination == null) {
                ViewUtils.makeToast(getActivity(), R.string.no_internet_connection,
                        SuperToast.Duration.EXTRA_LONG, Style.RED).show();
                return;
            }

            List<StandardMedia> medias = new ArrayList<>(trendingPagination.getItems().size());
            for (Trending trending : trendingPagination.getItems()) {
                medias.add(trending.getMedia());
            }

            if (mAdapter != null) {
                mAdapter.addAll(medias);
                return;
            }

            Object tag = mMediasContainer.getTag();
            boolean isLargeScreen = mLargeScreenMsg.equals(tag);
            mAdapter = new MediaAdapter(getActivity(), isLargeScreen, medias);
            AnimationAdapter animAdapter = AnimationUtils
                    .animateAdapter(new Random().nextInt(5), mAdapter);
            animAdapter.setAbsListView(mMediasContainer);
            mMediasContainer.setAdapter(animAdapter);
        }
    }
}
