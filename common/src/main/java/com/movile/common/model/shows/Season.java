package com.movile.common.model.shows;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.movile.common.model.common.StandardMedia;

/**
 * This is the season model of a show
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class Season extends StandardMedia {

    /** Show Id column name **/
    public static final String SHOW_ID_COLUMN = "show_id";

    /** Show Id **/
    @DatabaseField(canBeNull = false, columnName = SHOW_ID_COLUMN)
    private Integer showId;

    /** Season number **/
    @DatabaseField(canBeNull = false)
    private Integer number;

    /** Raiting **/
    @DatabaseField(canBeNull = false)
    private Float rating;

    /** Episodes count **/
    @DatabaseField(canBeNull = false)
    @SerializedName("episode_count")
    private Integer episodesCount;

    /**
     * @return the showId
     */
    public Integer getShowId() {
        return showId;
    }

    /**
     * @return showId the showId to set
     */
    public void setShowId(Integer showId) {
        this.showId = showId;
    }

    /**
     * @return the number
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * @return number the number to set
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * @return the rating
     */
    public Float getRating() {
        return rating;
    }

    /**
     * @return rating the rating to set
     */
    public void setRating(Float rating) {
        this.rating = rating;
    }

    /**
     * @return the episodesCount
     */
    public Integer getEpisodesCount() {
        return episodesCount;
    }

    /**
     * @return episodesCount the episodesCount to set
     */
    public void setEpisodesCount(Integer episodesCount) {
        this.episodesCount = episodesCount;
    }
}
