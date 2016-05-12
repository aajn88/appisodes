package com.movile.common.services;

import com.movile.common.model.authentication.AccessToken;
import com.movile.common.model.authentication.GeneratedCode;
import com.movile.common.model.authentication.User;

/**
 * This interface offers the session services such as log-in, log-out, and so on.
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public interface ISessionService {

    /**
     * This method performs the authentication process. A callback will be called when the
     * authentication process ends.
     *
     * @param callback
     *         Callback to be called when the authentication process ends
     *
     * @return The generated code to be shown to the user alongside the confirmation URL. Returns if
     * there was an error contacting the servers
     *
     * @throws IllegalArgumentException
     *         This will be thrown if the callback is null
     */
    GeneratedCode doAuthentication(IAuthenticationResultCallback callback) throws
            IllegalArgumentException;

    /**
     * This method stops the authentication process if the user cancels the process
     */
    void stopAuthentication();

    /**
     * This method returns the current active session (if exists). If there is no active session,
     * then null is returned
     *
     * @return The User of the current session. Returns null if there is no active session
     */
    User getCurrentSession();

    /**
     * This method returns the access token
     *
     * @return The access token
     */
    AccessToken getAccessToken();

    /**
     * This interface will be used for authentication results callback. The purpose of this
     * interface is communicate when the authentication process has ended and its result
     */
    interface IAuthenticationResultCallback {

        /**
         * This method will be called if the user has already accepted the app integration and has
         * granted permissions
         */
        void onSuccess();

        /**
         * This method will be called if expiration time has been reached and the user has not
         * already accepted the app integration
         */
        void onFailure();

        /**
         * This method will be called if the user denies access to the app integration
         */
        void onNotAuthorized();

    }

}
