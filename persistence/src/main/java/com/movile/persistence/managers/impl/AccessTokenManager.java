package com.movile.persistence.managers.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.movile.common.model.authentication.AccessToken;
import com.movile.persistence.DatabaseHelper;
import com.movile.persistence.managers.api.IAccessTokenManager;

import java.sql.SQLException;

/**
 * This is the Access Token manager implementation
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
@Singleton
public class AccessTokenManager extends CrudManager<AccessToken, Integer>
        implements IAccessTokenManager {

    /**
     * This is the main constructor of the CrudManager
     *
     * @param helper
     *         The DBHelper
     *
     * @throws SQLException
     *         If there's an error creating the Entity's DAO
     */
    @Inject
    public AccessTokenManager(DatabaseHelper helper) throws SQLException {
        super(helper, AccessToken.class);
    }
}
