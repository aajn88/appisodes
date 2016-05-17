package com.movile.appisodes.controllers.shows;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.movile.appisodes.R;
import com.movile.appisodes.utils.ImageUtils;
import com.movile.common.constants.ImageType;
import com.movile.common.model.shows.Season;
import com.movile.common.utils.StringUtils;

import java.util.List;

/**
 * Seasons Adapter for the RecyclerView
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class SeasonsAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    /** Current context **/
    private final Context mContext;

    /** Seasons data **/
    private final List<Season> mSeasons;

    /** Season msg **/
    private final String mSeasonMsg;

    /** The LayoutInflater **/
    private LayoutInflater mInflater;

    /** Last position shown **/
    private int mLastPosition = -1;

    /** OnClickItemListener **/
    private OnItemClickListener mListener;

    /**
     * This is the Seasons adapter constructor
     *
     * @param context
     *         Application context
     * @param seasons
     *         Seasons data
     */
    public SeasonsAdapter(Context context, List<Season> seasons) {
        this.mContext = context;
        this.mSeasons = seasons;
        this.mInflater = LayoutInflater.from(context);
        mSeasonMsg = context.getString(R.string.season_number);
    }

    /**
     * Called when RecyclerView needs a new {@link ViewHolder} of the given type to represent an
     * item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items of the
     * given type. You can either create a new View manually or inflate it from an XML layout file.
     * <p>
     * The new ViewHolder will be used to display items of the adapter using {@link
     * #onBindViewHolder(ViewHolder, int, List)}. Since it will be re-used to display different
     * items in the data set, it is a good idea to cache references to sub views of the View to
     * avoid unnecessary {@link View#findViewById(int)} calls.
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
        View v = mInflater.inflate(R.layout.media_list_item, parent, false);
        return new SeasonHolder(v);
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link ViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method again if the
     * position of the item changes in the data set unless the item itself is invalidated or the new
     * position cannot be determined. For this reason, you should only use the <code>position</code>
     * parameter while acquiring the related data item inside this method and should not keep a copy
     * of it. If you need the position of an item later on (e.g. in a click listener), use {@link
     * ViewHolder#getAdapterPosition()} which will have the updated adapter position.
     * <p>
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
        Season season = mSeasons.get(position);
        SeasonHolder holder = (SeasonHolder) viewHolder;
        String imageUrl = season.getImages().getImage(ImageType.THUMB).getFull();
        ImageUtils.displayImage(holder.mediaImage, imageUrl, null);
        holder.mediaTitle.setText(StringUtils.format(mSeasonMsg, season.getNumber() + 1));
        holder.mediaCaption.setText(season.getOverview());

        holder.mediaContainer.setTag(position);
        holder.mediaContainer.setOnClickListener(this);

        setAnimation(holder.mediaContainer, position);
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
        SeasonHolder seasonHolder = (SeasonHolder) holder;
        seasonHolder.mediaContainer.clearAnimation();
    }

    /**
     * Returns the total number of items in the data set hold by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return mSeasons.size();
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v
     *         The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        Object obj = v.getTag();
        if (mListener == null || !(obj instanceof Integer)) {
            return;
        }

        mListener.onItemClick(v, (Integer) obj);
    }

    /**
     * This method gets an item given its position
     *
     * @param position
     *         Item position
     *
     * @return The season
     */
    public Season getItem(int position) {
        return mSeasons.get(position);
    }

    /**
     * Sets the OnItemClickListener
     *
     * @param listener
     *         Listener to be set
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    /**
     * Interface to be implemented to get items click events
     */
    public interface OnItemClickListener {
        /**
         * This method is called when an item is clicked
         *
         * @param view
         *         Clicked view
         * @param position
         *         Item position
         */
        void onItemClick(View view, int position);
    }

    /**
     * This is the ViewHolder for seasons
     */
    private class SeasonHolder extends RecyclerView.ViewHolder {

        /** Media Container **/
        RelativeLayout mediaContainer;

        /** Media Image **/
        ImageView mediaImage;

        /** Media Title **/
        TextView mediaTitle;

        /** Media Caption **/
        TextView mediaCaption;

        /** Media icon **/
        TextView mediaIcon;

        /**
         * Constructor
         *
         * @param itemView
         *         Item view
         */
        public SeasonHolder(View itemView) {
            super(itemView);

            mediaContainer = (RelativeLayout) itemView.findViewById(R.id.media_container_rl);
            mediaImage = (ImageView) itemView.findViewById(R.id.media_image_siv);
            mediaTitle = (TextView) itemView.findViewById(R.id.media_title_rtv);
            mediaCaption = (TextView) itemView.findViewById(R.id.media_caption_rtv);
            mediaIcon = (TextView) itemView.findViewById(R.id.favorite_icon_tv);
        }
    }
}
