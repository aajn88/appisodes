package com.movile.persistence.managers.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.movile.persistence.managers.api.IFooManager;

/**
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
@Singleton
public class FooManager implements IFooManager {

    @Inject
    private FooManagerHelper helper;

}
