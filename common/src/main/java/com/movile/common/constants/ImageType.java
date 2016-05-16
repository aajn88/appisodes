package com.movile.common.constants;

/**
 * Enum for different image types
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public enum ImageType {

    /** Fanart type **/
    FANART("fanart"),

    /** Poster type **/
    POSTER("poster"),

    /** Logo type **/
    LOGO("logo"),

    /** Clearart type **/
    CLEARART("clearart"),

    /** Banner type **/
    BANNER("banner"),

    /** Thumb type **/
    THUMB("thumb");

    /** Image type name **/
    private final String type;

    /**
     * Image type name
     *
     * @param type
     *         Image type
     */
    ImageType(String type) {
        this.type = type;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }
}
