package com.movile.communication.clients.trakt.api;

import com.movile.common.model.shows.Show;
import com.movile.common.model.shows.Trending;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * This is the shows API where the shows information can be reached
 *
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public interface IShowsApi {

    /**
     * This method gets the trending shows
     *
     * @param page
     *         Requested page
     * @param limit
     *         Items per page
     *
     * @return List of trending shows
     */
    @GET("shows/trending")
    Call<List<Trending<Show>>> getTrending(@Query("page") int page, @Query("limit") int limit);
}
