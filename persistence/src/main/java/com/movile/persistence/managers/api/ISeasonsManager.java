package com.movile.persistence.managers.api;

import com.movile.common.model.shows.Season;

import java.util.List;

/**
 * This is the Seasons Manager
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public interface ISeasonsManager extends ICrudManager<Season, Integer> {

    /**
     * This method gets the seasons by a show Id
     *
     * @param showId
     *         Show Id
     *
     * @return List of seasons of the show
     */
    List<Season> getSeasonsByShowId(int showId);

}
