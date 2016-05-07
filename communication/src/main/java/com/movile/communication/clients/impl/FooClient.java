package com.movile.communication.clients.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.movile.communication.clients.api.IFooClient;

/**
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
@Singleton
public class FooClient implements IFooClient {

    @Inject
    private FooClientHelper helper;

}
