package com.movile.communication.clients.trakt.interceptors;

import android.util.Log;

import com.movile.common.utils.StringUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class LogInterceptor implements Interceptor {

    /** Tag for logs **/
    private static final String TAG_LOG = LogInterceptor.class.getName();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Log.d(TAG_LOG, "Performing: " + request.method() + ": " + request.url());
        RequestBody body = request.body();
        if (body != null) {
            try {
                Buffer buffer = new Buffer();
                body.writeTo(buffer);
                Log.d(TAG_LOG, StringUtils.format("Body: %s", buffer.readUtf8()));
            } catch (IOException e) {
                Log.e(TAG_LOG, "An error occurs while reading request body", e);
            }
        }

        Response response = chain.proceed(request);
        String responseBodyStr = null;
        if(response != null && response.body() != null) {
//            responseBodyStr = response.body();
        }
        Log.d(TAG_LOG, StringUtils.format("Response: %s", responseBodyStr));
        return response;
    }
}
