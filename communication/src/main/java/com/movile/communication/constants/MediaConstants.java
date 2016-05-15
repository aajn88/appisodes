package com.movile.communication.constants;

/**
 * The media constants and their name in strings
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public enum MediaConstants {

    /** Show constant **/
    SHOW("show"),

    /** Movie constant **/
    MOVIE("movie");

    /** The media name **/
    private final String mName;

    /**
     * Constructor for media constants
     *
     * @param name
     *         Name of the media
     */
    MediaConstants(String name) {
        this.mName = name;
    }

    /**
     * @return the mName
     */
    public String getName() {
        return mName;
    }
}
