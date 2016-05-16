package com.movile.persistence.managers.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.movile.common.model.shows.Show;
import com.movile.persistence.DatabaseHelper;
import com.movile.persistence.managers.api.IShowsManager;

import java.sql.SQLException;

/**
 * Implementation of the shows manager {@link IShowsManager}
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
@Singleton
public class ShowsManager extends CrudManager<Show, Integer> implements IShowsManager {

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
    public ShowsManager(DatabaseHelper helper) throws SQLException {
        super(helper, Show.class);
    }
}
