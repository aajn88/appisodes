package com.movile.common.model.common;

/**
 * @author <a href="mailto:aajn88@gmail.com">Antonio Jimenez</a>
 */
public class Id {

    /** Trakt ID **/
    private String trakt;

    /** Slug ID **/
    private String slug;

    /** TVDB ID **/
    private Integer tvdb;

    /** IMDB ID **/
    private String imdb;

    /** TMDB ID **/
    private Integer tmdb;

    /** Tv Rage ID **/
    private String tvrage;

    /**
     * @return the trakt
     */
    public String getTrakt() {
        return trakt;
    }

    /**
     * @return trakt the trakt to set
     */
    public void setTrakt(String trakt) {
        this.trakt = trakt;
    }

    /**
     * @return the slug
     */
    public String getSlug() {
        return slug;
    }

    /**
     * @return slug the slug to set
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }

    /**
     * @return the tvdb
     */
    public Integer getTvdb() {
        return tvdb;
    }

    /**
     * @return tvdb the tvdb to set
     */
    public void setTvdb(Integer tvdb) {
        this.tvdb = tvdb;
    }

    /**
     * @return the imdb
     */
    public String getImdb() {
        return imdb;
    }

    /**
     * @return imdb the imdb to set
     */
    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    /**
     * @return the tmdb
     */
    public Integer getTmdb() {
        return tmdb;
    }

    /**
     * @return tmdb the tmdb to set
     */
    public void setTmdb(Integer tmdb) {
        this.tmdb = tmdb;
    }

    /**
     * @return the tvrage
     */
    public String getTvrage() {
        return tvrage;
    }

    /**
     * @return tvrage the tvrage to set
     */
    public void setTvrage(String tvrage) {
        this.tvrage = tvrage;
    }
}
