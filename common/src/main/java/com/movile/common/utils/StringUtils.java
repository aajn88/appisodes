package com.movile.common.utils;

/**
 * String Utils class
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public final class StringUtils {

    /** Empty String **/
    public static final String EMPTY_STRING = "";

    /** String null **/
    private static final String STRING_NULL = "null";

    /** Private constructor to avoid instanes **/
    private StringUtils() {}

    /**
     * This is the standard {@link String#format(String, Object...)} but if any of the objects is
     * null, then "null" is displayed
     *
     * @param string
     *         Target String
     * @param params
     *         String parameters
     *
     * @return Formatted String
     */
    public static String format(String string, Object... params) {
        for (int i = 0; i < params.length; i++) {
            if (params[i] == null) {
                params[i] = STRING_NULL;
            }
        }
        return String.format(string, params);
    }
}
