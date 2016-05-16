package com.movile.common.model.common;

import java.io.Serializable;

/**
 * Image representation for trakt.tv images
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class Image implements Serializable {

    /** Full image **/
    private String full;

    /** Medium image **/
    private String medium;

    /** humb image **/
    private String thumb;

    /**
     * @return the full
     */
    public String getFull() {
        return full;
    }

    /**
     * @return full the full to set
     */
    public void setFull(String full) {
        this.full = full;
    }

    /**
     * @return the medium
     */
    public String getMedium() {
        return medium;
    }

    /**
     * @return medium the medium to set
     */
    public void setMedium(String medium) {
        this.medium = medium;
    }

    /**
     * @return the thumb
     */
    public String getThumb() {
        return thumb;
    }

    /**
     * @return thumb the thumb to set
     */
    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
