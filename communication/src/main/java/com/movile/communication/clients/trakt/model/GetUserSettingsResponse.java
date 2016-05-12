package com.movile.communication.clients.trakt.model;

import com.movile.common.model.authentication.User;

/**
 * Expected response for User settings
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class GetUserSettingsResponse {

    /** User info **/
    private User user;

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @return user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
}
