package com.movile.common.model.shows;

import com.google.gson.annotations.SerializedName;
import com.movile.common.model.common.StandardMedia;

/**
 * This is the trending media where information about the current status of the media is stored
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class Trending<T extends StandardMedia> {

    /** Media field **/
    public static final String WATCHERS = "watchers";

    /** Media field **/
    public static final String MEDIA = "media";

    /** Shows watchers **/
    private Integer watchers;

    /** The media (movie, show, episodes, etc.) **/
    @SerializedName(MEDIA)
    private T media;

    /**
     * @return the watchers
     */
    public Integer getWatchers() {
        return watchers;
    }

    /**
     * @return watchers the watchers to set
     */
    public void setWatchers(Integer watchers) {
        this.watchers = watchers;
    }

    /**
     * @return the media
     */
    public T getMedia() {
        return media;
    }

    /**
     * @return media the media to set
     */
    public void setMedia(T media) {
        this.media = media;
    }
}
