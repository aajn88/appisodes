package com.movile.common.model.authentication;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * This is the access token information, where the access token and other properties, such as
 * refresh token, can be found
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class AccessToken implements Serializable {

    /** Access token **/
    @SerializedName("access_token")
    private String token;

    /** Token type **/
    @SerializedName("token_type")
    private String type;

    /** Expires in (seconds) **/
    @SerializedName("expires_in")
    private String expiresIn;

    /** Refresh token **/
    @SerializedName("refresh_token")
    private String refreshToken;

    /** Token scope **/
    private String scope;

    /** Management date. When the data was registered **/
    private Date managementDate;

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @return token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the expiresIn
     */
    public String getExpiresIn() {
        return expiresIn;
    }

    /**
     * @return expiresIn the expiresIn to set
     */
    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    /**
     * @return the refreshToken
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * @return refreshToken the refreshToken to set
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    /**
     * @return the scope
     */
    public String getScope() {
        return scope;
    }

    /**
     * @return scope the scope to set
     */
    public void setScope(String scope) {
        this.scope = scope;
    }

    /**
     * @return the managementDate
     */
    public Date getManagementDate() {
        return managementDate;
    }

    /**
     * @return managementDate the managementDate to set
     */
    public void setManagementDate(Date managementDate) {
        this.managementDate = managementDate;
    }
}
