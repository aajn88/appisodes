package com.movile.business;

import com.movile.business.services.impl.SessionService;
import com.movile.common.constants.Property;
import com.movile.common.helpers.PropertiesAdminHelper;
import com.movile.common.model.authentication.AccessToken;
import com.movile.common.model.authentication.GeneratedCode;
import com.movile.common.model.authentication.User;
import com.movile.common.model.common.Image;
import com.movile.common.model.common.ImagesWrapper;
import com.movile.common.services.ISessionService;
import com.movile.communication.clients.trakt.api.IAuthenticationApi;
import com.movile.communication.clients.trakt.api.ITraktClient;
import com.movile.communication.clients.trakt.api.IUserApi;
import com.movile.communication.clients.trakt.model.GenerateCodeRequest;
import com.movile.communication.clients.trakt.model.GetTokenRequest;
import com.movile.communication.clients.trakt.model.GetUserSettingsResponse;
import com.movile.persistence.managers.api.IUsersManager;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import java.lang.reflect.Field;

import retrofit2.Call;
import retrofit2.Response;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class SessionServiceTest {

    /** Properties Helper mock **/
    private PropertiesAdminHelper mPropertiesHelperMock;

    /** Trakt client mock **/
    private ITraktClient mTraktClientMock;

    /** Authentication API mock **/
    private IAuthenticationApi mAuthenticationApiMock;

    /** User API mock **/
    private IUserApi mUserApiMock;

    /** Users Manager mock **/
    private IUsersManager mUsersManagerMock;

    /** Session Service **/
    private ISessionService mSessionService;

    @Before
    public void setup() throws Exception {
        mSessionService = new SessionService();
        mTraktClientMock = EasyMock.createNiceMock(ITraktClient.class);
        mAuthenticationApiMock = EasyMock.createNiceMock(IAuthenticationApi.class);
        mUserApiMock = EasyMock.createNiceMock(IUserApi.class);
        mPropertiesHelperMock = EasyMock.createNiceMock(PropertiesAdminHelper.class);
        mUsersManagerMock = EasyMock.createNiceMock(IUsersManager.class);

        Field field = SessionService.class.getDeclaredField("mTraktClient");
        field.setAccessible(true);
        field.set(mSessionService, mTraktClientMock);
        field = SessionService.class.getDeclaredField("mPropertiesHelper");
        field.setAccessible(true);
        field.set(mSessionService, mPropertiesHelperMock);
        field = SessionService.class.getDeclaredField("mUsersManager");
        field.setAccessible(true);
        field.set(mSessionService, mUsersManagerMock);
    }

    @Test
    public void doAuthenticationTest() {
        GeneratedCode generatedCode = new GeneratedCode();
        generatedCode.setDeviceCode("device_code");
        generatedCode.setUserCode("user_code");
        generatedCode.setVerificationUrl("url");
        generatedCode.setExpiresIn(10);
        generatedCode.setInterval(2);

        Response response = Response.success(generatedCode);
        Response<AccessToken> responseAt = Response.success(new AccessToken());

        GetUserSettingsResponse user = new GetUserSettingsResponse();
        user.setUser(new User());
        user.getUser().setImages(new ImagesWrapper());
        user.getUser().getImages().put(User.AVATAR, new Image());

        EasyMock.reset(mTraktClientMock, mAuthenticationApiMock, mPropertiesHelperMock,
                mUserApiMock);

        EasyMock.expect(mTraktClientMock.getApi(EasyMock.anyObject(Class.class)))
                .andReturn(mAuthenticationApiMock);
        EasyMock.expect(mPropertiesHelperMock.getProperty(Property.CLIENT_ID))
                .andReturn("client_id").anyTimes();
        EasyMock.expect(
                mAuthenticationApiMock.generateCode(EasyMock.anyObject(GenerateCodeRequest.class)))
                .andReturn(null);
        EasyMock.expect(mTraktClientMock.execute(EasyMock.anyObject(Call.class)))
                .andReturn(response);

        EasyMock.expect(mPropertiesHelperMock.getProperty(Property.CLIENT_SECRET))
                .andReturn("secret_id").anyTimes();
        EasyMock.expect(mAuthenticationApiMock.getToken(EasyMock.anyObject(GetTokenRequest.class)))
                .andReturn(null).anyTimes();

        EasyMock.replay(mTraktClientMock, mAuthenticationApiMock, mPropertiesHelperMock,
                mUserApiMock);

        mSessionService.doAuthentication(new SimpleCallBack());

        EasyMock.verify(mTraktClientMock, mAuthenticationApiMock, mPropertiesHelperMock,
                mUserApiMock);
    }

    @Test
    public void doAuthorizationResponseNull() {
        EasyMock.reset(mTraktClientMock, mAuthenticationApiMock, mPropertiesHelperMock);

        EasyMock.expect(mTraktClientMock.getApi(EasyMock.anyObject(Class.class)))
                .andReturn(mAuthenticationApiMock);
        EasyMock.expect(mPropertiesHelperMock.getProperty(Property.CLIENT_ID))
                .andReturn("client_id").anyTimes();
        EasyMock.expect(
                mAuthenticationApiMock.generateCode(EasyMock.anyObject(GenerateCodeRequest.class)))
                .andReturn(null);
        EasyMock.expect(mTraktClientMock.execute(EasyMock.anyObject(Call.class))).andReturn(null);

        EasyMock.replay(mTraktClientMock, mAuthenticationApiMock, mPropertiesHelperMock);

        mSessionService.doAuthentication(new SimpleCallBack());

        EasyMock.verify(mTraktClientMock, mAuthenticationApiMock, mPropertiesHelperMock);
    }

    @Test(expected = NullPointerException.class)
    public void doAuthorizationCallbackNull() {
        mSessionService.doAuthentication(null);
    }

    private class SimpleCallBack implements ISessionService.IAuthenticationResultCallback {

        /**
         * This method will be called if the user has already accepted the app integration and has
         * granted permissions
         */
        @Override
        public void onSuccess() {

        }

        /**
         * This method will be called if expiration time has been reached and the user has not already
         * accepted the app integration
         */
        @Override
        public void onFailure() {

        }

        /**
         * This method will be called if the user denies access to the app integration
         */
        @Override
        public void onNotAuthorized() {

        }
    }

}