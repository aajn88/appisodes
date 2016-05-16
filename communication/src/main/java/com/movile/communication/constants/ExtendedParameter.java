package com.movile.communication.constants;

/**
 * This where extended parameters are stored
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public enum ExtendedParameter {

    /** Extended parameter for images **/
    IMAGES("images"),

    /** Extended parameter for full information **/
    FULL("full");

    /** Extended parameter **/
    private final String parameter;

    /**
     * Extended parameter constructor
     *
     * @param parameter
     *         Extended parameter name
     */
    ExtendedParameter(String parameter) {
        this.parameter = parameter;
    }

    /**
     * @return the parameter
     */
    public String getParameter() {
        return parameter;
    }

    @Override
    public String toString() {
        return parameter;
    }
}
