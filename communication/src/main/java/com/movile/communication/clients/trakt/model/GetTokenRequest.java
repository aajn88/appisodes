package com.movile.communication.clients.trakt.model;

import com.google.gson.annotations.SerializedName;

/**
 * This is the request for get token request
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class GetTokenRequest {

    /** Device code **/
    @SerializedName("code")
    private String deviceCode;

    /** The client id **/
    @SerializedName("client_id")
    private String clientId;

    /** The client secret **/
    @SerializedName("client_secret")
    private String clientSecret;

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
     * @return the clientId
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * @return clientId the clientId to set
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * @return the clientSecret
     */
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * @return clientSecret the clientSecret to set
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
