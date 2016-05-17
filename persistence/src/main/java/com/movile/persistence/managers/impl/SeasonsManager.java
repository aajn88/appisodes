package com.movile.persistence.managers.impl;

import android.util.Log;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.movile.common.model.shows.Season;
import com.movile.persistence.DatabaseHelper;
import com.movile.persistence.managers.api.ISeasonsManager;

import java.sql.SQLException;
import java.util.List;

/**
 * This is the Seasons manager implementation
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
@Singleton
public class SeasonsManager extends CrudManager<Season, Integer> implements ISeasonsManager {

    /** Tag for logs **/
    private static final String TAG_LOG = SeasonsManager.class.getName();

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
    public SeasonsManager(DatabaseHelper helper) throws SQLException {
        super(helper, Season.class);
    }

    /**
     * This method gets the seasons by a show Id
     *
     * @param showId
     *         Show Id
     *
     * @return List of seasons of the show
     */
    @Override
    public List<Season> getSeasonsByShowId(int showId) {
        List<Season> seasons = null;
        try {
            seasons = getDao().queryBuilder().where().eq(Season.SHOW_ID_COLUMN, showId).query();
        } catch (SQLException e) {
            Log.e(TAG_LOG,
                    String.format("Error occurs searching seasons for [showId = %s]", showId), e);
        }
        return seasons;
    }
}
