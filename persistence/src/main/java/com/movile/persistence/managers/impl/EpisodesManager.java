package com.movile.persistence.managers.impl;

import android.util.Log;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.movile.common.model.shows.Episode;
import com.movile.persistence.DatabaseHelper;
import com.movile.persistence.managers.api.IEpisodesManager;

import java.sql.SQLException;
import java.util.List;

/**
 * This is the implementation of the Episodes Manager interface
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
@Singleton
public class EpisodesManager extends CrudManager<Episode, Integer> implements IEpisodesManager {

    /** Tag for logs **/
    private static final String TAG_LOG = EpisodesManager.class.getName();

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
    public EpisodesManager(DatabaseHelper helper) throws SQLException {
        super(helper, Episode.class);
    }

    @Override
    public List<Episode> findByShowIdAndSeasonNumber(int showId, int seasonNumber) {
        List<Episode> episodes = null;
        try {
            episodes = getDao().queryBuilder().where().eq(Episode.SHOW_ID_COLUMN, showId).and()
                    .eq(Episode.SEASON_NUMBER_COLUMN, seasonNumber).query();
        } catch (SQLException e) {
            Log.e(TAG_LOG,
                    String.format("Error while finding episodes for showId = %s and season = %s",
                            showId, seasonNumber), e);
        }
        return episodes;
    }
}
