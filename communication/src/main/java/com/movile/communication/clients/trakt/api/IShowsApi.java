package com.movile.communication.clients.trakt.api;

import com.movile.common.model.shows.Show;
import com.movile.common.model.shows.Trending;
import com.movile.communication.constants.ExtendedParameter;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
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

    /**
     * This method gets a show information
     *
     * @param id
     *         Requested page
     * @param extended
     *         Extended type for show {@link ExtendedParameter}
     *
     * @return List of trending shows
     */
    @GET("shows/{id}")
    Call<Show> getShow(@Path("id") Integer id, @Query("extended") ExtendedParameter extended);
}
