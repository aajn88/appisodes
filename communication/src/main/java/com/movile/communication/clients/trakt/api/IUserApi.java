package com.movile.communication.clients.trakt.api;

import com.movile.communication.clients.trakt.model.GetUserSettingsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * This is the users API
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public interface IUserApi {

    /**
     * This method requests the user settings
     *
     * @return Call to the user settings
     */
    @GET("users/settings")
    Call<GetUserSettingsResponse> getUserSettings();

}
