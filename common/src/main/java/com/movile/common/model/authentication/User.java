package com.movile.common.model.authentication;


import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.movile.common.model.common.Image;

import java.util.Map;

/**
 * The trakt.tv user information
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class User {

    /** Avatar key **/
    public static final String AVATAR = "avatar";

    /** Local id **/
    @DatabaseField(generatedId = true)
    private Integer id;

    /** Username **/
    @DatabaseField(canBeNull = false)
    private String username;

    /** Name **/
    @DatabaseField(canBeNull = false)
    private String name;

    /** Images **/
    private Map<String, Image> images;

    /** Avatar image URL **/
    @DatabaseField(canBeNull = false)
    private String avatarUrl;

    /** Access token **/
    @DatabaseField(canBeNull = false, dataType = DataType.SERIALIZABLE)
    private AccessToken accessToken;

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
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the images
     */
    public Map<String, Image> getImages() {
        return images;
    }

    /**
     * @return images the images to set
     */
    public void setImages(Map<String, Image> images) {
        this.images = images;
    }

    /**
     * @return the avatarUrl
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * @return avatarUrl the avatarUrl to set
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * @return the accessToken
     */
    public AccessToken getAccessToken() {
        return accessToken;
    }

    /**
     * @return accessToken the accessToken to set
     */
    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }
}
