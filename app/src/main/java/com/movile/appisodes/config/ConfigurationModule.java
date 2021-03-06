package com.movile.appisodes.config;

import android.app.Application;

import com.google.inject.AbstractModule;
import com.movile.business.services.api.IShowsService;
import com.movile.business.services.impl.SessionService;
import com.movile.business.services.impl.ShowsService;
import com.movile.common.services.ISessionService;
import com.movile.communication.clients.trakt.api.ITraktClient;
import com.movile.communication.clients.trakt.impl.TraktClient;
import com.movile.persistence.managers.api.IEpisodesManager;
import com.movile.persistence.managers.api.ISeasonsManager;
import com.movile.persistence.managers.api.IShowsManager;
import com.movile.persistence.managers.api.IUsersManager;
import com.movile.persistence.managers.impl.EpisodesManager;
import com.movile.persistence.managers.impl.SeasonsManager;
import com.movile.persistence.managers.impl.ShowsManager;
import com.movile.persistence.managers.impl.UsersManager;

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
        bind(IShowsService.class).to(ShowsService.class);
    }

    /**
     * This method binds the managers with their interfaces
     */
    private void bindManagers() {
        bind(IUsersManager.class).to(UsersManager.class);
        bind(IShowsManager.class).to(ShowsManager.class);
        bind(ISeasonsManager.class).to(SeasonsManager.class);
        bind(IEpisodesManager.class).to(EpisodesManager.class);
    }

    /**
     * This method binds other components and classes, such as clients
     */
    private void bindOthers() {
        bind(ITraktClient.class).to(TraktClient.class);
    }

}
