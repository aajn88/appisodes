package com.movile.business.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.movile.business.services.api.IFoo;

/**
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
@Singleton
public class Foo implements IFoo {

    @Inject
    private FooHelper helper;

}
