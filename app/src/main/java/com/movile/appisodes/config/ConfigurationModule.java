package com.movile.appisodes.config;

import android.app.Application;

import com.google.inject.AbstractModule;
import com.movile.business.services.impl.SessionService;
import com.movile.common.services.ISessionService;
import com.movile.communication.clients.trakt.api.ITraktClient;
import com.movile.communication.clients.trakt.impl.TraktClient;
import com.movile.persistence.managers.api.IAccessTokenManager;
import com.movile.persistence.managers.impl.AccessTokenManager;

/**
 * This is the RoboGuice configuration class
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class ConfigurationModule extends AbstractModule {

    /**
     * Constructor
     *
     * @param application
     *         The Android application
     */
    public ConfigurationModule(Application application) {}

    @Override
    protected void configure() {
        bindServices();
        bindManagers();
        bindOthers();
    }

    /**
     * This method binds the services with their interfaces
     */
    private void bindServices() {
        bind(ISessionService.class).to(SessionService.class);
    }

    /**
     * This method binds the managers with their interfaces
     */
    private void bindManagers() {
        bind(IAccessTokenManager.class).to(AccessTokenManager.class);
    }

    /**
     * This method binds other components and classes, such as clients
     */
    private void bindOthers() {
        bind(ITraktClient.class).to(TraktClient.class);
    }

}
