package com.movile.common.model.common;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

/**
 * This class represents a standard media such as movie, shows, episodes, etc.
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class StandardMedia {

    /** Local Id **/
    public static final String LOCAL_ID = "local_id";

    /** Local Id **/
    @SerializedName(LOCAL_ID)
    @DatabaseField(id = true)
    protected Integer localId;

    /** Media Ids **/
    @DatabaseField(canBeNull = false, dataType = DataType.SERIALIZABLE)
    protected Id ids;

    /** Show title **/
    @DatabaseField(canBeNull = false)
    protected String title;

    /** Show year **/
    @DatabaseField(canBeNull = false)
    protected Integer year;

    /** Media overview **/
    @DatabaseField
    protected String overview;

    /** Images wrapper **/
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    protected ImagesWrapper images;

    /**
     * @return the localId
     */
    public Integer getLocalId() {
        return localId;
    }

    /**
     * @return localId the localId to set
     */
    public void setLocalId(Integer localId) {
        this.localId = localId;
    }

    /**
     * @return the ids
     */
    public Id getIds() {
        return ids;
    }

    /**
     * @return ids the ids to set
     */
    public void setIds(Id ids) {
        this.ids = ids;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * @return year the year to set
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * @return the overview
     */
    public String getOverview() {
        return overview;
    }

    /**
     * @return overview the overview to set
     */
    public void setOverview(String overview) {
        this.overview = overview;
    }

    /**
     * @return the images
     */
    public ImagesWrapper getImages() {
        return images;
    }

    /**
     * @return images the images to set
     */
    public void setImages(ImagesWrapper images) {
        this.images = images;
    }
}
