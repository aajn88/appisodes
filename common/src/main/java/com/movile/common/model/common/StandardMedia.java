package com.movile.common.model.common;

/**
 * This class represents a standard media such as movie, shows, episodes, etc.
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class StandardMedia {

    /** Media Ids **/
    protected Id ids;

    /** Show title **/
    protected String title;

    /** Show year **/
    protected Integer year;

    /** Media overview **/
    protected String overview;

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
}
