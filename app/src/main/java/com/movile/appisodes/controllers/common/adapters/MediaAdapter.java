package com.movile.appisodes.controllers.common.adapters;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.inject.Inject;
import com.movile.appisodes.R;
import com.movile.appisodes.utils.ImageUtils;
import com.movile.business.services.api.IShowsService;
import com.movile.common.constants.ImageType;
import com.movile.common.model.common.StandardMedia;
import com.movile.common.model.shows.Show;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import roboguice.RoboGuice;
import roboguice.inject.RoboInjector;

/**
 * This adapter shows the media depending on the device screen. A medium to small screen, they are
 * shown in a listview, in large screens they are shown in a gridview
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class MediaAdapter extends ArrayAdapter<StandardMedia> implements View.OnClickListener {

    /** Tag for Logs **/
    private static final String TAG_LOG = MediaAdapter.class.getName();

    /** The original dataset **/
    private final List<StandardMedia> mMedias;

    /** Layout Inflater **/
    private LayoutInflater mInflater;

    /** Shows service **/
    @Inject
    private IShowsService mShowsService;

    /** Large Screen **/
    private boolean mIsLargeScreen;

    /** Selected positions **/
    private Set<Integer> mSelectedPositions = new HashSet<>();

    /**
     * Constructor
     *
     * @param context
     *         The current context.
     * @param isLargeScreen
     *         Indicates if is a large screen view
     * @param medias
     *         The objects to represent in the ListView.
     */
    public MediaAdapter(Context context, boolean isLargeScreen, StandardMedia[] medias) {
        super(context, R.layout.media_google_card, medias);
        mMedias = new ArrayList<>(medias.length);
        Collections.addAll(mMedias, medias);
        init(isLargeScreen);
    }

    /**
     * Constructor
     *
     * @param context
     *         The current context.
     * @param isLargeScreen
     *         Indicates if is a large screen view
     * @param apps
     *         The objects to represent in the ListView.
     */
    public MediaAdapter(Context context, boolean isLargeScreen, List<StandardMedia> apps) {
        super(context, R.layout.media_google_card, apps);
        mMedias = new ArrayList<>(apps);
        init(isLargeScreen);
    }

    @Override
    public void addAll(Collection<? extends StandardMedia> collection) {
        super.addAll(collection);
        mMedias.addAll(collection);
    }

    /**
     * Inits the basic fields
     *
     * @param isLargeScreen
     *         Indicates if is a large screen view
     */
    private void init(boolean isLargeScreen) {
        RoboInjector injector = RoboGuice.getInjector(getContext());
        injector.injectMembersWithoutViews(this);
        mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mIsLargeScreen = isLargeScreen;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            int layout = mIsLargeScreen ? R.layout.media_google_card : R.layout.media_list_item;
            convertView = mInflater.inflate(layout, parent, false);
            holder = new Holder();

            holder.image = (ImageView) convertView.findViewById(R.id.media_image_siv);
            holder.title = (TextView) convertView.findViewById(R.id.media_title_rtv);
            holder.caption = (TextView) convertView.findViewById(R.id.media_caption_rtv);
            holder.favorite = (TextView) convertView.findViewById(R.id.favorite_icon_tv);

            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        StandardMedia media = getItem(position);

        holder.title.setText(media.getTitle());
        holder.image.setImageResource(R.drawable.serie_thumbnail_placeholder);
        holder.caption.setText(null);
        if (holder.favorite != null) {
            checkStar(mSelectedPositions.contains(position), holder.favorite);
            holder.favorite.setOnClickListener(this);
            holder.favorite.setTag(position);
        }
        new AdditionalInformationAsyncTask(holder, parent, position)
                .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, media.getLocalId());

        return convertView;
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
            case R.id.favorite_icon_tv:
                int position = (int) v.getTag();
                boolean isChecked = mSelectedPositions.contains(position);
                if (!isChecked) {
                    mSelectedPositions.add(position);
                } else {
                    mSelectedPositions.remove(position);
                }
                isChecked = !isChecked;
                TextView icon = (TextView) v;
                checkStar(isChecked, icon);
                break;
        }
    }

    /**
     * This method checks the star given the icon
     *
     * @param check
     *         Check or not?
     * @param icon
     *         Icon to be checked
     */
    private void checkStar(boolean check, TextView icon) {
        icon.setText(check ? R.string.material_icon_star : R.string.material_icon_star_outline);
        int colorId = check ? R.color.material_yellow_500 : R.color.black;
        int colorRes;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            colorRes = getContext().getColor(colorId);
        } else {
            colorRes = getContext().getResources().getColor(colorId);
        }
        icon.setTextColor(colorRes);
    }

    /**
     * This is the Holder class for HolderView pattern
     */
    private class Holder {
        /** Media image **/
        ImageView image;

        /** Media title **/
        TextView title;

        /** Media caption **/
        TextView caption;

        /** Favorite media **/
        TextView favorite;
    }

    private class AdditionalInformationAsyncTask extends AsyncTask<Integer, Void, Show> {

        /** View Holder **/
        private final Holder mHolder;

        /** ViewGroup **/
        private final AbsListView mParent;

        /** Item position **/
        private final int mPosition;

        /**
         * AsyncTask constructor
         *
         * @param mHolder
         *         The view holder
         */
        AdditionalInformationAsyncTask(Holder mHolder, ViewGroup parent, int position) {
            this.mHolder = mHolder;
            this.mParent = (AbsListView) parent;
            this.mPosition = position;
        }

        @Override
        protected Show doInBackground(Integer... params) {
            return mShowsService.getShow(params[0]);
        }

        @Override
        protected void onPostExecute(Show show) {
            super.onPostExecute(show);
            if (show == null || mPosition < mParent.getFirstVisiblePosition() ||
                    mParent.getLastVisiblePosition() < mPosition) {
                return;
            }

            ImageUtils.displayImage(mHolder.image,
                    show.getImages().getImage(ImageType.THUMB).getFull(), null);
            mHolder.caption.setText(show.getOverview());
        }
    }
}
