package com.movile.common.constants;

/**
 * This enum represents the properties that can be loaded
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public enum Property {

    /** The client id **/
    CLIENT_ID("secure.client_id"),

    /** The client secret **/
    CLIENT_SECRET("secure.client_secret");

    /** Property name **/
    private final String property;

    /**
     * Constructor for property name
     *
     * @param property
     *         Property name
     */
    Property(String property) {
        this.property = property;
    }

    /**
     * @return the property
     */
    public String getProperty() {
        return property;
    }
}
