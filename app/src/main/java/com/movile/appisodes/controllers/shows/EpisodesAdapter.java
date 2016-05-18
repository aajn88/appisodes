package com.movile.appisodes.controllers.shows;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.movile.appisodes.R;
import com.movile.common.model.shows.Episode;

import java.util.List;

/**
 * This is the Episodes Adapter that creates the view for each item in the recycler view
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class EpisodesAdapter extends RecyclerView.Adapter {

    /** Current context **/
    private final Context mContext;

    /** The LayoutInflater **/
    private LayoutInflater mInflater;

    /** The original episodes **/
    private List<Episode> mEpisodes;

    /** Last position shown **/
    private int mLastPosition = -1;

    /**
     * Constructor for the adapter
     *
     * @param context
     *         Application context
     * @param episodes
     *         Episodes to be shown
     */
    public EpisodesAdapter(Context context, List<Episode> episodes) {
        this.mContext = context;
        this.mEpisodes = episodes;
        this.mInflater = LayoutInflater.from(context);
    }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent an
     * item.
     * <p/>
     * This new ViewHolder should be constructed with a new View that can represent the items of the
     * given type. You can either create a new View manually or inflate it from an XML layout file.
     * <p/>
     * The new ViewHolder will be used to display items of the adapter using {@link
     * #onBindViewHolder(ViewHolder, int, List)}. Since it will be re-used to display different
     * items in the data set, it is a good idea to cache references to sub views of the View to
     * avoid unnecessary {@link android.view.View#findViewById(int)} calls.
     *
     * @param parent
     *         The ViewGroup into which the new View will be added after it is bound to an adapter
     *         position.
     * @param viewType
     *         The view type of the new View.
     *
     * @return A new ViewHolder that holds a View of the given view type.
     *
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(ViewHolder, int)
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.episode_list_item, parent, false);
        return new EpisodeHolder(v);
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p/>
     * Note that unlike {@link ListView}, RecyclerView will not call this method again if the
     * position of the item changes in the data set unless the item itself is invalidated or the new
     * position cannot be determined. For this reason, you should only use the <code>position</code>
     * parameter while acquiring the related data item inside this method and should not keep a copy
     * of it. If you need the position of an item later on (e.g. in a click listener), use {@link
     * ViewHolder#getAdapterPosition()} which will have the updated adapter position.
     * <p/>
     * Override {@link #onBindViewHolder(ViewHolder, int, List)} instead if Adapter can handle
     * effcient partial bind.
     *
     * @param viewHolder
     *         The ViewHolder which should be updated to represent the contents of the item at the
     *         given position in the data set.
     * @param position
     *         The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Episode episode = mEpisodes.get(position);

        EpisodeHolder holder = (EpisodeHolder) viewHolder;
        holder.title.setText(episode.getTitle());
        holder.number.setText(Integer.toString(episode.getEpisode()));

        setAnimation(holder.container, position);
    }

    /**
     * Here is the key method to apply the animation
     */
    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > mLastPosition) {
            Animation animation = AnimationUtils
                    .loadAnimation(mContext, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            mLastPosition = position;
        }
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        EpisodeHolder episodeHolder = (EpisodeHolder) holder;
        episodeHolder.container.clearAnimation();
    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return mEpisodes.size();
    }

    /**
     * This is the ViewHolder for episodes
     */
    private class EpisodeHolder extends RecyclerView.ViewHolder {

        /** Episode Container **/
        RelativeLayout container;

        /** Episode number **/
        TextView number;

        /** Episode Title **/
        TextView title;

        /** Episode icon **/
        TextView mediaIcon;

        /**
         * Constructor
         *
         * @param itemView
         *         Item view
         */
        public EpisodeHolder(View itemView) {
            super(itemView);

            container = (RelativeLayout) itemView.findViewById(R.id.episode_container_rl);
            number = (TextView) itemView.findViewById(R.id.episode_number_rtv);
            title = (TextView) itemView.findViewById(R.id.episode_title_rtv);
            mediaIcon = (TextView) itemView.findViewById(R.id.favorite_icon_tv);
        }
    }
}
