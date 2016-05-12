package com.movile.communication.clients.trakt.interceptors;

import android.content.Context;

import com.google.inject.Inject;
import com.movile.common.constants.Property;
import com.movile.common.helpers.PropertiesAdminHelper;
import com.movile.common.model.authentication.AccessToken;
import com.movile.common.services.ISessionService;
import com.movile.communication.constants.RestConstants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import roboguice.RoboGuice;
import roboguice.inject.RoboInjector;

/**
 * This is the header interceptor for Trakt requests
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class HeaderInterceptor implements Interceptor {

    /** Trakt version **/
    private static final String TRAKT_VERSION = "2";

    /** Properties helper **/
    @Inject
    private PropertiesAdminHelper mPropertiesHelper;

    /** Session Service **/
    @Inject
    private ISessionService mSessionService;

    /**
     * Constructor for Roboguice injection
     *
     * @param context
     *         App context
     */
    public HeaderInterceptor(Context context) {
        RoboInjector injector = RoboGuice.getInjector(context);
        injector.injectMembersWithoutViews(this);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Request.Builder builder = request.newBuilder()
                .addHeader(RestConstants.CONTENT_TYPE, RestConstants.APPLICATION_JSON);
        builder.addHeader(RestConstants.TRAKT_API_VERSION, TRAKT_VERSION);
        builder.addHeader(RestConstants.TRAKT_API_KEY,
                mPropertiesHelper.getProperty(Property.CLIENT_ID));

        AccessToken token = mSessionService.getAccessToken();
        if (token != null) {
            builder.addHeader(RestConstants.AUTHORIZATION,
                    token.getType() + " " + token.getToken());
        }

        return chain.proceed(builder.build());
    }

}
