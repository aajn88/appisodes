package com.movile.common.model.authentication;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

import java.util.Date;

/**
 * This is the access token information, where the access token and other properties, such as
 * refresh token, can be found
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class AccessToken {

    /** Local Id **/
    @DatabaseField(generatedId = true)
    private Integer id;

    /** Access token **/
    @SerializedName("access_token")
    @DatabaseField(canBeNull = false)
    private String token;

    /** Token type **/
    @SerializedName("token_type")
    @DatabaseField(canBeNull = false)
    private String type;

    /** Expires in (seconds) **/
    @SerializedName("expires_in")
    @DatabaseField(canBeNull = false)
    private String expiresIn;

    /** Refresh token **/
    @SerializedName("refresh_token")
    @DatabaseField(canBeNull = false)
    private String refreshToken;

    /** Token scope **/
    @DatabaseField(canBeNull = false)
    private String scope;

    /** Management date. When the data was registered **/
    @DatabaseField(dataType = DataType.DATE_LONG)
    private Date managementDate;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

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
