package com.movile.communication.clients.trakt.model;

import com.google.gson.annotations.SerializedName;

/**
 * This is the request to generate a code for this device
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class GenerateCodeRequest {

    /** The client Id **/
    @SerializedName("client_id")
    private String clientId;

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
}
