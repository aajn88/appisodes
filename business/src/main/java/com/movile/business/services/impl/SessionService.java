package com.movile.business.services.impl;

import android.os.Handler;
import android.os.HandlerThread;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.movile.common.constants.Property;
import com.movile.common.helpers.PropertiesAdminHelper;
import com.movile.common.model.authentication.AccessToken;
import com.movile.common.model.authentication.GeneratedCode;
import com.movile.common.services.ISessionService;
import com.movile.communication.clients.trakt.api.IAuthentication;
import com.movile.communication.clients.trakt.api.ITraktClient;
import com.movile.communication.clients.trakt.model.GenerateCodeRequest;
import com.movile.communication.clients.trakt.model.GetTokenRequest;
import com.movile.persistence.managers.api.IAccessTokenManager;

import java.util.Calendar;
import java.util.List;

import retrofit2.Response;

/**
 * This is the Session services implementation
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
@Singleton
public class SessionService implements ISessionService {

    /** Token success **/
    private static final int TOKEN_SUCCESS = 200;

    /** Token already used **/
    private static final int TOKEN_ALREADY_USED = 409;

    /** Token not found **/
    private static final int TOKEN_NOT_FOUND = 404;

    /** Time expired **/
    private static final int TOKEN_TIME_EXPIRED = 410;

    /** Token denied **/
    private static final int TOKEN_DENIED = 418;

    /** Trakt client **/
    @Inject
    private ITraktClient mTraktClient;

    /** Access Token Manager **/
    @Inject
    private IAccessTokenManager mAccessTokenManager;

    /** Properties admin **/
    @Inject
    private PropertiesAdminHelper mPropertiesHelper;

    /** This is the current session (in memory) **/
    private AccessToken mCurrentSession;

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
    @Override
    public synchronized GeneratedCode doAuthentication(
            final IAuthenticationResultCallback callback) throws IllegalArgumentException {
        final IAuthentication authenticationApi = mTraktClient.getApi(IAuthentication.class);
        GenerateCodeRequest request = new GenerateCodeRequest();

        request.setClientId(mPropertiesHelper.getProperty(Property.CLIENT_ID));

        Response<GeneratedCode> response = mTraktClient
                .execute(authenticationApi.generateCode(request));
        if (response == null || !response.isSuccessful()) {
            return null;
        }

        GeneratedCode generatedCode = response.body();

        final long intervalMillis = generatedCode.getInterval() * 1000;
        final long limitMillis = generatedCode.getExpiresIn() * 1000;
        final long start = Calendar.getInstance().getTimeInMillis();

        final GetTokenRequest getTokenRequest = new GetTokenRequest();
        getTokenRequest.setClientId(mPropertiesHelper.getProperty(Property.CLIENT_ID));
        getTokenRequest.setClientSecret(mPropertiesHelper.getProperty(Property.CLIENT_SECRET));
        getTokenRequest.setDeviceCode(generatedCode.getDeviceCode());

        HandlerThread thread = new HandlerThread(SessionService.class.getName());
        thread.start();
        final Handler pollHandler = new Handler(thread.getLooper());
        pollHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Calendar now = Calendar.getInstance();
                if (now.getTimeInMillis() - start > limitMillis) {
                    callback.onFailure();
                    return;
                }
                Response<AccessToken> tokenResponse = mTraktClient
                        .execute(authenticationApi.getToken(getTokenRequest));

                Integer code = tokenResponse == null ? -1 : tokenResponse.code();
                switch (code) {
                    case TOKEN_SUCCESS:
                    case TOKEN_ALREADY_USED:
                        AccessToken accessToken = tokenResponse.body();
                        persistSession(accessToken);
                        callback.onSuccess();
                        return;
                    case TOKEN_TIME_EXPIRED:
                    case TOKEN_NOT_FOUND:
                        callback.onFailure();
                        return;
                    case TOKEN_DENIED:
                        callback.onNotAuthorized();
                        return;
                }

                pollHandler.postDelayed(this, intervalMillis);
            }
        }, intervalMillis);

        return generatedCode;
    }

    /**
     * This method returns the current active session (if exists). If there is no active session,
     * then null is returned
     *
     * @return The AccessToken owned by the current session. Returns null if there is no active
     * session
     */
    @Override
    public AccessToken getCurrentSession() {
        if (mCurrentSession == null) {
            List<AccessToken> tokens = mAccessTokenManager.all();
            if (tokens != null && !tokens.isEmpty()) {
                mCurrentSession = tokens.get(0);
            }
        }
        return mCurrentSession;
    }

    /**
     * This method persists the access token and erase any other stored session
     *
     * @param accessToken
     *         Access token to be stored
     */
    private void persistSession(AccessToken accessToken) {
        mAccessTokenManager.deleteAll();
        mAccessTokenManager.createOrUpdate(accessToken);
        mCurrentSession = accessToken;
    }
}
