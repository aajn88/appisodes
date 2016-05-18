package com.movile.persistence.managers.api;

import com.movile.common.model.shows.Episode;

import java.util.List;

/**
 * Interface for Episodes manager
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public interface IEpisodesManager extends ICrudManager<Episode, Integer> {

    List<Episode> findByShowIdAndSeasonNumber(int showId, int seasonNumber);

}
