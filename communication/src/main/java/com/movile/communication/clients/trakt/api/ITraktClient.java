package com.movile.communication.clients.trakt.api;

import retrofit2.Call;
import retrofit2.Response;

/**
 * This interface offers the services for trakt servers communication
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public interface ITraktClient {

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
    <T> T getApi(Class<T> clazz);

    /**
     * This method executes a call to the server and returns the result. If an error occurs, then
     * null is returned
     *
     * @param call
     *         Call to be executed
     * @param <T>
     *         Response type
     *
     * @return The response. Returns null if an error occurs
     */
    <T> Response<T> execute(Call<T> call);

}
