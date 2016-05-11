package com.movile.common.model.authentication;

import com.google.gson.annotations.SerializedName;

/**
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class GeneratedCode {

    /** The device code **/
    @SerializedName("device_code")
    private String deviceCode;

    /** The user code **/
    @SerializedName("user_code")
    private String userCode;

    /** The verification URL to be accesed by the User **/
    @SerializedName("verification_url")
    private String verificationUrl;

    /** Expiration time (in seconds) **/
    @SerializedName("expires_in")
    private Integer expiresIn;

    /** Interval time for poll (in seconds) **/
    @SerializedName("interval")
    private Integer interval;

    /**
     * @return the deviceCode
     */
    public String getDeviceCode() {
        return deviceCode;
    }

    /**
     * @return deviceCode the deviceCode to set
     */
    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    /**
     * @return the userCode
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * @return userCode the userCode to set
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    /**
     * @return the verificationUrl
     */
    public String getVerificationUrl() {
        return verificationUrl;
    }

    /**
     * @return verificationUrl the verificationUrl to set
     */
    public void setVerificationUrl(String verificationUrl) {
        this.verificationUrl = verificationUrl;
    }

    /**
     * @return the expiresIn
     */
    public Integer getExpiresIn() {
        return expiresIn;
    }

    /**
     * @return expiresIn the expiresIn to set
     */
    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    /**
     * @return the interval
     */
    public Integer getInterval() {
        return interval;
    }

    /**
     * @return interval the interval to set
     */
    public void setInterval(Integer interval) {
        this.interval = interval;
    }
}
