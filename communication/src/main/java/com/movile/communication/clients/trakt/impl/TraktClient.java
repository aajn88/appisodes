package com.movile.communication.clients.trakt.impl;

import android.content.Context;
import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.movile.common.model.shows.Trending;
import com.movile.communication.BuildConfig;
import com.movile.communication.clients.trakt.api.ITraktClient;
import com.movile.communication.clients.trakt.deserializers.MediaDeserializer;
import com.movile.communication.clients.trakt.interceptors.HeaderInterceptor;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This is the rest client for trakt servers
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
@Singleton
public class TraktClient implements ITraktClient {

    /** Tag for logs **/
    private static final String TAG_LOG = TraktClient.class.getName();

    /** The retrofit client **/
    private Retrofit mRetrofit;

    /** Application context **/
    @Inject
    private Context mContext;

    /**
     * This method returns the API of the selected class
     *
     * @param clazz
     *         Class of the desired API
     * @param <T>
     *         API class
     *
     * @return the instance API
     */
    @Override
    public <T> T getApi(Class<T> clazz) {

        synchronized (TraktClient.class) {
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .registerTypeAdapter(Trending.class, new MediaDeserializer()).create();
            mRetrofit = new Retrofit.Builder().baseUrl(BuildConfig.SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson)).client(getClient())
                    .build();
            return mRetrofit.create(clazz);
        }
    }

    /**
     * This method returns the specific client for this interceptor
     *
     * @return Client interceptor
     */
    public OkHttpClient getClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        // Just for debug level
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(new HeaderInterceptor(mContext))
                .addInterceptor(loggingInterceptor).build();
    }

    /**
     * This method executes a call to the server and returns the result. If an error occurs, then
     * null is returned
     *
     * @param call
     *         Call to be executed
     *
     * @return The response. Returns null if an error occurs
     */
    @Override
    public <T> Response<T> execute(Call<T> call) {
        Response<T> response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            Log.e(TAG_LOG, "An error occurs talking to the server", e);
        } catch (RuntimeException e) {
            Log.e(TAG_LOG, "unexpected error occurs creating the request or decoding the response",
                    e);
        }
        return response;
    }

}
