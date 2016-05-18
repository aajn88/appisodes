package com.movile.common.model.shows;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.movile.common.model.common.StandardMedia;

/**
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class Episode extends StandardMedia {

    /** Show Id column **/
    public static final String SHOW_ID_COLUMN = "show_id";

    /** Season Number column **/
    public static final String SEASON_NUMBER_COLUMN = "season";

    /** Season number **/
    @DatabaseField(canBeNull = false, columnName = SHOW_ID_COLUMN)
    private Integer showId;

    /** Season number **/
    @DatabaseField(canBeNull = false, columnName = SEASON_NUMBER_COLUMN)
    private Integer season;

    /** Episode number **/
    @DatabaseField(canBeNull = false)
    @SerializedName("number")
    private Integer episode;

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
     * @return the season
     */
    public Integer getSeason() {
        return season;
    }

    /**
     * @return season the season to set
     */
    public void setSeason(Integer season) {
        this.season = season;
    }

    /**
     * @return the episode
     */
    public Integer getEpisode() {
        return episode;
    }

    /**
     * @return episode the episode to set
     */
    public void setEpisode(Integer episode) {
        this.episode = episode;
    }

}
