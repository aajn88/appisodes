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

}
