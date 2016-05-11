package com.movile.communication.clients.trakt;

import android.content.Context;

import com.google.inject.Inject;
import com.movile.common.services.ISessionService;
import com.movile.communication.constants.RestConstants;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
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

    /**
     * This method returns the specific client for this interceptor
     *
     * @return Client interceptor
     */
    public static OkHttpClient getClient(Context context) {
        OkHttpClient mClient = new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor(context)).build();
        return mClient;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        request = request.newBuilder()
                .addHeader(RestConstants.CONTENT_TYPE, RestConstants.APPLICATION_JSON).build();

        return chain.proceed(request);
    }

}
