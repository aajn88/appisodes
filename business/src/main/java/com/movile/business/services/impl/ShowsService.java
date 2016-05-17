package com.movile.business.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.movile.business.services.api.IShowsService;
import com.movile.common.model.common.Pagination;
import com.movile.common.model.shows.Season;
import com.movile.common.model.shows.Show;
import com.movile.common.model.shows.Trending;
import com.movile.communication.clients.trakt.api.ITraktClient;
import com.movile.communication.clients.trakt.api.shows.IShowsApi;
import com.movile.communication.constants.ExtendedParameter;
import com.movile.persistence.managers.api.ISeasonsManager;
import com.movile.persistence.managers.api.IShowsManager;

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

    /** Shows API. This variable should not be accessed directly. **/
    private IShowsApi mShowsApi;

    /** Shows manager **/
    @Inject
    private IShowsManager mShowsManager;

    /** Seasons manager **/
    @Inject
    private ISeasonsManager mSeasonsManager;

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
        IShowsApi showsApi = getShowsApi();
        Call<List<Trending<Show>>> call = showsApi.getTrending(page, limit);
        Response<List<Trending<Show>>> response = mTraktClient.execute(call);

        if (response == null || !response.isSuccessful()) {
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

    /**
     * This method gets the show from the server. Once server is contacted, the show is persisted in
     * the DB. Then DB record is returned
     *
     * @param id
     *         Trakt id of the show
     *
     * @return The show. Returns null if an error occurs
     */
    @Override
    public Show getShow(int id) {
        Show show = mShowsManager.findById(id);
        if (show != null) {
            return show;
        }

        IShowsApi showsApi = getShowsApi();
        Response<Show> fullResponse = mTraktClient
                .execute(showsApi.getShow(id, ExtendedParameter.FULL));

        if (fullResponse == null || !fullResponse.isSuccessful() ||
                (show = fullResponse.body()) == null) {
            return null;
        }

        Response<Show> imagesResponse = mTraktClient
                .execute(showsApi.getShow(id, ExtendedParameter.IMAGES));

        if (imagesResponse == null || !imagesResponse.isSuccessful() ||
                imagesResponse.body() == null) {
            return show;
        }

        show.setImages(imagesResponse.body().getImages());
        show.setLocalId(show.getIds().getTrakt());

        mShowsManager.createOrUpdate(show);
        return show;
    }

    /**
     * This method gets the season from the server. If exists an error contacting the server, then
     * DB info is returned. If no solution exists, then returns null
     *
     * @param showId
     *         Show id
     *
     * @return List of seasons. Returns null if an error occurs
     */
    @Override
    public List<Season> getSeasons(int showId) {
        IShowsApi showsApi = getShowsApi();

        Response<List<Season>> response = mTraktClient
                .execute(showsApi.getSeasons(showId, ExtendedParameter.FULL));
        if (response == null || !response.isSuccessful()) {
            return mSeasonsManager.getSeasonsByShowId(showId);
        }
        List<Season> seasons = response.body();
        for (Season season : seasons) {
            season.setLocalId(season.getIds().getTrakt());
            season.setShowId(showId);
        }
        Response<List<Season>> imagesResponse = mTraktClient
                .execute(showsApi.getSeasons(showId, ExtendedParameter.IMAGES));
        if(imagesResponse == null || !imagesResponse.isSuccessful()) {
            return mSeasonsManager.getSeasonsByShowId(showId);
        }

        List<Season> images = imagesResponse.body();
        for (int i = 0; i < seasons.size(); i++) {
            Season season = seasons.get(i);
            season.setImages(images.get(i).getImages());
            mSeasonsManager.createOrUpdate(season);
        }

        return seasons;
    }

    /**
     * This method gets the shows API
     *
     * @return The shows API
     */
    private IShowsApi getShowsApi() {
        if (mShowsApi == null) {
            mShowsApi = mTraktClient.getApi(IShowsApi.class);
        }
        return mShowsApi;
    }
}
