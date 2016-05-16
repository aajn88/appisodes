package com.movile.business.services.api;

import com.movile.common.model.common.Pagination;
import com.movile.common.model.shows.Show;
import com.movile.common.model.shows.Trending;

/**
 * This is the interface that exposes the shows services such as the trending shows or specific
 * information about a show
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public interface IShowsService {

    /**
     * This method returns the trending shows
     *
     * @param page
     *         Page to retrieve
     * @param limit
     *         Limit of elements per page
     *
     * @return The current show page
     */
    Pagination<Trending<Show>> getTrending(int page, int limit);

    /**
     * This method gets the show from the server. Once server is contacted, the show is persisted in
     * the DB. Then DB record is returned
     *
     * @param id
     *         Trakt id of the show
     *
     * @return The show. Returns null if an error occurs
     */
    Show getShow(int id);

}
