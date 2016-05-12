package com.movile.communication.clients.trakt.api;

import com.movile.common.model.authentication.AccessToken;
import com.movile.common.model.authentication.GeneratedCode;
import com.movile.communication.clients.trakt.model.GenerateCodeRequest;
import com.movile.communication.clients.trakt.model.GetTokenRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * This is the API for authentication method such as generate
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public interface IAuthenticationApi {

    /**
     * This method generates the code for authentication
     *
     * @param request
     *         Request that contains the client id
     *
     * @return The call generate code response
     */
    @POST("oauth/device/code")
    Call<GeneratedCode> generateCode(@Body GenerateCodeRequest request);

    /**
     * This method polls for token verification
     *
     * @param request
     *         The request that contain the basic client info
     *
     * @return The call get token response
     */
    @POST("oauth/device/token")
    Call<AccessToken> getToken(@Body GetTokenRequest request);

}
