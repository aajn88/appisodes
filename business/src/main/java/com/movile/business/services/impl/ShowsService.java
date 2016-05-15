package com.movile.business.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.movile.business.services.api.IShowsService;
import com.movile.common.model.common.Pagination;
import com.movile.common.model.shows.Show;
import com.movile.common.model.shows.Trending;
import com.movile.communication.clients.trakt.api.IShowsApi;
import com.movile.communication.clients.trakt.api.ITraktClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Implementation of the shows service
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
@Singleton
public class ShowsService implements IShowsService {

    /** Page number header **/
    private static final String PAGE_NUMBER = "X-Pagination-Page";

    /** Items per page **/
    private static final String ITEMS_PER_PAGE = "X-Pagination-Limit";

    /** Page count header **/
    private static final String PAGE_COUNT = "X-Pagination-Page-Count";

    /** Total items count **/
    private static final String ITEMS_COUNT = "X-Pagination-Item-Count";

    /** Trakt Client **/
    @Inject
    private ITraktClient mTraktClient;

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
    @Override
    public Pagination<Trending<Show>> getTrending(int page, int limit) {
        IShowsApi showsApi = mTraktClient.getApi(IShowsApi.class);
        Call<List<Trending<Show>>> call = showsApi.getTrending(page, limit);
        Response<List<Trending<Show>>> response = mTraktClient.execute(call);

        if(response == null || !response.isSuccessful()) {
            return null;
        }

        Pagination<Trending<Show>> onePage = new Pagination<>();
        onePage.setItems(response.body());
        onePage.setPage(Integer.parseInt(response.headers().get(PAGE_NUMBER)));
        onePage.setItemsPerPage(Integer.parseInt(response.headers().get(ITEMS_PER_PAGE)));
        onePage.setPagesCount(Integer.parseInt(response.headers().get(PAGE_COUNT)));
        onePage.setItemsCount(Integer.parseInt(response.headers().get(ITEMS_COUNT)));

        return onePage;
    }
}
