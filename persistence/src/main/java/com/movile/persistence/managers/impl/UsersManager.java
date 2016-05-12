package com.movile.persistence.managers.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.movile.common.model.authentication.User;
import com.movile.persistence.DatabaseHelper;
import com.movile.persistence.managers.api.IUsersManager;

import java.sql.SQLException;

/**
 * This is the Users manager implementation
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
@Singleton
public class UsersManager extends CrudManager<User, Integer> implements IUsersManager {

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
    public UsersManager(DatabaseHelper helper) throws SQLException {
        super(helper, User.class);
    }
}
