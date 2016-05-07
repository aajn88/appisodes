package com.movile.appisodes.config;

import android.app.Application;

import com.google.inject.AbstractModule;
import com.movile.business.services.api.IFoo;
import com.movile.business.services.impl.Foo;
import com.movile.communication.clients.api.IFooClient;
import com.movile.communication.clients.impl.FooClient;
import com.movile.persistence.managers.api.IFooManager;
import com.movile.persistence.managers.impl.FooManager;

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
        bind(IFoo.class).to(Foo.class);
    }

    /**
     * This method binds the managers with their interfaces
     */
    private void bindManagers() {
        bind(IFooManager.class).to(FooManager.class);
    }

    /**
     * This method binds other components and classes, such as clients
     */
    private void bindOthers() {
        bind(IFooClient.class).to(FooClient.class);
    }

}
